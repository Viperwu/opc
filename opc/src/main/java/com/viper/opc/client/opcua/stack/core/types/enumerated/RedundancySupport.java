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

public enum RedundancySupport implements UaEnumeration {
    None(0),

    Cold(1),

    Warm(2),

    Hot(3),

    Transparent(4),

    HotAndMirrored(5);

    private final int value;

    RedundancySupport(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static RedundancySupport from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return Cold;
            case 2:
                return Warm;
            case 3:
                return Hot;
            case 4:
                return Transparent;
            case 5:
                return HotAndMirrored;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=851");
    }

    public static class Codec extends GenericDataTypeCodec<RedundancySupport> {
        @Override
        public Class<RedundancySupport> getType() {
            return RedundancySupport.class;
        }

        @Override
        public RedundancySupport decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, RedundancySupport.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RedundancySupport value) {
            encoder.writeEnum(null, value);
        }
    }
}
