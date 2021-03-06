package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.ProgramTransitionAuditEventType;
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

public class ProgramTransitionAuditEventTypeNode extends AuditUpdateStateEventTypeNode implements ProgramTransitionAuditEventType {
    public ProgramTransitionAuditEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public LocalizedText getTransition() throws UaException {
        FiniteTransitionVariableTypeNode node = getTransitionNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setTransition(LocalizedText transition) throws UaException {
        FiniteTransitionVariableTypeNode node = getTransitionNode();
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
    public FiniteTransitionVariableTypeNode getTransitionNode() throws UaException {
        try {
            return getTransitionNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends FiniteTransitionVariableTypeNode> getTransitionNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "Transition", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (FiniteTransitionVariableTypeNode) node);
    }
}
