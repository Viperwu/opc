/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.client.api.subscriptions;

import java.util.function.Consumer;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExtensionObject;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.builtin.Variant;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.enumerated.MonitoringMode;
import com.viper.opc.client.opcua.stack.core.types.enumerated.TimestampsToReturn;
import com.viper.opc.client.opcua.stack.core.types.structured.ReadValueId;

public interface UaMonitoredItem {

    /**
     * Get the {@link OpcUaClient} that created this monitored item.
     *
     * @return the {@link OpcUaClient} that created this monitored item.
     */
    OpcUaClient getClient();

    /**
     * Get the client-assigned id.
     * <p>
     * This handle is used in the subscription to match incoming values to the corresponding monitored item.
     *
     * @return the client-assigned id.
     */
    UInteger getClientHandle();

    /**
     * Get the server-assigned id.
     *
     * @return the server-assigned id.
     */
    UInteger getMonitoredItemId();

    /**
     * Get the {@link ReadValueId}.
     *
     * @return the {@link ReadValueId}.
     */
    ReadValueId getReadValueId();

    /**
     * Get the {@link StatusCode} of the last operation.
     *
     * @return the {@link StatusCode} of the last operation.
     */
    StatusCode getStatusCode();

    /**
     * Get the last requested sampling interval.
     *
     * @return the last requested sampling interval.
     */
    double getRequestedSamplingInterval();

    /**
     * Get the revised sampling interval.
     *
     * @return the revised sampling interval.
     */
    double getRevisedSamplingInterval();

    /**
     * Get the last requested queue size.
     *
     * @return the last requested queue size.
     */
    UInteger getRequestedQueueSize();

    /**
     * Get the revised queue size.
     *
     * @return the revised queue size.
     */
    UInteger getRevisedQueueSize();

    /**
     * Get the filter result {@link ExtensionObject}.
     *
     * @return the filter result {@link ExtensionObject}.
     */
    ExtensionObject getFilterResult();

    /**
     * Get the {@link MonitoringMode}.
     *
     * @return the {@link MonitoringMode}.
     */
    MonitoringMode getMonitoringMode();

    /**
     * Get the filter requested when the item was created.
     * <p>
     * May be null if no filter was requested.
     *
     * @return the filter requested when the item was created. May be null if no filter was requested.
     */
    ExtensionObject getMonitoringFilter();

    /**
     * Get the discard policy.
     * <p>
     * {@code true} if oldest are discarded when the queue capacity is exceeded.
     *
     * @return the discard policy.
     */
    boolean getDiscardOldest();

    /**
     * Get the {@link TimestampsToReturn} requested when the item was created.
     *
     * @return the {@link TimestampsToReturn} requested when the item was created.
     */
    TimestampsToReturn getTimestamps();

    /**
     * Set the {@link Consumer} that will receive values as they arrive from the server.
     *
     * @param valueConsumer the {@link Consumer} that will receive values as they arrive from the server.
     */
    void setValueConsumer(Consumer<DataValue> valueConsumer);

    /**
     * Set a {@link ValueConsumer} that will receive values as they arrive from the server.
     * <p>
     * The {@link UaMonitoredItem} in the consumer will be this item.
     *
     * @param valueConsumer the {@link ValueConsumer} that will receive values as they arrive from the server.
     */
    void setValueConsumer(ValueConsumer valueConsumer);

    /**
     * Set the {@link Consumer} that will receive events as they arrive from the server.
     *
     * @param eventConsumer the {@link Consumer} that will receive events as they arrive from the server.
     */
    void setEventConsumer(Consumer<Variant[]> eventConsumer);

    /**
     * Set the {@link EventConsumer} that will receive events as they arrive from the server.
     *
     * @param eventConsumer the {@link EventConsumer} that will receive events as they arrive from the server.
     */
    void setEventConsumer(EventConsumer eventConsumer);

    interface ValueConsumer {

        /**
         * A new value has arrived for the {@link UaMonitoredItem} {@code item}.
         *
         * @param item  the {@link UaMonitoredItem} this value is for.
         * @param value the new {@link DataValue}.
         */
        void onValueArrived(UaMonitoredItem item, DataValue value);

    }

    interface EventConsumer {

        /**
         * A new event has arrived for the {@link UaMonitoredItem} {@code item}.
         *
         * @param item        the {@link UaMonitoredItem} this event is for.
         * @param eventValues the event values.
         */
        void onEventArrived(UaMonitoredItem item, Variant[] eventValues);

    }

}
