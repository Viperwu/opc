/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.types.builtin;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.viper.opc.client.opcua.stack.core.StatusCodes;
import com.viper.opc.client.opcua.stack.core.UaSerializationException;
import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;
import com.viper.opc.client.opcua.stack.core.serialization.UaStructure;
import com.viper.opc.client.opcua.stack.core.types.DataTypeEncoding;
import com.viper.opc.client.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import com.viper.opc.client.opcua.stack.core.types.OpcUaDefaultXmlEncoding;
import com.viper.opc.client.opcua.stack.core.util.Lazy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ExtensionObject {

    public enum BodyType {
        ByteString,
        XmlElement
    }

    private final Lazy<Object> decoded = new Lazy<>();

    private final BodyType bodyType;

    private final Object body;
    private final NodeId encodingId;

    public ExtensionObject(
        @NotNull ByteString body,
        @NotNull NodeId encodingId) {

        this((Object) body, encodingId);
    }

    public ExtensionObject(
        @NotNull XmlElement body,
        @NotNull NodeId encodingId) {

        this((Object) body, encodingId);
    }

    private ExtensionObject(
        @NotNull Object body,
        @NotNull NodeId encodingId) {

        this.body = body;
        this.encodingId = encodingId;

        if (body instanceof ByteString) {
            bodyType = BodyType.ByteString;
        } else if (body instanceof XmlElement) {
            bodyType = BodyType.XmlElement;
        } else {
            throw new IllegalArgumentException("body: " + body);
        }
    }

    public Object getBody() {
        return body;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public NodeId getEncodingId() {
        return encodingId;
    }

    public boolean isNull() {
        switch (bodyType) {
            case ByteString:
                return ((ByteString) body).isNull();
            case XmlElement:
                return ((XmlElement) body).isNull();
            default:
                throw new IllegalStateException("BodyType: " + bodyType);
        }
    }

    public Object decode(SerializationContext context) throws UaSerializationException {
        switch (bodyType) {
            case ByteString:
                return decode(context, OpcUaDefaultBinaryEncoding.getInstance());
            case XmlElement:
                return decode(context, OpcUaDefaultXmlEncoding.getInstance());
            default:
                throw new IllegalStateException("BodyType: " + bodyType);
        }
    }

    public Object decode(SerializationContext context, DataTypeEncoding encoding) throws UaSerializationException {
        return decoded.getOrCompute(() -> encoding.decode(context, body, encodingId));
    }

    @Nullable
    public Object decodeOrNull(SerializationContext context) {
        try {
            return decode(context);
        } catch (UaSerializationException e) {
            return null;
        }
    }

    public ExtensionObject transcode(
        SerializationContext context,
        NodeId newEncodingId,
        DataTypeEncoding newEncoding
    ) {

        if (this.encodingId.equals(newEncodingId)) {
            return this;
        } else {
            // The "fast" path: body is a encoded in Default Binary or Default XML.
            // No need to look up the DataTypeEncoding.
            Object struct = decodeOrNull(context);

            if (struct != null) {
                Object encoded = newEncoding.encode(context, struct, newEncodingId);

                return new ExtensionObject(encoded, newEncodingId);
            } else {
                // TODO look up current DataTypeEncoding via this.encodingId, try decoding again using that.
                return this;
            }
        }
    }

    public static ExtensionObject encode(
        SerializationContext context,
        UaStructure struct
    ) throws UaSerializationException {

        NodeId encodingId = struct.getBinaryEncodingId()
            .toNodeId(context.getNamespaceTable())
            .orElseThrow(
                () ->
                    new UaSerializationException(
                        StatusCodes.Bad_EncodingError,
                        "namespace not registered: " +
                            struct.getBinaryEncodingId().getNamespaceUri())
            );

        return encodeDefaultBinary(context, struct, encodingId);
    }

    public static ExtensionObject[] encodeArray(
        SerializationContext context,
        UaStructure[] structArray
    ) throws UaSerializationException {

        ExtensionObject[] xos = new ExtensionObject[structArray.length];

        for (int i = 0; i < xos.length; i++) {
            xos[i] = encode(context, structArray[i]);
        }

        return xos;
    }

    public static ExtensionObject encodeDefaultBinary(
        SerializationContext context,
        Object object,
        NodeId encodingId
    ) throws UaSerializationException {

        return encode(
            context,
            object,
            encodingId,
            OpcUaDefaultBinaryEncoding.getInstance()
        );
    }

    public static ExtensionObject encodeDefaultXml(
        SerializationContext context,
        Object object,
        NodeId encodingId
    ) throws UaSerializationException {

        return encode(
            context,
            object,
            encodingId,
            OpcUaDefaultXmlEncoding.getInstance()
        );
    }

    public static ExtensionObject encode(
        SerializationContext context,
        Object object,
        ExpandedNodeId xEncodingId,
        DataTypeEncoding encoding
    ) throws UaSerializationException {

        NodeId encodingId = xEncodingId.toNodeId(context.getNamespaceTable())
            .orElseThrow(
                () ->
                    new UaSerializationException(
                        StatusCodes.Bad_EncodingError,
                        "namespace not registered: " +
                            xEncodingId.getNamespaceUri())
            );

        Object body = encoding.encode(context, object, encodingId);

        return new ExtensionObject(body, encodingId);
    }

    public static ExtensionObject encode(
        SerializationContext context,
        Object object,
        NodeId encodingId,
        DataTypeEncoding encoding
    ) throws UaSerializationException {

        Object body = encoding.encode(context, object, encodingId);

        return new ExtensionObject(body, encodingId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtensionObject that = (ExtensionObject) o;

        return Objects.equal(body, that.body) &&
            Objects.equal(encodingId, that.encodingId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(body, encodingId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("encoded", body)
            .add("encodingId", encodingId)
            .toString();
    }

}
