/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.serialization.codecs;

import com.viper.opc.client.opcua.stack.core.UaSerializationException;
import com.viper.opc.client.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import com.viper.opc.client.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;

public interface OpcUaBinaryDataTypeCodec<T> extends
    DataTypeCodec<T, OpcUaBinaryStreamDecoder, OpcUaBinaryStreamEncoder> {

    /**
     * Decode a {@link T} using the provided {@link OpcUaBinaryStreamDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param reader  the {@link OpcUaBinaryStreamDecoder} to decode from.
     * @return a decoded {@link T}.
     */
    @Override
    T decode(SerializationContext context, OpcUaBinaryStreamDecoder reader) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided {@link OpcUaBinaryStreamEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param writer  the {@link OpcUaBinaryStreamEncoder} to encode to.
     * @param value   the {@link T} to encode.
     */
    @Override
    void encode(SerializationContext context, OpcUaBinaryStreamEncoder writer, T value) throws UaSerializationException;

}
