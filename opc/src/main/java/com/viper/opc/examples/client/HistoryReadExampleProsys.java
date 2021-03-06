/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.examples.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.stack.core.types.builtin.ByteString;
import com.viper.opc.client.opcua.stack.core.types.builtin.DataValue;
import com.viper.opc.client.opcua.stack.core.types.builtin.DateTime;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.enumerated.TimestampsToReturn;
import com.viper.opc.client.opcua.stack.core.types.structured.HistoryData;
import com.viper.opc.client.opcua.stack.core.types.structured.HistoryReadDetails;
import com.viper.opc.client.opcua.stack.core.types.structured.HistoryReadResponse;
import com.viper.opc.client.opcua.stack.core.types.structured.HistoryReadResult;
import com.viper.opc.client.opcua.stack.core.types.structured.HistoryReadValueId;
import com.viper.opc.client.opcua.stack.core.types.structured.ReadRawModifiedDetails;

import static com.viper.opc.client.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static com.viper.opc.client.opcua.stack.core.util.ConversionUtil.l;

public class HistoryReadExampleProsys implements ClientExample {

    public static void main(String[] args) throws Exception {
        HistoryReadExampleProsys example = new HistoryReadExampleProsys();

        new ClientExampleRunner(example, false).run();
    }

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connect().get();

        HistoryReadDetails historyReadDetails = new ReadRawModifiedDetails(
            false,
            DateTime.MIN_VALUE,
            DateTime.now(),
            uint(0),
            true
        );

        HistoryReadValueId historyReadValueId = new HistoryReadValueId(
            new NodeId(3, "Counter"),
            null,
            QualifiedName.NULL_VALUE,
            ByteString.NULL_VALUE
        );

        List<HistoryReadValueId> nodesToRead = new ArrayList<>();
        nodesToRead.add(historyReadValueId);

        HistoryReadResponse historyReadResponse = client.historyRead(
            historyReadDetails,
            TimestampsToReturn.Both,
            false,
            nodesToRead
        ).get();


        HistoryReadResult[] historyReadResults = historyReadResponse.getResults();

        if (historyReadResults != null) {
            HistoryReadResult historyReadResult = historyReadResults[0];
            StatusCode statusCode = historyReadResult.getStatusCode();

            if (statusCode.isGood()) {
                HistoryData historyData = (HistoryData) historyReadResult.getHistoryData().decode(
                    client.getStaticSerializationContext()
                );

                List<DataValue> dataValues = l(historyData.getDataValues());

                dataValues.forEach(v -> System.out.println("value=" + v));
            } else {
                System.out.println("History read failed: " + statusCode);
            }
        }

        future.complete(client);
    }

    @Override
    public String getEndpointUrl() {
        return "opc.tcp://localhost:53530/OPCUA/SimulationServer";
    }

}
