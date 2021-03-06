/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.client.transport;

import java.util.concurrent.CompletableFuture;

import io.netty.util.Timeout;
import com.viper.opc.client.opcua.stack.core.serialization.UaRequestMessage;
import com.viper.opc.client.opcua.stack.core.serialization.UaResponseMessage;
import org.jetbrains.annotations.Nullable;

public class UaTransportRequest {

    private volatile Timeout timeout;

    private final UaRequestMessage request;
    private final CompletableFuture<UaResponseMessage> future;

    public UaTransportRequest(UaRequestMessage request) {
        this(request, new CompletableFuture<>());
    }

    public UaTransportRequest(UaRequestMessage request, CompletableFuture<UaResponseMessage> future) {
        this.request = request;
        this.future = future;
    }

    public UaRequestMessage getRequest() {
        return request;
    }

    public CompletableFuture<UaResponseMessage> getFuture() {
        return future;
    }

    public synchronized void setTimeout(Timeout timeout) {
        this.timeout = timeout;
    }

    @Nullable
    public synchronized Timeout getTimeout() {
        return timeout;
    }

}
