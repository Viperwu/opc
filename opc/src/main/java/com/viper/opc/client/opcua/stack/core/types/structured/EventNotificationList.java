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
import com.viper.opc.client.opcua.stack.core.serialization.SerializationContext;
import com.viper.opc.client.opcua.stack.core.serialization.UaDecoder;
import com.viper.opc.client.opcua.stack.core.serialization.UaEncoder;
import com.viper.opc.client.opcua.stack.core.serialization.UaStructure;
import com.viper.opc.client.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EventNotificationList extends NotificationData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=914");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=916");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=915");

    private final EventFieldList[] events;

    public EventNotificationList(EventFieldList[] events) {
        this.events = events;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public EventFieldList[] getEvents() {
        return events;
    }

    public static final class Codec extends GenericDataTypeCodec<EventNotificationList> {
        @Override
        public Class<EventNotificationList> getType() {
            return EventNotificationList.class;
        }

        @Override
        public EventNotificationList decode(SerializationContext context, UaDecoder decoder) {
            EventFieldList[] events = (EventFieldList[]) decoder.readStructArray("Events", EventFieldList.TYPE_ID);
            return new EventNotificationList(events);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           EventNotificationList value) {
            encoder.writeStructArray("Events", value.getEvents(), EventFieldList.TYPE_ID);
        }
    }
}
