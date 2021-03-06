package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.StateVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.TransitionVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.TransitionEventType;
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

public class TransitionEventTypeNode extends BaseEventTypeNode implements TransitionEventType {
    public TransitionEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                   QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                   UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public LocalizedText getTransition() throws UaException {
        TransitionVariableTypeNode node = getTransitionNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransition(LocalizedText transition) throws UaException {
        TransitionVariableTypeNode node = getTransitionNode();
        node.setValue(new Variant(transition));
    }

    @Override
    public LocalizedText readTransition() throws UaException {
        try {
            return readTransitionAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeTransition(LocalizedText transition) throws UaException {
        try {
            writeTransitionAsync(transition).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readTransitionAsync() {
        return getTransitionNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeTransitionAsync(LocalizedText transition) {
        DataValue value = DataValue.valueOnly(new Variant(transition));
        return getTransitionNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TransitionVariableTypeNode getTransitionNode() throws UaException {
        try {
            return getTransitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TransitionVariableTypeNode> getTransitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Transition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TransitionVariableTypeNode) node);
    }

    @Override
    public LocalizedText getFromState() throws UaException {
        StateVariableTypeNode node = getFromStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setFromState(LocalizedText fromState) throws UaException {
        StateVariableTypeNode node = getFromStateNode();
        node.setValue(new Variant(fromState));
    }

    @Override
    public LocalizedText readFromState() throws UaException {
        try {
            return readFromStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeFromState(LocalizedText fromState) throws UaException {
        try {
            writeFromStateAsync(fromState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readFromStateAsync() {
        return getFromStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeFromStateAsync(LocalizedText fromState) {
        DataValue value = DataValue.valueOnly(new Variant(fromState));
        return getFromStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public StateVariableTypeNode getFromStateNode() throws UaException {
        try {
            return getFromStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends StateVariableTypeNode> getFromStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "FromState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (StateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getToState() throws UaException {
        StateVariableTypeNode node = getToStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setToState(LocalizedText toState) throws UaException {
        StateVariableTypeNode node = getToStateNode();
        node.setValue(new Variant(toState));
    }

    @Override
    public LocalizedText readToState() throws UaException {
        try {
            return readToStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeToState(LocalizedText toState) throws UaException {
        try {
            writeToStateAsync(toState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readToStateAsync() {
        return getToStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeToStateAsync(LocalizedText toState) {
        DataValue value = DataValue.valueOnly(new Variant(toState));
        return getToStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public StateVariableTypeNode getToStateNode() throws UaException {
        try {
            return getToStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends StateVariableTypeNode> getToStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ToState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (StateVariableTypeNode) node);
    }
}
