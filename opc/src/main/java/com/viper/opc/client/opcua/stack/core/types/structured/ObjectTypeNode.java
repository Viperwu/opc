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
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.LocalizedText;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ObjectTypeNode extends TypeNode implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=264");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=266");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=265");

    private final Boolean isAbstract;

    public ObjectTypeNode(NodeId nodeId, NodeClass nodeClass, QualifiedName browseName,
                          LocalizedText displayName, LocalizedText description, UInteger writeMask,
                          UInteger userWriteMask, ReferenceNode[] references, Boolean isAbstract) {
        super(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references);
        this.isAbstract = isAbstract;
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

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public static final class Codec extends GenericDataTypeCodec<ObjectTypeNode> {
        @Override
        public Class<ObjectTypeNode> getType() {
            return ObjectTypeNode.class;
        }

        @Override
        public ObjectTypeNode decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeClass nodeClass = decoder.readEnum("NodeClass", NodeClass.class);
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            ReferenceNode[] references = (ReferenceNode[]) decoder.readStructArray("References", ReferenceNode.TYPE_ID);
            Boolean isAbstract = decoder.readBoolean("IsAbstract");
            return new ObjectTypeNode(nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, references, isAbstract);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ObjectTypeNode value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeStructArray("References", value.getReferences(), ReferenceNode.TYPE_ID);
            encoder.writeBoolean("IsAbstract", value.getIsAbstract());
        }
    }
}
