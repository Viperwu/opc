package com.viper.opc.client.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import com.viper.opc.client.opcua.sdk.client.model.types.variables.PropertyType;
import com.viper.opc.client.opcua.sdk.core.QualifiedProperty;
import com.viper.opc.client.opcua.sdk.core.ValueRanks;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.enumerated.RedundancySupport;

public interface ServerRedundancyType extends BaseObjectType {
    QualifiedProperty<RedundancySupport> REDUNDANCY_SUPPORT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RedundancySupport",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=851"),
        ValueRanks.Scalar,
        RedundancySupport.class
    );

    /**
     * Get the local value of the RedundancySupport Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the RedundancySupport Node.
     * @throws UaException if an error occurs creating or getting the RedundancySupport Node.
     */
    RedundancySupport getRedundancySupport() throws UaException;

    /**
     * Set the local value of the RedundancySupport Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param redundancySupport the local value to set for the RedundancySupport Node.
     * @throws UaException if an error occurs creating or getting the RedundancySupport Node.
     */
    void setRedundancySupport(RedundancySupport redundancySupport) throws UaException;

    /**
     * Read the value of the RedundancySupport Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link RedundancySupport} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    RedundancySupport readRedundancySupport() throws UaException;

    /**
     * Write a new value for the RedundancySupport Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param redundancySupport the {@link RedundancySupport} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeRedundancySupport(RedundancySupport redundancySupport) throws UaException;

    /**
     * An asynchronous implementation of {@link #readRedundancySupport()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends RedundancySupport> readRedundancySupportAsync();

    /**
     * An asynchronous implementation of {@link #writeRedundancySupport(RedundancySupport)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeRedundancySupportAsync(RedundancySupport redundancySupport);

    /**
     * Get the RedundancySupport {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the RedundancySupport {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getRedundancySupportNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getRedundancySupportNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getRedundancySupportNodeAsync();
}
