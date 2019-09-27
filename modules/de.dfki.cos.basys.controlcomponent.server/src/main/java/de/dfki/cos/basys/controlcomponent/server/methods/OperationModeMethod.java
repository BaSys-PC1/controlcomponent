/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.server.methods;

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

import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;

public class OperationModeMethod extends AbstractMethodInvocationHandler {

	private ControlComponent component;
	private OperationModeInfo info;
	
	//public static final String NAMESPACE = "http://basys42.de/controlcomponent";
	
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

    public static final Argument OCCUPIER = new Argument(
        "occupierId",
        Identifiers.String,
        ValueRanks.Scalar,
        null,
        new LocalizedText("The occupier id.")
    );

    public static final Argument MSG = new Argument(
        "message",
        Identifiers.String,
        ValueRanks.Scalar,
        null,
        new LocalizedText("The response message.")
    );
  
    public static final Argument STATUS = new Argument(
        "status",
        Identifiers.String,
        ValueRanks.Scalar,
        null,
        new LocalizedText("The response status.")
    );

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public OperationModeMethod(ControlComponent component, OperationModeInfo info, UaMethodNode node) {
        super(node);
        this.component = component;
        this.info = info;
    }

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{OCCUPIER};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{STATUS, MSG};
    }

    @Override
    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) {
        logger.debug("Set operation mode " + info.getName() + " of objectId={}", invocationContext.getObjectId());

        String occupierId = (String) inputValues[0].getValue();
        ComponentOrderStatus status = component.setOperationMode(info.getName(), occupierId);       
        return new Variant[]{new Variant(status.getStatus().getName()),new Variant(status.getMessage())};
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

}
