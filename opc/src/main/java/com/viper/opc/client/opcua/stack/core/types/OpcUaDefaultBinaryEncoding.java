/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import com.viper.opc.client.opcua.stack.core.StatusCodes;
import com.viper.opc.client.opcua.stack.core.UaSerializationException;
import com.viper.opc.client.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import com.viper.opc.client.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;
import com.viper.opc.client.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.ByteString;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExtensionObject;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;

import static io.netty.buffer.Unpooled.buffer;

public class OpcUaDefaultBinaryEncoding implements DataTypeEncoding {

    public static final QualifiedName ENCODING_NAME =
        new QualifiedName(0, "Default Binary");

    public static OpcUaDefaultBinaryEncoding getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final OpcUaDefaultBinaryEncoding INSTANCE = new OpcUaDefaultBinaryEncoding();
    }

    private OpcUaDefaultBinaryEncoding() {}

    @Override
    public QualifiedName getName() {
        return ENCODING_NAME;
    }

    @Override
    public ExtensionObject.BodyType getBodyType() {
        return ExtensionObject.BodyType.ByteString;
    }

    @Override
    public Object encode(
        SerializationContext context,
        Object decodedBody,
        NodeId encodingId
    ) {

        try {
            @SuppressWarnings("unchecked")
            OpcUaBinaryDataTypeCodec<Object> codec =
                (OpcUaBinaryDataTypeCodec<Object>)
                    context.getDataTypeManager().getCodec(encodingId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingId=" + encodingId);
            }

            ByteBuf buffer = buffer();

            try {
                OpcUaBinaryStreamEncoder writer = new OpcUaBinaryStreamEncoder(context).setBuffer(buffer);

                codec.encode(context, writer, decodedBody);

                byte[] bs = new byte[buffer.readableBytes()];
                buffer.readBytes(bs);

                return ByteString.of(bs);
            } finally {
                buffer.release();
            }
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public Object decode(
        SerializationContext context,
        Object encodedBody,
        NodeId encodingId
    ) {

        try {
            @SuppressWarnings("unchecked")
            OpcUaBinaryDataTypeCodec<Object> codec =
                (OpcUaBinaryDataTypeCodec<Object>)
                    context.getDataTypeManager().getCodec(encodingId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered for encodingId=" + encodingId);
            }

            ByteString binaryBody = (ByteString) encodedBody;
            byte[] bs = binaryBody.bytesOrEmpty();

            ByteBuf buffer = Unpooled.wrappedBuffer(bs);

            OpcUaBinaryStreamDecoder reader = new OpcUaBinaryStreamDecoder(context).setBuffer(buffer);

            return codec.decode(context, reader);
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
