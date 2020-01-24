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

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;

public class OccupationCommandMethod extends OperationsMethodInvocationHandler {

	private ControlComponent component;
	private OccupationCommand cmd;
	
    public OccupationCommandMethod(UaMethodNode node, ControlComponent component, OccupationCommand cmd) {
        super(node);
        this.component = component;
        this.cmd = cmd;
    }

    @Override
    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) {
        logger.debug("Invoking " + cmd.getName() + " method of objectId={}", invocationContext.getObjectId());

        String occupierId = (String) inputValues[0].getValue();
        ComponentOrderStatus status = component.occupy(cmd, occupierId);       
        return new Variant[]{new Variant(status.getStatus().getName()),new Variant(status.getMessage())};
    }

}
