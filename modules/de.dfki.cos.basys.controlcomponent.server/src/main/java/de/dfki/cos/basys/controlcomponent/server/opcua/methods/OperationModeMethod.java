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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.util.Namespaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;

public class OperationModeMethod extends OperationsMethodInvocationHandler {

	private ControlComponent component;
	private OperationModeInfo info;
	
	public static final QualifiedProperty<String[]> ExecutionModes = new QualifiedProperty<>(
		Namespaces.OPC_UA,
		"AllowedExecutionModes",
		Identifiers.EnumValues,
		ValueRanks.OneDimension,
		String[].class
	);
	
	public static final QualifiedProperty<String[]> ExecutionCommands = new QualifiedProperty<>(
		Namespaces.OPC_UA,
		"AllowedExecutionCommands",
		Identifiers.EnumValues,
		ValueRanks.OneDimension,
		String[].class
	);

  
    public OperationModeMethod(UaMethodNode node, ControlComponent component, OperationModeInfo info) {
        super(node);
        this.component = component;
        this.info = info;
    }

	public String[] getExecutionModes() {
		List<String> result = new ArrayList<>(info.getExecutionModes().size());
		List<ExecutionMode> list = info.getExecutionModes();
		for (ExecutionMode executionMode : list) {
			result.add(executionMode.getName());
		}		
		return result.toArray(new String[0]);
	}

	public String[] getExecutionCommands() {
		List<String> result = new ArrayList<>(info.getExecutionModes().size());
		List<ExecutionCommand> list = info.getExecutionCommands();
		for (ExecutionCommand executionCommand : list) {
			result.add(executionCommand.getName());
		}		
		return result.toArray(new String[0]);
	}

	@Override
	protected ComponentOrderStatus doInvoke(String senderId) {
		 ComponentOrderStatus status = component.setOperationMode(info.getShortName(), senderId);       
		return status;
	}
	
}
