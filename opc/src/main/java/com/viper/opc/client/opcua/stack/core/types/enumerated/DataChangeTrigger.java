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

public enum DataChangeTrigger implements UaEnumeration {
    Status(0),

    StatusValue(1),

    StatusValueTimestamp(2);

    private final int value;

    DataChangeTrigger(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static DataChangeTrigger from(int value) {
        switch (value) {
            case 0:
                return Status;
            case 1:
                return StatusValue;
            case 2:
                return StatusValueTimestamp;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=717");
    }

    public static class Codec extends GenericDataTypeCodec<DataChangeTrigger> {
        @Override
        public Class<DataChangeTrigger> getType() {
            return DataChangeTrigger.class;
        }

        @Override
        public DataChangeTrigger decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, DataChangeTrigger.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataChangeTrigger value) {
            encoder.writeEnum(null, value);
        }
    }
}
