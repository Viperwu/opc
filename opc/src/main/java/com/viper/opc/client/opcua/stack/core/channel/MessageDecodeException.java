/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package com.viper.opc.client.opcua.stack.core.channel;

import com.viper.opc.client.opcua.stack.core.UaException;

public class MessageDecodeException extends Exception {

    public MessageDecodeException(UaException cause) {
        super(cause);
    }

}
