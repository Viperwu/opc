/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core;

import java.util.Optional;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;

import static com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public enum AttributeId {

    NodeId(1),
    NodeClass(2),
    BrowseName(3),
    DisplayName(4),
    Description(5),
    WriteMask(6),
    UserWriteMask(7),
    IsAbstract(8),
    Symmetric(9),
    InverseName(10),
    ContainsNoLoops(11),
    EventNotifier(12),
    Value(13),
    DataType(14),
    ValueRank(15),
    ArrayDimensions(16),
    AccessLevel(17),
    UserAccessLevel(18),
    MinimumSamplingInterval(19),
    Historizing(20),
    Executable(21),
    UserExecutable(22);

    public static final ImmutableSet<AttributeId> BASE_ATTRIBUTES = ImmutableSet.copyOf(
        ImmutableSet.of(
            NodeId, NodeClass, BrowseName, DisplayName, Description, WriteMask, UserWriteMask)
    );

    public static final ImmutableSet<AttributeId> DATA_TYPE_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(IsAbstract))
    );

    public static final ImmutableSet<AttributeId> METHOD_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(Executable, UserExecutable))
    );

    public static final ImmutableSet<AttributeId> OBJECT_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(EventNotifier))
    );

    public static final ImmutableSet<AttributeId> OBJECT_TYPE_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(IsAbstract))
    );

    public static final ImmutableSet<AttributeId> REFERENCE_TYPE_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(IsAbstract, Symmetric, InverseName))
    );

    public static final ImmutableSet<AttributeId> VARIABLE_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(Value, DataType, ValueRank, ArrayDimensions,
                AccessLevel, UserAccessLevel, MinimumSamplingInterval, Historizing))
    );

    public static final ImmutableSet<AttributeId> VARIABLE_TYPE_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(Value, DataType, ValueRank, ArrayDimensions, IsAbstract))
    );

    public static final ImmutableSet<AttributeId> VIEW_ATTRIBUTES = ImmutableSet.copyOf(
        Sets.union(
            BASE_ATTRIBUTES,
            ImmutableSet.of(ContainsNoLoops, EventNotifier))
    );

    private final int id;

    AttributeId(int id) {
        this.id = id;
    }

    public final int id() {
        return id;
    }

    public final UInteger uid() {
        return uint(id);
    }

    public static Optional<AttributeId> from(UInteger attributeId) {
        return from(attributeId.intValue());
    }

    public static Optional<AttributeId> from(int attributeId) {
        if (attributeId > 0 && attributeId <= values().length) {
            return Optional.of(values()[attributeId - 1]);
        } else {
            return Optional.empty();
        }
    }

    public boolean isEqual(UInteger attributeId) {
        return attributeId != null && isEqual(attributeId.intValue());
    }

    public boolean isEqual(int attributeId) {
        return id == attributeId;
    }

    /**
     * @param attributeId the id to test for validity.
     * @return {@code true} if {@code attributeId} is valid.
     */
    public static boolean isValid(UInteger attributeId) {
        return from(attributeId).isPresent();
    }

    /**
     * @param attributeId the id to test for validity.
     * @return {@code true} if {@code attributeId} is valid.
     */
    public static boolean isValid(int attributeId) {
        return from(attributeId).isPresent();
    }

    /**
     * Get the set of valid attributes for a {@link com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass}.
     *
     * @param nodeClass the {@link com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass}.
     * @return the set of valid attributes for {@code nodeClass}.
     */
    public static ImmutableSet<AttributeId> getAttributes(
        com.viper.opc.client.opcua.stack.core.types.enumerated.NodeClass nodeClass) {

        //@formatter:off
        switch (nodeClass) {
            case Object:        return OBJECT_ATTRIBUTES;
            case Variable:      return VARIABLE_ATTRIBUTES;
            case Method:        return METHOD_ATTRIBUTES;
            case ObjectType:    return OBJECT_TYPE_ATTRIBUTES;
            case VariableType:  return VARIABLE_TYPE_ATTRIBUTES;
            case ReferenceType: return REFERENCE_TYPE_ATTRIBUTES;
            case DataType:      return DATA_TYPE_ATTRIBUTES;
            case View:          return VIEW_ATTRIBUTES;
            default:            return ImmutableSet.of();
        }
        //@formatter:on
    }

}
