/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.client.nodes;

import java.util.concurrent.CompletableFuture;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.core.nodes.DataTypeNode;
import com.viper.opc.client.opcua.sdk.core.nodes.DataTypeNodeProperties;
import com.viper.opc.client.opcua.stack.core.AttributeId;
import com.viper.opc.client.opcua.stack.core.UaException;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.LocalizedText;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.builtin.Variant;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass;
import com.viper.opc.client.opcua.stack.core.types.structured.EnumValueType;

import static com.viper.opc.client.opcua.sdk.core.nodes.DataTypeNodeProperties.NodeVersion;

public class UaDataTypeNode extends UaNode implements DataTypeNode {

    private Boolean isAbstract;

    public UaDataTypeNode(
        OpcUaClient client,
        NodeId nodeId,
        NodeClass nodeClass,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        Boolean isAbstract
    ) {

        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask);

        this.isAbstract = isAbstract;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The returned attribute is the most recently seen value; it is not read live from the server.
     *
     * @see #readIsAbstract()
     */
    @Override
    public synchronized Boolean getIsAbstract() {
        return isAbstract;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The attribute is only updated locally; it is not written to the server.
     *
     * @see #writeIsAbstract(Boolean)
     */
    @Override
    public synchronized void setIsAbstract(Boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    /**
     * Read the IsAbstract attribute for this Node from the server and update the local
     * attribute if the operation succeeds.
     *
     * @return the {@link Boolean} read from the server.
     * @throws UaException if a service- or operation-level error occurs.s
     */
    public Boolean readIsAbstract() throws UaException {
        DataValue value = readAttribute(AttributeId.IsAbstract);

        StatusCode statusCode = value.getStatusCode();

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "read IsAbstract failed");
        } else {
            Boolean isAbstract = (Boolean) value.getValue().getValue();
            setIsAbstract(isAbstract);
            return isAbstract;
        }
    }

    /**
     * Write a new IsAbstract attribute for this Node to the server and update the local attribute
     * if the operation succeeds.
     *
     * @param isAbstract the {@link Boolean} to write to the server.
     * @throws UaException if a service- or operation-level error occurs.
     */
    public void writeIsAbstract(Boolean isAbstract) throws UaException {
        DataValue value = DataValue.valueOnly(new Variant(isAbstract));
        StatusCode statusCode = writeAttribute(AttributeId.IsAbstract, value);

        if (statusCode != null && statusCode.isBad()) {
            throw new UaException(statusCode, "write IsAbstract failed");
        } else {
            setIsAbstract(isAbstract);
        }
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#NodeVersion} Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<? extends String> readNodeVersionAsync() {
        return getProperty(DataTypeNodeProperties.NodeVersion);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#EnumStrings} Property, if it exists.
     *
     * @return the value of the EnumStrings Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<? extends LocalizedText[]> readEnumStringsAsync() {
        return getProperty(DataTypeNodeProperties.EnumStrings);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#EnumValues} Property, if it exists.
     *
     * @return the value of the EnumValues Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<? extends EnumValueType[]> readEnumValuesAsync() {
        return getProperty(DataTypeNodeProperties.EnumValues);
    }

    /**
     * Get the value of the {@link DataTypeNodeProperties#OptionSetValues} Property, if it exists.
     *
     * @return the value of the OptionSetValues Property, if it exists.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<? extends LocalizedText[]> readOptionSetValuesAsync() {
        return getProperty(DataTypeNodeProperties.OptionSetValues);
    }

    /**
     * Set the value of the {@link DataTypeNodeProperties#NodeVersion} Property, if it exists.
     *
     * @param nodeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> writeNodeVersionAsync(String nodeVersion) {
        return setProperty(NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the EnumStrings Property, if it exists.
     *
     * @param enumStrings the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> writeEnumStringsAsync(LocalizedText[] enumStrings) {
        return setProperty(DataTypeNodeProperties.EnumStrings, enumStrings);
    }

    /**
     * Set the value of the {@link DataTypeNodeProperties#EnumValues} Property, if it exists.
     *
     * @param enumValues the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> writeEnumValuesAsync(EnumValueType[] enumValues) {
        return setProperty(DataTypeNodeProperties.EnumValues, enumValues);
    }

    /**
     * Set the value of the {@link DataTypeNodeProperties#OptionSetValues} Property, if it exists.
     *
     * @param optionSetValues the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see DataTypeNodeProperties
     */
    public CompletableFuture<StatusCode> writeOptionSetValuesAsync(LocalizedText[] optionSetValues) {
        return setProperty(DataTypeNodeProperties.OptionSetValues, optionSetValues);
    }

    @Override
    protected DataValue getAttributeValue(AttributeId attributeId) {
        switch (attributeId) {
            case IsAbstract:
                return DataValue.valueOnly(new Variant(getIsAbstract()));
            default:
                return super.getAttributeValue(attributeId);
        }
    }

    @Override
    protected void setAttributeValue(AttributeId attributeId, DataValue value) {
        switch (attributeId) {
            case IsAbstract: {
                setIsAbstract((Boolean) value.getValue().getValue());
                break;
            }
            default: {
                super.setAttributeValue(attributeId, value);
            }
        }
    }

}
