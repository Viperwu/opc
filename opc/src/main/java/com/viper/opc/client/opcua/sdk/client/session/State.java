/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.sdk.client.session;

enum State {

    Inactive,
    CreatingWait,
    Creating,
    Activating,
    Transferring,
    Initializing,
    Active,
    Closing

}
