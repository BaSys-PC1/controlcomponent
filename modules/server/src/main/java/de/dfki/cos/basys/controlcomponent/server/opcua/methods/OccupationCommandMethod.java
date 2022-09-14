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

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;

public class OccupationCommandMethod extends OperationsMethodInvocationHandler {

	private ControlComponent component;
	private OccupationCommand cmd;
	
    public OccupationCommandMethod(UaMethodNode node, ControlComponent component, OccupationCommand cmd) {
        super(node);
        this.component = component;
        this.cmd = cmd;
    }

    @Override
    protected ComponentOrderStatus doInvoke(String senderId) {
    	 ComponentOrderStatus status = component.occupy(cmd, senderId);     
    	 return status;
    }
    
}
