/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.server.opcua.methods;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;

public class ExecutionCommandMethod extends OperationsMethodInvocationHandler {

	private ControlComponent component;
	private ExecutionCommand command;

    public ExecutionCommandMethod(UaMethodNode node, ControlComponent component, ExecutionCommand command) {
        super(node);
        this.component = component;
        this.command = command;
    }

    @Override
    protected ComponentOrderStatus doInvoke(String senderId) {
    	 ComponentOrderStatus status = component.raiseExecutionCommand(command, senderId);
    	 return status;
    }
    
}
