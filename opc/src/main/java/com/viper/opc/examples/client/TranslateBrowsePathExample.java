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

import java.util.concurrent.CompletableFuture;

import com.viper.opc.client.opcua.sdk.client.OpcUaClient;
import com.viper.opc.client.opcua.stack.core.Identifiers;
import com.viper.opc.client.opcua.stack.core.types.builtin.QualifiedName;
import com.viper.opc.client.opcua.stack.core.types.builtin.StatusCode;
import com.viper.opc.client.opcua.stack.core.types.structured.BrowsePath;
import com.viper.opc.client.opcua.stack.core.types.structured.BrowsePathResult;
import com.viper.opc.client.opcua.stack.core.types.structured.RelativePath;
import com.viper.opc.client.opcua.stack.core.types.structured.RelativePathElement;
import com.viper.opc.client.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static com.viper.opc.client.opcua.stack.core.util.ConversionUtil.l;

public class TranslateBrowsePathExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        TranslateBrowsePathExample example = new TranslateBrowsePathExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        TranslateBrowsePathsToNodeIdsResponse response = client.translateBrowsePaths(newArrayList(new BrowsePath(
            Identifiers.ObjectsFolder,
            new RelativePath(new RelativePathElement[]{
                new RelativePathElement(
                    Identifiers.HierarchicalReferences,
                    false,
                    true,
                    new QualifiedName(2, "HelloWorld")
                ),
                new RelativePathElement(
                    Identifiers.HierarchicalReferences,
                    false,
                    true,
                    new QualifiedName(2, "ScalarTypes")
                ),
                new RelativePathElement(
                    Identifiers.HierarchicalReferences,
                    false,
                    true,
                    new QualifiedName(2, "UInt64")
                )
            })
        ))).get();

        BrowsePathResult result = l(response.getResults()).get(0);
        StatusCode statusCode = result.getStatusCode();
        logger.info("Status={}", statusCode);

        l(result.getTargets()).forEach(target -> logger.info("TargetId={}", target.getTargetId()));

        future.complete(client);
    }

}
