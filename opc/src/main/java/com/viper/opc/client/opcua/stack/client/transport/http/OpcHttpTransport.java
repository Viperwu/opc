/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.client.transport.http;

import java.util.concurrent.CompletableFuture;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.pool.AbstractChannelPoolHandler;
import io.netty.channel.pool.ChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.util.concurrent.FutureListener;
import com.viper.opc.client.opcua.stack.client.UaStackClient;
import com.viper.opc.client.opcua.stack.client.transport.AbstractTransport;
import com.viper.opc.client.opcua.stack.client.transport.UaTransport;
import com.viper.opc.client.opcua.stack.core.serialization.UaRequestMessage;
import com.viper.opc.client.opcua.stack.core.serialization.UaResponseMessage;
import com.viper.opc.client.opcua.stack.core.util.EndpointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpcHttpTransport extends AbstractTransport {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpcHttpTransport.class);

    private ChannelPool channelPool = null;

    private final UaStackClient client;

    public OpcHttpTransport(UaStackClient client) {
        super(client.getConfig());

        this.client = client;
    }

    @Override
    public synchronized CompletableFuture<UaTransport> connect() {
        if (channelPool == null) {
            channelPool = createChannelPool(client);
        }

        return CompletableFuture.completedFuture(OpcHttpTransport.this);
    }

    @Override
    public synchronized CompletableFuture<UaTransport> disconnect() {
        if (channelPool != null) {
            channelPool.close();
            channelPool = null;
        }

        return CompletableFuture.completedFuture(OpcHttpTransport.this);
    }

    @Override
    public synchronized CompletableFuture<Channel> channel() {
        return acquireChannel();
    }

    @Override
    public synchronized CompletableFuture<UaResponseMessage> sendRequest(UaRequestMessage request) {
        LOGGER.trace("sendRequest({})", request.getClass().getSimpleName());

        return acquireChannel().thenCompose(ch ->
            sendRequest(request, ch, true)
                .whenComplete((response, ex) -> releaseChannel(ch))
        );
    }

    private synchronized CompletableFuture<Channel> acquireChannel() {
        if (channelPool == null) {
            channelPool = createChannelPool(client);
        }

        CompletableFuture<Channel> future = new CompletableFuture<>();

        channelPool.acquire().addListener((FutureListener<Channel>) cf -> {
            if (cf.isSuccess()) {
                future.complete(cf.getNow());
            } else {
                future.completeExceptionally(cf.cause());
            }
        });

        return future;
    }

    private synchronized void releaseChannel(Channel channel) {
        if (channelPool != null) {
            channelPool.release(channel);
        }
    }

    private static ChannelPool createChannelPool(UaStackClient client) {
        final String endpointUrl = client.getConfig().getEndpoint().getEndpointUrl();

        String host = EndpointUtil.getHost(endpointUrl);
        if (host == null) host = "";

        int port = EndpointUtil.getPort(endpointUrl);

        LOGGER.debug("createChannelPool() host={} port={}", host, port);

        Bootstrap bootstrap = new Bootstrap()
            .channel(NioSocketChannel.class)
            .group(client.getConfig().getEventLoop())
            .remoteAddress(host, port);

        return new SimpleChannelPool(
            bootstrap,
            new AbstractChannelPoolHandler() {
                @Override
                public void channelCreated(Channel channel) throws Exception {
                    String scheme = EndpointUtil.getScheme(endpointUrl);

                    if ("https".equalsIgnoreCase(scheme) || "opc.https".equalsIgnoreCase(scheme)) {
                        SslContext sslContext = SslContextBuilder.forClient()
                            .trustManager(InsecureTrustManagerFactory.INSTANCE)
                            .build();

                        channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
                    }

                    int maxMessageSize = client.getConfig().getEncodingLimits().getMaxMessageSize();

                    channel.pipeline().addLast(new LoggingHandler(LogLevel.TRACE));
                    channel.pipeline().addLast(new HttpClientCodec());
                    channel.pipeline().addLast(new HttpObjectAggregator(maxMessageSize));
                    channel.pipeline().addLast(new OpcClientHttpCodec(client));

                    LOGGER.debug("channelCreated(): " + channel);
                }

                @Override
                public void channelAcquired(Channel channel) {
                    LOGGER.debug("channelAcquired(): " + channel);
                }

                @Override
                public void channelReleased(Channel channel) {
                    LOGGER.debug("channelReleased(): " + channel);
                }
            }
        );
    }

}
