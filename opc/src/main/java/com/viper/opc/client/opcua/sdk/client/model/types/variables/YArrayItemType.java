package com.viper.opc.client.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import com.viper.opc.client.opcua.sdk.core.QualifiedProperty;
import com.viper.opc.client.opcua.sdk.core.ValueRanks;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.structured.AxisInformation;

public interface YArrayItemType extends ArrayItemType {
    QualifiedProperty<AxisInformation> X_AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "XAxisDefinition",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12079"),
        ValueRanks.Scalar,
        AxisInformation.class
    );

    /**
     * Get the local value of the XAxisDefinition Node.
     * <p>
     * The returned value is the last seen; it is not read live from the server.
     *
     * @return the local value of the XAxisDefinition Node.
     * @throws UaException if an error occurs creating or getting the XAxisDefinition Node.
     */
    AxisInformation getXAxisDefinition() throws UaException;

    /**
     * Set the local value of the XAxisDefinition Node.
     * <p>
     * The value is only updated locally; it is not written to the server.
     *
     * @param xAxisDefinition the local value to set for the XAxisDefinition Node.
     * @throws UaException if an error occurs creating or getting the XAxisDefinition Node.
     */
    void setXAxisDefinition(AxisInformation xAxisDefinition) throws UaException;

    /**
     * Read the value of the XAxisDefinition Node from the server and update the local value if the
     * operation succeeds.
     *
     * @return the {@link AxisInformation} value read from the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    AxisInformation readXAxisDefinition() throws UaException;

    /**
     * Write a new value for the XAxisDefinition Node to the server and update the local value if
     * the operation succeeds.
     *
     * @param xAxisDefinition the {@link AxisInformation} value to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    void writeXAxisDefinition(AxisInformation xAxisDefinition) throws UaException;

    /**
     * An asynchronous implementation of {@link #readXAxisDefinition()}.
     *
     * @return a CompletableFuture that completes successfully with the property value or completes
     * exceptionally if an operation- or service-level error occurs.
     */
    CompletableFuture<? extends AxisInformation> readXAxisDefinitionAsync();

    /**
     * An asynchronous implementation of {@link #writeXAxisDefinition(AxisInformation)}.
     *
     * @return a CompletableFuture that completes successfully with the operation result or
     * completes exceptionally if a service-level error occurs.
     */
    CompletableFuture<StatusCode> writeXAxisDefinitionAsync(AxisInformation xAxisDefinition);

    /**
     * Get the XAxisDefinition {@link PropertyType} Node, or {@code null} if it does not exist.
     * <p>
     * The Node is created when first accessed and cached for subsequent calls.
     *
     * @return the XAxisDefinition {@link PropertyType} Node, or {@code null} if it does not exist.
     * @throws UaException if an error occurs creating or getting the Node.
     */
    PropertyType getXAxisDefinitionNode() throws UaException;

    /**
     * Asynchronous implementation of {@link #getXAxisDefinitionNode()}.
     *
     * @return a CompletableFuture that completes successfully with the
     * ? extends PropertyType Node or completes exceptionally if an error occurs
     * creating or getting the Node.
     */
    CompletableFuture<? extends PropertyType> getXAxisDefinitionNodeAsync();
}
