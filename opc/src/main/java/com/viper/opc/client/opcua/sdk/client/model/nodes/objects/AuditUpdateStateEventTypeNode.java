package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.AuditUpdateStateEventType;
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

public class AuditUpdateStateEventTypeNode extends AuditUpdateMethodEventTypeNode implements AuditUpdateStateEventType {
    public AuditUpdateStateEventTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Object getOldStateId() throws UaException {
        PropertyTypeNode node = getOldStateIdNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setOldStateId(Object oldStateId) throws UaException {
        PropertyTypeNode node = getOldStateIdNode();
        node.setValue(new Variant(oldStateId));
    }

    @Override
    public Object readOldStateId() throws UaException {
        try {
            return readOldStateIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeOldStateId(Object oldStateId) throws UaException {
        try {
            writeOldStateIdAsync(oldStateId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readOldStateIdAsync() {
        return getOldStateIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeOldStateIdAsync(Object oldStateId) {
        DataValue value = DataValue.valueOnly(new Variant(oldStateId));
        return getOldStateIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getOldStateIdNode() throws UaException {
        try {
            return getOldStateIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getOldStateIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "OldStateId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Object getNewStateId() throws UaException {
        PropertyTypeNode node = getNewStateIdNode();
        return (Object) node.getValue().getValue().getValue();
    }

    @Override
    public void setNewStateId(Object newStateId) throws UaException {
        PropertyTypeNode node = getNewStateIdNode();
        node.setValue(new Variant(newStateId));
    }

    @Override
    public Object readNewStateId() throws UaException {
        try {
            return readNewStateIdAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeNewStateId(Object newStateId) throws UaException {
        try {
            writeNewStateIdAsync(newStateId).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<?> readNewStateIdAsync() {
        return getNewStateIdNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Object) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeNewStateIdAsync(Object newStateId) {
        DataValue value = DataValue.valueOnly(new Variant(newStateId));
        return getNewStateIdNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getNewStateIdNode() throws UaException {
        try {
            return getNewStateIdNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getNewStateIdNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "NewStateId", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }
}
