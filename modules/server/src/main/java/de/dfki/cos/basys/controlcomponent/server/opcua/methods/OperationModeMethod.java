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

import de.dfki.cos.basys.controlcomponent.*;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;

import java.util.ArrayList;
import java.util.List;

public class OperationModeMethod extends OperationsMethodInvocationHandler {

	private ControlComponent component;
	private OperationModeInfo info;
	
	
	public static final QualifiedProperty<String[]> ExecutionModes = new QualifiedProperty<>(
		NodeIds.NAMESPACE_URI,
		"AllowedExecutionModes",
		Identifiers.EnumValues.expanded(),
		ValueRanks.OneDimension,
		String[].class
	);
	
	public static final QualifiedProperty<String[]> ExecutionCommands = new QualifiedProperty<>(
		NodeIds.NAMESPACE_URI,
		"AllowedExecutionCommands",
		Identifiers.EnumValues.expanded(),
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
