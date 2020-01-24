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
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;

public abstract class OperationsMethodInvocationHandler extends AbstractMethodInvocationHandler {

	public static final Argument SENDERID = new Argument(
        "SENDERID",
        Identifiers.String,
        ValueRanks.Scalar,
        null,
        new LocalizedText("The sender id.")
    );

    public static final Argument MSG = new Argument(
        "MESSAGE",
        Identifiers.String,
        ValueRanks.Scalar,
        null,
        new LocalizedText("The response message.")
    );
  
    public static final Argument STATUS = new Argument(
        "STATUS",
        Identifiers.String,
        ValueRanks.Scalar,
        null,
        new LocalizedText("The response status.")
    );

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Argument[] getInputArguments() {
        return new Argument[]{SENDERID};
    }

    @Override
    public Argument[] getOutputArguments() {
        return new Argument[]{STATUS, MSG};
    }

    public OperationsMethodInvocationHandler(UaMethodNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}


}
