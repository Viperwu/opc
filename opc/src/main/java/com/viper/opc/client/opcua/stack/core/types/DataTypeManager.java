/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.types;

import com.viper.opc.client.opcua.stack.core.serialization.codecs.DataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import org.jetbrains.annotations.Nullable;

public interface DataTypeManager {

    /**
     * Get a registered {@link DataTypeCodec} by its encoding id.
     *
     * @param encodingId the encoding id.
     * @return the {@link DataTypeCodec} registered for {@code encodingId}.
     */
    @Nullable
    DataTypeCodec getCodec(NodeId encodingId);

    /**
     * Get a registered {@link DataTypeCodec} by its encoding name and datatype id.
     *
     * @param encodingName the {@link QualifiedName} of the datatype encoding.
     * @param dataTypeId   the datatype id.
     * @return the {@link DataTypeCodec} registered for {@code encodingName} and {@code dataTypeId}.
     */
    @Nullable
    DataTypeCodec getCodec(QualifiedName encodingName, NodeId dataTypeId);

    /**
     * Get a registered {@link DataTypeCodec} by its datatype dictionary namespace URI and description.
     *
     * @param namespaceUri the namespace URI of the datatype dictionary the codec is from.
     * @param description  the datatype description in the dictionary.
     * @return the {@link DataTypeCodec} registered for {@code namespaceUri} and {@code description}.
     */
    @Nullable
    DataTypeCodec getCodec(String namespaceUri, String description);

    /**
     * Get a registered {@link DataTypeDictionary} by its namespace URI.
     *
     * @param namespaceUri the namespace URI the dictionary is registered under.
     * @return the {@link DataTypeDictionary} registered under {@code namespaceUri}.
     */
    @Nullable
    DataTypeDictionary<?> getDataTypeDictionary(String namespaceUri);

    /**
     * Register a {@link DataTypeCodec} by its encoding id.
     *
     * @param encodingId the encoding id.
     * @param codec      the {@link DataTypeCodec} to register.
     */
    void registerCodec(NodeId encodingId, DataTypeCodec codec);

    /**
     * Register a {@link DataTypeCodec} by its encoding name and datatype id.
     *
     * @param encodingName the {@link QualifiedName} of the datatype encoding.
     * @param dataTypeId   the datatype id.
     * @param codec        the {@link DataTypeCodec} to register.
     */
    void registerCodec(QualifiedName encodingName, NodeId dataTypeId, DataTypeCodec codec);

    /**
     * Register a {@link DataTypeDictionary} and all the {@link DataTypeCodec}s it contains.
     *
     * @param dataTypeDictionary the {@link DataTypeDictionary} to register.
     */
    void registerTypeDictionary(DataTypeDictionary<?> dataTypeDictionary);

}
