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

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.OrderStatusCodes;

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
        //return new Argument[]{SENDERID};
    	return new Argument[] {};
    }

    @Override
    public Argument[] getOutputArguments() {
        //return new Argument[]{STATUS, MSG};
    	return new Argument[] {};
    }

    public OperationsMethodInvocationHandler(UaMethodNode node) {
		super(node);
		// TODO Auto-generated constructor stub
	}

    @Override
    protected Variant[] invoke(InvocationContext invocationContext, Variant[] inputValues) throws UaException {
        logger.debug("Invoking " + invocationContext.getMethodNode().getBrowseName().getName() + " method of objectId={}", invocationContext.getObjectId());

        Optional<Object> identityObject = invocationContext.getSession().map(session -> session.getIdentityObject());        
        String senderId = (String) identityObject.get();
        if (inputValues.length > 0 && inputValues[0].isNotNull()) {
        	senderId = (String) inputValues[0].getValue();
        }       
        
        ComponentOrderStatus status = doInvoke(senderId);
        if (status.getStatus() == OrderStatus.ACCEPTED) {
        	throw new UaException(StatusCodes.Good_CompletesAsynchronously);
        } else if (status.getStatus() == OrderStatus.REJECTED) {
        	long statusCode;
			switch (status.getStatusCode()) {
			case OrderStatusCodes.NothingToDo:
				statusCode = StatusCodes.Bad_NothingToDo;
				break;
			case OrderStatusCodes.NotSupported:
				statusCode = StatusCodes.Bad_NotSupported;
				break;
			case OrderStatusCodes.InvalidState:
				statusCode = StatusCodes.Bad_InvalidState;
				break;
			case OrderStatusCodes.RequestNotAllowed:
				statusCode = StatusCodes.Bad_RequestNotAllowed;
				break;
			case OrderStatusCodes.IdentityTokenInvalid:
				statusCode = StatusCodes.Bad_IdentityTokenInvalid;
				break;
			case OrderStatusCodes.IdentityTokenRejected:
				statusCode = StatusCodes.Bad_IdentityTokenRejected;
				break;
			case OrderStatusCodes.NotImplemented:
				statusCode = StatusCodes.Bad_NotImplemented;
				break;
			case OrderStatusCodes.NotFound:
				statusCode = StatusCodes.Bad_NotFound;
				break;
			case OrderStatusCodes.EntryExists:
				statusCode = StatusCodes.Bad_EntryExists;
				break;
			case OrderStatusCodes.NoEntryExists:
				statusCode = StatusCodes.Bad_NoEntryExists;
				break;
			default:
				statusCode = StatusCodes.Bad_UnexpectedError;
			}
        	throw new UaException(statusCode);
        }
        //return new Variant[]{new Variant(status.getStatus().getName()),new Variant(status.getMessage())};
        return new Variant[0];
    }
    
    protected abstract ComponentOrderStatus doInvoke(String senderId);

}
