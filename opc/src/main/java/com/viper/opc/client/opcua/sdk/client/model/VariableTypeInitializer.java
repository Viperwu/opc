/*
 * Copyright (c) 2020 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.client.model;

import com.viper.opc.client.opcua.sdk.client.VariableTypeManager;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.AnalogItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ArrayItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.BaseDataVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.BaseVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.BuildInfoTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ConditionVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.CubeItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.DataItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.DataTypeDescriptionTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.DataTypeDictionaryTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.DiscreteItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.FiniteStateVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.FiniteTransitionVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ImageItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.MultiStateDiscreteTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.MultiStateValueDiscreteTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.NDimensionArrayItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.OptionSetTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ProgramDiagnosticTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsArrayTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SamplingIntervalDiagnosticsTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ServerDiagnosticsSummaryTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ServerStatusTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.ServerVendorCapabilityTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsArrayTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SessionDiagnosticsVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SessionSecurityDiagnosticsTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.StateVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.SubscriptionDiagnosticsTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.TransitionVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.TwoStateDiscreteTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.TwoStateVariableTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.XYArrayItemTypeNode;
import com.viper.opc.client.opcua.sdk.client.model.nodes.variables.YArrayItemTypeNode;
import com.viper.opc.client.opcua.stack.core.NamespaceTable;
import com.viper.opc.client.opcua.stack.core.types.builtin.NodeId;

public class VariableTypeInitializer {
    public static void initialize(NamespaceTable namespaceTable,
                                  VariableTypeManager variableTypeManager) {
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=62")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseVariableTypeNode.class,
            BaseVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=63")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BaseDataVariableTypeNode.class,
            BaseDataVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=68")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            PropertyTypeNode.class,
            PropertyTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=69")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataTypeDescriptionTypeNode.class,
            DataTypeDescriptionTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=72")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataTypeDictionaryTypeNode.class,
            DataTypeDictionaryTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=8995")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TwoStateVariableTypeNode.class,
            TwoStateVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=9002")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ConditionVariableTypeNode.class,
            ConditionVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2137")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerVendorCapabilityTypeNode.class,
            ServerVendorCapabilityTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2138")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerStatusTypeNode.class,
            ServerStatusTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2150")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ServerDiagnosticsSummaryTypeNode.class,
            ServerDiagnosticsSummaryTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2164")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SamplingIntervalDiagnosticsArrayTypeNode.class,
            SamplingIntervalDiagnosticsArrayTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2165")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SamplingIntervalDiagnosticsTypeNode.class,
            SamplingIntervalDiagnosticsTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2171")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SubscriptionDiagnosticsArrayTypeNode.class,
            SubscriptionDiagnosticsArrayTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2172")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SubscriptionDiagnosticsTypeNode.class,
            SubscriptionDiagnosticsTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2196")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionDiagnosticsArrayTypeNode.class,
            SessionDiagnosticsArrayTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2197")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionDiagnosticsVariableTypeNode.class,
            SessionDiagnosticsVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2243")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionSecurityDiagnosticsArrayTypeNode.class,
            SessionSecurityDiagnosticsArrayTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2244")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            SessionSecurityDiagnosticsTypeNode.class,
            SessionSecurityDiagnosticsTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2365")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DataItemTypeNode.class,
            DataItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2368")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            AnalogItemTypeNode.class,
            AnalogItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2372")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            DiscreteItemTypeNode.class,
            DiscreteItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2373")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TwoStateDiscreteTypeNode.class,
            TwoStateDiscreteTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2376")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            MultiStateDiscreteTypeNode.class,
            MultiStateDiscreteTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2380")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ProgramDiagnosticTypeNode.class,
            ProgramDiagnosticTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2755")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            StateVariableTypeNode.class,
            StateVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2760")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FiniteStateVariableTypeNode.class,
            FiniteStateVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2762")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            TransitionVariableTypeNode.class,
            TransitionVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=2767")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            FiniteTransitionVariableTypeNode.class,
            FiniteTransitionVariableTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=11238")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            MultiStateValueDiscreteTypeNode.class,
            MultiStateValueDiscreteTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=3051")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            BuildInfoTypeNode.class,
            BuildInfoTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=11487")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            OptionSetTypeNode.class,
            OptionSetTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=12021")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ArrayItemTypeNode.class,
            ArrayItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=12029")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            YArrayItemTypeNode.class,
            YArrayItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=12038")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            XYArrayItemTypeNode.class,
            XYArrayItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=12047")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            ImageItemTypeNode.class,
            ImageItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=12057")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            CubeItemTypeNode.class,
            CubeItemTypeNode::new
        );
        variableTypeManager.registerVariableType(
            NodeId.parse("ns=0;i=12068")
                .reindex(namespaceTable, "http://opcfoundation.org/UA/"),
            NDimensionArrayItemTypeNode.class,
            NDimensionArrayItemTypeNode::new
        );
    }
}
