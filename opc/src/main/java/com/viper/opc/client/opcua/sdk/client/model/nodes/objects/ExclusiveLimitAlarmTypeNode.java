package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.ExclusiveLimitAlarmType;
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

public class ExclusiveLimitAlarmTypeNode extends LimitAlarmTypeNode implements ExclusiveLimitAlarmType {
    public ExclusiveLimitAlarmTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public LocalizedText getActiveState() throws UaException {
        TwoStateVariableTypeNode node = getActiveStateNode();
        return (LocalizedText) node.getValue().getValue().getValue();
    }

    @Override
    public void setActiveState(LocalizedText activeState) throws UaException {
        TwoStateVariableTypeNode node = getActiveStateNode();
        node.setValue(new Variant(activeState));
    }

    @Override
    public LocalizedText readActiveState() throws UaException {
        try {
            return readActiveStateAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeActiveState(LocalizedText activeState) throws UaException {
        try {
            writeActiveStateAsync(activeState).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends LocalizedText> readActiveStateAsync() {
        return getActiveStateNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (LocalizedText) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeActiveStateAsync(LocalizedText activeState) {
        DataValue value = DataValue.valueOnly(new Variant(activeState));
        return getActiveStateNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public TwoStateVariableTypeNode getActiveStateNode() throws UaException {
        try {
            return getActiveStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends TwoStateVariableTypeNode> getActiveStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ActiveState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (TwoStateVariableTypeNode) node);
    }

    public ExclusiveLimitStateMachineTypeNode getLimitStateNode() throws UaException {
        try {
            return getLimitStateNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends ExclusiveLimitStateMachineTypeNode> getLimitStateNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "LimitState", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (ExclusiveLimitStateMachineTypeNode) node);
    }
}
