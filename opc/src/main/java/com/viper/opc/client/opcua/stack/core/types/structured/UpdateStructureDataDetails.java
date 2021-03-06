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
import com.viper.opc.client.opcua.stack.core.serialization.UaStructure;
import com.viper.opc.client.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.enumerated.PerformUpdateType;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UpdateStructureDataDetails extends HistoryUpdateDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11295");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11300");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11296");

    private final PerformUpdateType performInsertReplace;

    private final DataValue[] updateValues;

    public UpdateStructureDataDetails(NodeId nodeId, PerformUpdateType performInsertReplace,
                                      DataValue[] updateValues) {
        super(nodeId);
        this.performInsertReplace = performInsertReplace;
        this.updateValues = updateValues;
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

    public PerformUpdateType getPerformInsertReplace() {
        return performInsertReplace;
    }

    public DataValue[] getUpdateValues() {
        return updateValues;
    }

    public static final class Codec extends GenericDataTypeCodec<UpdateStructureDataDetails> {
        @Override
        public Class<UpdateStructureDataDetails> getType() {
            return UpdateStructureDataDetails.class;
        }

        @Override
        public UpdateStructureDataDetails decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = decoder.readEnum("PerformInsertReplace", PerformUpdateType.class);
            DataValue[] updateValues = decoder.readDataValueArray("UpdateValues");
            return new UpdateStructureDataDetails(nodeId, performInsertReplace, updateValues);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           UpdateStructureDataDetails value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("PerformInsertReplace", value.getPerformInsertReplace());
            encoder.writeDataValueArray("UpdateValues", value.getUpdateValues());
        }
    }
}
