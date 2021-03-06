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
import com.viper.opc.client.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import com.viper.opc.client.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;

public interface OpcUaXmlDataTypeCodec<T> extends
    DataTypeCodec<T, OpcUaXmlStreamDecoder, OpcUaXmlStreamEncoder> {

    /**
     * Decode a {@link T} using the provided {@link OpcUaXmlStreamDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param reader  the {@link OpcUaXmlStreamDecoder} to decode from.
     * @return a decoded {@link T}.
     */
    @Override
    T decode(SerializationContext context, OpcUaXmlStreamDecoder reader) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided {@link OpcUaXmlStreamEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param writer  the {@link OpcUaXmlStreamEncoder} to encode to.
     * @param value   the value {@link T} to encode.
     */
    @Override
    void encode(SerializationContext context, OpcUaXmlStreamEncoder writer, T value) throws UaSerializationException;

}
