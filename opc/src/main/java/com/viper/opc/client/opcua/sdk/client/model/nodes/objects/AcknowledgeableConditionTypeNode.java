package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.AcknowledgeableConditionType;
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

public class AcknowledgeableConditionTypeNode extends ConditionTypeNode implements AcknowledgeableConditionType {
    public AcknowledgeableConditionTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                            UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public LocalizedText getEnabledState() throws UaException {
        TwoStateVariableTypeNode node = getEnabledStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setEnabledState(LocalizedText enabledState) throws UaException {
        TwoStateVariableTypeNode node = getEnabledStateNode();
        node.setValue(new Variant(enabledState));
    }

    @Override
    public LocalizedText readEnabledState() throws UaException {
        try {
            return readEnabledStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeEnabledState(LocalizedText enabledState) throws UaException {
        try {
            writeEnabledStateAsync(enabledState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readEnabledStateAsync() {
        return getEnabledStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeEnabledStateAsync(LocalizedText enabledState) {
        DataValue value = DataValue.valueOnly(new Variant(enabledState));
        return getEnabledStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getEnabledStateNode() throws UaException {
        try {
            return getEnabledStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getEnabledStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "EnabledState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getAckedState() throws UaException {
        TwoStateVariableTypeNode node = getAckedStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setAckedState(LocalizedText ackedState) throws UaException {
        TwoStateVariableTypeNode node = getAckedStateNode();
        node.setValue(new Variant(ackedState));
    }

    @Override
    public LocalizedText readAckedState() throws UaException {
        try {
            return readAckedStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeAckedState(LocalizedText ackedState) throws UaException {
        try {
            writeAckedStateAsync(ackedState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readAckedStateAsync() {
        return getAckedStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeAckedStateAsync(LocalizedText ackedState) {
        DataValue value = DataValue.valueOnly(new Variant(ackedState));
        return getAckedStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getAckedStateNode() throws UaException {
        try {
            return getAckedStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getAckedStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "AckedState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    @Override
    public LocalizedText getConfirmedState() throws UaException {
        TwoStateVariableTypeNode node = getConfirmedStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setConfirmedState(LocalizedText confirmedState) throws UaException {
        TwoStateVariableTypeNode node = getConfirmedStateNode();
        node.setValue(new Variant(confirmedState));
    }

    @Override
    public LocalizedText readConfirmedState() throws UaException {
        try {
            return readConfirmedStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeConfirmedState(LocalizedText confirmedState) throws UaException {
        try {
            writeConfirmedStateAsync(confirmedState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readConfirmedStateAsync() {
        return getConfirmedStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeConfirmedStateAsync(LocalizedText confirmedState) {
        DataValue value = DataValue.valueOnly(new Variant(confirmedState));
        return getConfirmedStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getConfirmedStateNode() throws UaException {
        try {
            return getConfirmedStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getConfirmedStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ConfirmedState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }
}
