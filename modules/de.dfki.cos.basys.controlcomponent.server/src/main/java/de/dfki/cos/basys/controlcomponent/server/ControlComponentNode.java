/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.server;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseObjectNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegateChain;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.eventbus.Subscribe;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.manager.ComponentManager;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerImpl;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;
import de.dfki.cos.basys.controlcomponent.OccupationState;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.server.methods.ExecutionCommandMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.ExecutionModeMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.GenerateEventMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.OccupationCommandMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.OperationModeMethod;
import de.dfki.cos.basys.controlcomponent.server.types.CustomDataType;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class ControlComponentNode extends BaseObjectNode implements ControlComponentType {
    
	private ControlComponent component = null;
	
	public ControlComponentNode(ControlComponent component, UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
		this.component = component;
		
	}

	public ControlComponentNode(ControlComponent component, UaNodeContext context, NodeId nodeId, QualifiedName browseName,
			LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask,
			UByte eventNotifier) {
		super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
		this.component = component;
	}

	@Override
	public ControlComponent getControlComponent() {
		return component;
	}
}
