/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.core;

import java.util.Objects;
import java.util.Optional;

import com.google.common.base.MoreObjects;
import com.viper.opc.client.opcua.stack.core.NamespaceTable;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UShort;
import org.jetbrains.annotations.Nullable;

import static com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class QualifiedProperty<T> {

    private final String namespaceUri;
    private final String browseName;
    private final ExpandedNodeId dataType;
    private final Integer valueRank;
    private final Class<T> javaType;

    public QualifiedProperty(
        String namespaceUri,
        String browseName,
        ExpandedNodeId dataType,
        Integer valueRank,
        Class<T> javaType
    ) {

        this.namespaceUri = namespaceUri;
        this.browseName = browseName;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.javaType = javaType;
    }

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public String getBrowseName() {
        return browseName;
    }

    public ExpandedNodeId getDataType() {
        return dataType;
    }

    public Integer getValueRank() {
        return valueRank;
    }

    public Class<T> getJavaType() {
        return javaType;
    }

    @Nullable
    public UInteger[] getArrayDimensions() {
        int valueRank = getValueRank();

        if (valueRank <= 0) {
            return null;
        } else {
            UInteger[] arrayDimensions = new UInteger[valueRank];
            for (int i = 0; i < valueRank; i++) {
                arrayDimensions[i] = uint(0);
            }
            return arrayDimensions;
        }
    }

    /**
     * Get the {@link QualifiedName} of this property.
     * <p>
     * The {@code namespaceUri} must be present in {@code namespaceTable} or an empty {@link Optional} will be returned.
     *
     * @param namespaceTable the {@link NamespaceTable} containing the namespace URIs and their indices.
     * @return the {@link QualifiedName} of this property, or an empty {@link Optional} if the namespace URI is not
     * present in {@code namespaceTable}.
     */
    public Optional<QualifiedName> getQualifiedName(NamespaceTable namespaceTable) {
        UShort namespaceIndex = namespaceTable.getIndex(namespaceUri);

        if (namespaceIndex != null) {
            return Optional.of(new QualifiedName(namespaceIndex, browseName));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("namespaceUri", namespaceUri)
            .add("browseName", browseName)
            .add("dataType", dataType)
            .add("valueRank", valueRank)
            .add("javaType", javaType)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualifiedProperty<?> that = (QualifiedProperty<?>) o;

        return Objects.equals(namespaceUri, that.namespaceUri) &&
            Objects.equals(browseName, that.browseName) &&
            Objects.equals(dataType, that.dataType) &&
            Objects.equals(valueRank, that.valueRank) &&
            Objects.equals(javaType, that.javaType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespaceUri, browseName, dataType, valueRank, javaType);
    }

}
