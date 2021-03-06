/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;
import com.viper.opc.client.opcua.stack.core.serialization.UaDecoder;
import com.viper.opc.client.opcua.stack.core.serialization.UaEncoder;
import com.viper.opc.client.opcua.stack.core.serialization.UaRequestMessage;
import com.viper.opc.client.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DeleteReferencesRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=504");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=506");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=505");

    private final RequestHeader requestHeader;

    private final DeleteReferencesItem[] referencesToDelete;

    public DeleteReferencesRequest(RequestHeader requestHeader,
                                   DeleteReferencesItem[] referencesToDelete) {
        this.requestHeader = requestHeader;
        this.referencesToDelete = referencesToDelete;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public DeleteReferencesItem[] getReferencesToDelete() {
        return referencesToDelete;
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteReferencesRequest> {
        @Override
        public Class<DeleteReferencesRequest> getType() {
            return DeleteReferencesRequest.class;
        }

        @Override
        public DeleteReferencesRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            DeleteReferencesItem[] referencesToDelete = (DeleteReferencesItem[]) decoder.readStructArray("ReferencesToDelete", DeleteReferencesItem.TYPE_ID);
            return new DeleteReferencesRequest(requestHeader, referencesToDelete);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DeleteReferencesRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("ReferencesToDelete", value.getReferencesToDelete(), DeleteReferencesItem.TYPE_ID);
        }
    }
}
