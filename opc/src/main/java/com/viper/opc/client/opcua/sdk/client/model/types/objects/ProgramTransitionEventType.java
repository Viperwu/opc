package com.viper.opc.client.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import com.viper.opc.client.opcua.sdk.client.model.types.variables.PropertyType;
import com.viper.opc.client.opcua.sdk.core.QualifiedProperty;
import com.viper.opc.client.opcua.sdk.core.ValueRanks;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;

public interface ProgramTransitionEventType extends TransitionEventType {
    QualifiedProperty<Object> INTERMEDIATE_RESULT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IntermediateResult",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    /**
     * Get the local value of the IntermediateResult Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the IntermediateResult Node.
     * @throws UaException if an error occurs creating or getting the IntermediateResult Node.
     */
    Object getIntermediateResult() throws UaException;

    /**
     * Set the local value of the IntermediateResult Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param intermediateResult the local value to set for the IntermediateResult Node.
     * @throws UaException if an error occurs creating or getting the IntermediateResult Node.
     */
    void setIntermediateResult(Object intermediateResult) throws UaException;

    /**
     * Read the value of the IntermediateResult Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link Object} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    Object readIntermediateResult() throws UaException;

    /**
     * Write a new value for the IntermediateResult Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param intermediateResult the {@link Object} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeIntermediateResult(Object intermediateResult) throws UaException;

    /**
     * An asynchronous implementation of {@link #readIntermediateResult()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<?> readIntermediateResultAsync();

    /**
     * An asynchronous implementation of {@link #writeIntermediateResult(Object)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeIntermediateResultAsync(Object intermediateResult);

    /**
     * Get the IntermediateResult {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the IntermediateResult {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getIntermediateResultNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getIntermediateResultNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getIntermediateResultNodeAsync();
}
