/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.client.transport.websocket;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import com.viper.opc.client.opcua.stack.client.UaStackClient;
import com.viper.opc.client.opcua.stack.client.transport.uasc.ClientSecureChannel;
import com.viper.opc.client.opcua.stack.core.StatusCodes;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.transport.TransportProfile;
import com.viper.opc.client.opcua.stack.core.util.EndpointUtil;

public class OpcClientWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final UaStackClient client;
    private final CompletableFuture<ClientSecureChannel> handshake;

    public OpcClientWebSocketChannelInitializer(
        UaStackClient client,
        CompletableFuture<ClientSecureChannel> handshake) {

        this.client = client;
        this.handshake = handshake;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        String endpointUrl = client.getConfig().getEndpoint().getEndpointUrl();
        String scheme = EndpointUtil.getScheme(endpointUrl);

        TransportProfile transportProfile = TransportProfile
            .fromUri(client.getConfig().getEndpoint().getTransportProfileUri());

        String subprotocol;
        if (transportProfile == TransportProfile.WSS_UASC_UABINARY) {
            subprotocol = "opcua+cp";
        } else if (transportProfile == TransportProfile.WSS_UAJSON) {
            subprotocol = "opcua+uajson";
        } else {
            throw new UaException(
                StatusCodes.Bad_InternalError,
                "unexpected TransportProfile: " + transportProfile);
        }

        if ("opc.wss".equalsIgnoreCase(scheme)) {
            SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

            channel.pipeline().addLast(sslContext.newHandler(channel.alloc()));
        }

        int maxMessageSize = client.getConfig().getEncodingLimits().getMaxMessageSize();

        channel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        channel.pipeline().addLast(new HttpClientCodec());
        channel.pipeline().addLast(new HttpObjectAggregator(maxMessageSize));

        channel.pipeline().addLast(
            new WebSocketClientProtocolHandler(
                WebSocketClientHandshakerFactory.newHandshaker(
                    new URI(endpointUrl),
                    WebSocketVersion.V13,
                    subprotocol,
                    true,
                    new DefaultHttpHeaders(),
                    client.getConfig().getEncodingLimits().getMaxChunkSize()
                )
            )
        );

        channel.pipeline().addLast(
            new WebSocketFrameAggregator(
                client.getConfig().getEncodingLimits().getMaxMessageSize())
        );

        // OpcClientWebSocketFrameCodec adds UascClientAcknowledgeHandler when the WS upgrade is done.
        channel.pipeline().addLast(new OpcClientWebSocketBinaryFrameCodec(client, handshake));
    }

}
