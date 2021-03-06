/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.client.subscriptions;

import java.util.function.Consumer;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.ExtensionObject;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.builtin.Variant;
import com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.UInteger;
import com.viper.opc.client.opcua.stack.core.types.enumerated.MonitoringMode;
import com.viper.opc.client.opcua.stack.core.types.enumerated.TimestampsToReturn;
import com.viper.opc.client.opcua.stack.core.types.structured.ReadValueId;

import static com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaMonitoredItem implements UaMonitoredItem {

    private final OpcUaClient client;

    private volatile ValueConsumer valueConsumer;
    private volatile EventConsumer eventConsumer;

    private volatile double requestedSamplingInterval = 0.0;
    private volatile UInteger requestedQueueSize = uint(0);

    private volatile StatusCode statusCode;
    private volatile double revisedSamplingInterval = 0.0;
    private volatile UInteger revisedQueueSize = uint(0);
    private volatile ExtensionObject filterResult;
    private volatile MonitoringMode monitoringMode = MonitoringMode.Disabled;
    private volatile ExtensionObject monitoringFilter;
    private volatile boolean discardOldest;
    private volatile TimestampsToReturn timestamps;

    private final UInteger clientHandle;
    private final ReadValueId readValueId;
    private final UInteger monitoredItemId;

    public OpcUaMonitoredItem(
        OpcUaClient client,
        UInteger clientHandle,
        ReadValueId readValueId,
        UInteger monitoredItemId,
        StatusCode statusCode,
        double revisedSamplingInterval,
        UInteger revisedQueueSize,
        ExtensionObject filterResult,
        MonitoringMode monitoringMode,
        ExtensionObject monitoringFilter,
        boolean discardOldest,
        TimestampsToReturn timestamps
    ) {

        this.client = client;
        this.clientHandle = clientHandle;
        this.readValueId = readValueId;
        this.monitoredItemId = monitoredItemId;
        this.statusCode = statusCode;
        this.revisedSamplingInterval = revisedSamplingInterval;
        this.revisedQueueSize = revisedQueueSize;
        this.filterResult = filterResult;
        this.monitoringMode = monitoringMode;
        this.monitoringFilter = monitoringFilter;
        this.discardOldest = discardOldest;
        this.timestamps = timestamps;
    }

    @Override
    public OpcUaClient getClient() {
        return client;
    }

    @Override
    public UInteger getClientHandle() {
        return clientHandle;
    }

    @Override
    public ReadValueId getReadValueId() {
        return readValueId;
    }

    @Override
    public UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

    @Override
    public StatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    public double getRequestedSamplingInterval() {
        return requestedSamplingInterval;
    }

    @Override
    public double getRevisedSamplingInterval() {
        return revisedSamplingInterval;
    }

    @Override
    public UInteger getRequestedQueueSize() {
        return requestedQueueSize;
    }

    @Override
    public UInteger getRevisedQueueSize() {
        return revisedQueueSize;
    }

    @Override
    public ExtensionObject getFilterResult() {
        return filterResult;
    }

    @Override
    public MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    @Override
    public ExtensionObject getMonitoringFilter() {
        return monitoringFilter;
    }

    @Override
    public boolean getDiscardOldest() {
        return discardOldest;
    }

    @Override
    public TimestampsToReturn getTimestamps() {
        return timestamps;
    }

    @Override
    public void setValueConsumer(Consumer<DataValue> consumer) {
        this.valueConsumer = (item, value) -> consumer.accept(value);
    }

    @Override
    public void setValueConsumer(ValueConsumer valueConsumer) {
        this.valueConsumer = valueConsumer;
    }

    @Override
    public void setEventConsumer(Consumer<Variant[]> consumer) {
        this.eventConsumer = (item, values) -> consumer.accept(values);
    }

    @Override
    public void setEventConsumer(EventConsumer eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    void setFilterResult(ExtensionObject filterResult) {
        this.filterResult = filterResult;
    }

    void setRequestedFilter(ExtensionObject filter) {
        this.monitoringFilter = filter;
    }

    void setRequestedSamplingInterval(double requestedSamplingInterval) {
        this.requestedSamplingInterval = requestedSamplingInterval;
    }

    void setRevisedSamplingInterval(double revisedSamplingInterval) {
        this.revisedSamplingInterval = revisedSamplingInterval;
    }

    void setRequestedQueueSize(UInteger requestedQueueSize) {
        this.requestedQueueSize = requestedQueueSize;
    }

    void setRevisedQueueSize(UInteger revisedQueueSize) {
        this.revisedQueueSize = revisedQueueSize;
    }

    void setMonitoringMode(MonitoringMode monitoringMode) {
        this.monitoringMode = monitoringMode;
    }

    void setTimestamps(TimestampsToReturn timestamps) {
        this.timestamps = timestamps;
    }

    void setDiscardOldest(boolean discardOldest) {
        this.discardOldest = discardOldest;
    }

    void onValueArrived(DataValue value) {
        ValueConsumer c = valueConsumer;
        if (c != null) c.onValueArrived(this, value);
    }

    void onEventArrived(Variant[] values) {
        EventConsumer c = eventConsumer;
        if (c != null) c.onEventArrived(this, values);
    }

}
