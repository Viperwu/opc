package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.ProgramTransitionEventType;
import com.viper.opc.client.opcua.sdk.client.nodes.UaNode;
import com.viper.opc.client.opcua.stack.core.AttributeId;
import com.viper.opc.client.opcua.stack.core.StatusCodes;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.LocalizedText;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.builtin.Variant;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UByte;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass;

public class ProgramTransitionEventTypeNode extends TransitionEventTypeNode implements ProgramTransitionEventType {
    public ProgramTransitionEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Object getIntermediateResult() throws UaException {
        PropertyTypeNode node = getIntermediateResultNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setIntermediateResult(Object intermediateResult) throws UaException {
        PropertyTypeNode node = getIntermediateResultNode();
        node.setValue(new Variant(intermediateResult));
    }

    @Override
    public Object readIntermediateResult() throws UaException {
        try {
            return readIntermediateResultAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeIntermediateResult(Object intermediateResult) throws UaException {
        try {
            writeIntermediateResultAsync(intermediateResult).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readIntermediateResultAsync() {
        return getIntermediateResultNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeIntermediateResultAsync(Object intermediateResult) {
        DataValue value = DataValue.valueOnly(new Variant(intermediateResult));
        return getIntermediateResultNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getIntermediateResultNode() throws UaException {
        try {
            return getIntermediateResultNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getIntermediateResultNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "IntermediateResult", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
