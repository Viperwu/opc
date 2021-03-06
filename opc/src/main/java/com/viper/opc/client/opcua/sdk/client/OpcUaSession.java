/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.client;

import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.MoreObjects;
import com.viper.opc.client.opcua.sdk.client.api.UaSession;
import com.viper.opc.client.opcua.stack.core.types.builtin.ByteString;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OpcUaSession extends ConcurrentHashMap<String, Object> implements UaSession {

    private volatile ByteString serverNonce = ByteString.NULL_VALUE;

    private final NodeId authToken;
    private final NodeId sessionId;
    private final String sessionName;
    private final double sessionTimeout;
    private final UInteger maxRequestSize;
    private final ByteString serverCertificate;
    private final SignedSoftwareCertificate[] serverSoftwareCertificates;

    public OpcUaSession(NodeId authToken,
                        NodeId sessionId,
                        String sessionName,
                        double sessionTimeout,
                        UInteger maxRequestSize,
                        ByteString serverCertificate,
                        SignedSoftwareCertificate[] serverSoftwareCertificates) {

        this.authToken = authToken;
        this.sessionId = sessionId;
        this.sessionName = sessionName;
        this.sessionTimeout = sessionTimeout;
        this.maxRequestSize = maxRequestSize;
        this.serverCertificate = serverCertificate;
        this.serverSoftwareCertificates = serverSoftwareCertificates;
    }

    @Override
    public NodeId getAuthenticationToken() {
        return authToken;
    }

    @Override
    public NodeId getSessionId() {
        return sessionId;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public Double getSessionTimeout() {
        return sessionTimeout;
    }

    @Override
    public UInteger getMaxRequestSize() {
        return maxRequestSize;
    }

    @Override
    public SignedSoftwareCertificate[] getServerSoftwareCertificates() {
        return serverSoftwareCertificates;
    }

    @Override
    public ByteString getServerCertificate() {
        return serverCertificate;
    }

    @Override
    public ByteString getServerNonce() {
        return serverNonce;
    }

    public void setServerNonce(ByteString serverNonce) {
        this.serverNonce = serverNonce;
    }

    @Nullable
    @Override
    public Object getAttribute(@NotNull String name) {
        return get(name);
    }

    @Nullable
    @Override
    public Object setAttribute(@NotNull String name, @NotNull Object value) {
        return put(name, value);
    }

    @Override
    public Object removeAttribute(@NotNull String name) {
        return remove(name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("sessionId", sessionId)
            .add("sessionName", sessionName)
            .toString();
    }

}
