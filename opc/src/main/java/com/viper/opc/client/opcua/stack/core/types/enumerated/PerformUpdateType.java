/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.types.enumerated;

import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;
import com.viper.opc.client.opcua.stack.core.serialization.UaDecoder;
import com.viper.opc.client.opcua.stack.core.serialization.UaEncoder;
import com.viper.opc.client.opcua.stack.core.serialization.UaEnumeration;
import com.viper.opc.client.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.jetbrains.annotations.Nullable;

public enum PerformUpdateType implements UaEnumeration {
    Insert(1),

    Replace(2),

    Update(3),

    Remove(4);

    private final int value;

    PerformUpdateType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static PerformUpdateType from(int value) {
        switch (value) {
            case 1:
                return Insert;
            case 2:
                return Replace;
            case 3:
                return Update;
            case 4:
                return Remove;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11293");
    }

    public static class Codec extends GenericDataTypeCodec<PerformUpdateType> {
        @Override
        public Class<PerformUpdateType> getType() {
            return PerformUpdateType.class;
        }

        @Override
        public PerformUpdateType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, PerformUpdateType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PerformUpdateType value) {
            encoder.writeEnum(null, value);
        }
    }
}
