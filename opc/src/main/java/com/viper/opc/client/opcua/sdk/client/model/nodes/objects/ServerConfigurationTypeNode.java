package com.viper.opc.client.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.types.objects.ServerConfigurationType;
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

public class ServerConfigurationTypeNode extends BaseObjectTypeNode implements ServerConfigurationType {
    public ServerConfigurationTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                                       QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                       UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public String[] getServerCapabilities() throws UaException {
        PropertyTypeNode node = getServerCapabilitiesNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setServerCapabilities(String[] serverCapabilities) throws UaException {
        PropertyTypeNode node = getServerCapabilitiesNode();
        node.setValue(new Variant(serverCapabilities));
    }

    @Override
    public String[] readServerCapabilities() throws UaException {
        try {
            return readServerCapabilitiesAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeServerCapabilities(String[] serverCapabilities) throws UaException {
        try {
            writeServerCapabilitiesAsync(serverCapabilities).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readServerCapabilitiesAsync() {
        return getServerCapabilitiesNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeServerCapabilitiesAsync(String[] serverCapabilities) {
        DataValue value = DataValue.valueOnly(new Variant(serverCapabilities));
        return getServerCapabilitiesNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getServerCapabilitiesNode() throws UaException {
        try {
            return getServerCapabilitiesNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getServerCapabilitiesNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "ServerCapabilities", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public String[] getSupportedPrivateKeyFormats() throws UaException {
        PropertyTypeNode node = getSupportedPrivateKeyFormatsNode();
        return (String[]) node.getValue().getValue().getValue();
    }

    @Override
    public void setSupportedPrivateKeyFormats(String[] supportedPrivateKeyFormats) throws
        UaException {
        PropertyTypeNode node = getSupportedPrivateKeyFormatsNode();
        node.setValue(new Variant(supportedPrivateKeyFormats));
    }

    @Override
    public String[] readSupportedPrivateKeyFormats() throws UaException {
        try {
            return readSupportedPrivateKeyFormatsAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeSupportedPrivateKeyFormats(String[] supportedPrivateKeyFormats) throws
        UaException {
        try {
            writeSupportedPrivateKeyFormatsAsync(supportedPrivateKeyFormats).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends String[]> readSupportedPrivateKeyFormatsAsync() {
        return getSupportedPrivateKeyFormatsNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (String[]) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeSupportedPrivateKeyFormatsAsync(
        String[] supportedPrivateKeyFormats) {
        DataValue value = DataValue.valueOnly(new Variant(supportedPrivateKeyFormats));
        return getSupportedPrivateKeyFormatsNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getSupportedPrivateKeyFormatsNode() throws UaException {
        try {
            return getSupportedPrivateKeyFormatsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getSupportedPrivateKeyFormatsNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "SupportedPrivateKeyFormats", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public UInteger getMaxTrustListSize() throws UaException {
        PropertyTypeNode node = getMaxTrustListSizeNode();
        return (UInteger) node.getValue().getValue().getValue();
    }

    @Override
    public void setMaxTrustListSize(UInteger maxTrustListSize) throws UaException {
        PropertyTypeNode node = getMaxTrustListSizeNode();
        node.setValue(new Variant(maxTrustListSize));
    }

    @Override
    public UInteger readMaxTrustListSize() throws UaException {
        try {
            return readMaxTrustListSizeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMaxTrustListSize(UInteger maxTrustListSize) throws UaException {
        try {
            writeMaxTrustListSizeAsync(maxTrustListSize).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends UInteger> readMaxTrustListSizeAsync() {
        return getMaxTrustListSizeNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (UInteger) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMaxTrustListSizeAsync(UInteger maxTrustListSize) {
        DataValue value = DataValue.valueOnly(new Variant(maxTrustListSize));
        return getMaxTrustListSizeNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMaxTrustListSizeNode() throws UaException {
        try {
            return getMaxTrustListSizeNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMaxTrustListSizeNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MaxTrustListSize", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    @Override
    public Boolean getMulticastDnsEnabled() throws UaException {
        PropertyTypeNode node = getMulticastDnsEnabledNode();
        return (Boolean) node.getValue().getValue().getValue();
    }

    @Override
    public void setMulticastDnsEnabled(Boolean multicastDnsEnabled) throws UaException {
        PropertyTypeNode node = getMulticastDnsEnabledNode();
        node.setValue(new Variant(multicastDnsEnabled));
    }

    @Override
    public Boolean readMulticastDnsEnabled() throws UaException {
        try {
            return readMulticastDnsEnabledAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public void writeMulticastDnsEnabled(Boolean multicastDnsEnabled) throws UaException {
        try {
            writeMulticastDnsEnabledAsync(multicastDnsEnabled).get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends Boolean> readMulticastDnsEnabledAsync() {
        return getMulticastDnsEnabledNodeAsync().thenCompose(node -> node.readAttributeAsync(AttributeId.Value)).thenApply(v -> (Boolean) v.getValue().getValue());
    }

    @Override
    public CompletableFuture<StatusCode> writeMulticastDnsEnabledAsync(Boolean multicastDnsEnabled) {
        DataValue value = DataValue.valueOnly(new Variant(multicastDnsEnabled));
        return getMulticastDnsEnabledNodeAsync()
            .thenCompose(node -> node.writeAttributeAsync(AttributeId.Value, value));
    }

    @Override
    public PropertyTypeNode getMulticastDnsEnabledNode() throws UaException {
        try {
            return getMulticastDnsEnabledNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    @Override
    public CompletableFuture<? extends PropertyTypeNode> getMulticastDnsEnabledNodeAsync() {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "MulticastDnsEnabled", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=46"), false);
        return future.thenApply(node -> (PropertyTypeNode) node);
    }

    public CertificateGroupFolderTypeNode getCertificateGroupsNode() throws UaException {
        try {
            return getCertificateGroupsNodeAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e).orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public CompletableFuture<? extends CertificateGroupFolderTypeNode> getCertificateGroupsNodeAsync(
    ) {
        CompletableFuture<UaNode> future = getMemberNodeAsync("http://opcfoundation.org/UA/", "CertificateGroups", ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=47"), false);
        return future.thenApply(node -> (CertificateGroupFolderTypeNode) node);
    }
}
