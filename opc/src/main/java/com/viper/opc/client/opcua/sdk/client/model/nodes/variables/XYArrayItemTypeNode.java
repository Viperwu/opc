package com.viper.opc.client.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.types.variables.XYArrayItemType;
import com.viper.opc.client.opcua.sdk.client.nodes.UaNode;
import com.viper.opc.client.opcua.stack.core.AttributeId;
import com.viper.opc.client.opcua.stack.core.StatusCodes;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExtensionObject;
import com.viper.opc.client.opcua.stack.core.types.builtin.LocalizedText;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.builtin.Variant;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UByte;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass;
import com.viper.opc.client.opcua.stack.core.types.structured.AxisInformation;

public class XYArrayItemTypeNode extends ArrayItemTypeNode implements XYArrayItemType {
    public XYArrayItemTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                               UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                               Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                               Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    @Override
    public AxisInformation getXAxisDefinition() throws UaException {
        PropertyTypeNode node = getXAxisDefinitionNode();
        return cast(node.getValue().getValue().getValue(), AxisInformation.class);
    }

    @Override
    public void setXAxisDefinition(AxisInformation xAxisDefinition) throws UaException {
        PropertyTypeNode node = getXAxisDefinitionNode();
        ExtensionObject value = ExtensionObject.encode(client.getStaticSerializationContext(), xAxisDefinition);
        node.setValue(new Variant(value));
    }

    @Override
    public AxisInformation readXAxisDefinition() throws UaException {
        try {
            return readXAxisDefinitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeXAxisDefinition(AxisInformation xAxisDefinition) throws UaException {
        try {
            writeXAxisDefinitionAsync(xAxisDefinition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends AxisInformation> readXAxisDefinitionAsync() {
        return getXAxisDefinitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> cast(v.getValue().getValue(), AxisInformation.class));
    }

    @Override
    public CompletableFuture<StatusCode> writeXAxisDefinitionAsync(AxisInformation xAxisDefinition) {
        ExtensionObject encoded = ExtensionObject.encode(client.getStaticSerializationContext(), xAxisDefinition);
        DataValue value = DataValue.valueOnly(new Variant(encoded));
        return getXAxisDefinitionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getXAxisDefinitionNode() throws UaException {
        try {
            return getXAxisDefinitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getXAxisDefinitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "XAxisDefinition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
