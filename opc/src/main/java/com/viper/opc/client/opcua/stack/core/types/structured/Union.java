/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import com.viper.opc.client.opcua.stack.core.serialization.UaStructure;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public abstract class Union extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12756");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12758");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12766");

    public Union() {
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }
}
