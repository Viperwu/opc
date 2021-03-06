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
import com.viper.opc.client.opcua.stack.core.serialization.UaResponseMessage;
import com.viper.opc.client.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.DiagnosticInfo;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SetTriggeringResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=776");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=778");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=777");

    private final ResponseHeader responseHeader;

    private final StatusCode[] addResults;

    private final DiagnosticInfo[] addDiagnosticInfos;

    private final StatusCode[] removeResults;

    private final DiagnosticInfo[] removeDiagnosticInfos;

    public SetTriggeringResponse(ResponseHeader responseHeader, StatusCode[] addResults,
                                 DiagnosticInfo[] addDiagnosticInfos, StatusCode[] removeResults,
                                 DiagnosticInfo[] removeDiagnosticInfos) {
        this.responseHeader = responseHeader;
        this.addResults = addResults;
        this.addDiagnosticInfos = addDiagnosticInfos;
        this.removeResults = removeResults;
        this.removeDiagnosticInfos = removeDiagnosticInfos;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public StatusCode[] getAddResults() {
        return addResults;
    }

    public DiagnosticInfo[] getAddDiagnosticInfos() {
        return addDiagnosticInfos;
    }

    public StatusCode[] getRemoveResults() {
        return removeResults;
    }

    public DiagnosticInfo[] getRemoveDiagnosticInfos() {
        return removeDiagnosticInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<SetTriggeringResponse> {
        @Override
        public Class<SetTriggeringResponse> getType() {
            return SetTriggeringResponse.class;
        }

        @Override
        public SetTriggeringResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            StatusCode[] addResults = decoder.readStatusCodeArray("AddResults");
            DiagnosticInfo[] addDiagnosticInfos = decoder.readDiagnosticInfoArray("AddDiagnosticInfos");
            StatusCode[] removeResults = decoder.readStatusCodeArray("RemoveResults");
            DiagnosticInfo[] removeDiagnosticInfos = decoder.readDiagnosticInfoArray("RemoveDiagnosticInfos");
            return new SetTriggeringResponse(responseHeader, addResults, addDiagnosticInfos, removeResults, removeDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SetTriggeringResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeStatusCodeArray("AddResults", value.getAddResults());
            encoder.writeDiagnosticInfoArray("AddDiagnosticInfos", value.getAddDiagnosticInfos());
            encoder.writeStatusCodeArray("RemoveResults", value.getRemoveResults());
            encoder.writeDiagnosticInfoArray("RemoveDiagnosticInfos", value.getRemoveDiagnosticInfos());
        }
    }
}
