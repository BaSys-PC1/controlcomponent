package de.dfki.cos.basys.controlcomponent.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ServiceProvider;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.controlcomponent.CommandInterface;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;
import de.dfki.cos.basys.controlcomponent.OccupationState;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.SharedParameterSpace;
import de.dfki.cos.basys.controlcomponent.StatusInterface;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.OpcUaChannel;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.OpcUaException;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentClientImpl implements ControlComponentClient, ServiceProvider<ControlComponentClient> {

	public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private ComponentContext context = null;
	
	private boolean connected = false;
	Properties config;
	private boolean internalChannel = false;
	OpcUaChannel channel;

	PackMLWaitStatesHandler executionStateChangedHandler = null;

	private ControlComponentNode cc = null;
	private ControlComponentStatusNode status = null;
	private ControlComponentOperationsNode operations = null;
	private FolderNode variables = null;
	
	private UaSubscription subsciption = null;

	public ControlComponentClientImpl(Properties config, PackMLWaitStatesHandler handler) {
		this.config = config;
		//TODO inject open channel from external in order to circumvent 10 connections limit.
		this.channel = new OpcUaChannel(config);
		this.executionStateChangedHandler = handler;
		this.internalChannel = true;
	}

	public ControlComponentClientImpl(Properties config, PackMLWaitStatesHandler handler, OpcUaChannel channel) {
		this.config = config;
		this.channel = channel;
		this.executionStateChangedHandler = handler;
	}

	
	@Override
	public boolean connect(ComponentContext context, String connectionString) {
		LOGGER.info("connect");
		this.context = context;
		try {
			if (internalChannel) {
				channel.open(connectionString);
			}
			
			//channel.getClient().getAddressSpace().getObjectNode(new NodeId(channel.getNsIndex(),config.getProperty(StringConstants.id)), ControlComponentNode.class).get();			
			this.cc = channel.getClient().getAddressSpace().getObjectNode(new NodeId(channel.getNsIndex(),config.getProperty("nodeId")), ControlComponentNode.class).get();
			this.status = cc.getControlComponentStatusNode().get();
			this.operations = cc.getControlComponentOperationsNode().get();
			this.variables = (FolderNode) cc.getControlComponentVariablesNode().get();
			this.subsciption = channel.subscribeToValue(status.getExecutionStateNode().get().getNodeId().get(), this::onExecutionStateChanged);
			//channel.subscribeToValue(status.getOccupationStateNode().get().getNodeId().get(), this::onOccupationStateChanged);
		
			connected = true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return connected;
	}

	@Override
	public void disconnect() {
		LOGGER.info("disconnect");
		try {			
			channel.getClient().deleteSubscriptions(Collections.singletonList(this.subsciption.getSubscriptionId())).get();			
			if (internalChannel) {
				channel.close();
			}
			connected = false;
		} catch (OpcUaException | InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public boolean isConnected() {
		return connected;
	}

	@Override
	public ControlComponentClient getService() {
		return this;
	}

	@Override
	public int getErrorCode() {
		LOGGER.info("getErrorCode");
		int result = Integer.MIN_VALUE;
		try {
			result = status.getErrorCode().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getErrorMessage() {
		LOGGER.info("getErrorMessage");
		String result = null;
		try {
			result = status.getErrorMessage().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ErrorStatus getErrorStatus() {
		LOGGER.info("getErrorStatus");
		int errorCode = getErrorCode();
		String errorMessage = getErrorMessage();
		return new ErrorStatus.Builder().errorCode(errorCode).errorMessage(errorMessage).build();
	}

	@Override
	public OccupationState getOccupationState() {
		LOGGER.info("getOccupationState");
		OccupationState result = null;
		try {
			result = OccupationState.get(status.getOccupationState().get());
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public String getOccupierId() {
		LOGGER.info("getOccupierId");
		String result = null;
		try {
			result = status.getOccupierId().get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public OccupationStatus getOccupationStatus() {
		LOGGER.info("getOccupationStatus");
		OccupationState level = getOccupationState();
		String occupierId = getOccupierId();
		return new OccupationStatus.Builder().level(level).occupierId(occupierId).build();
	}

	@Override
	public OperationModeInfo getOperationMode() {
		LOGGER.info("getOperationMode");
		OperationModeInfo result = null;
		try {
			String opmode = status.getOperationMode().get();
			// TODO Get data from AAS
			result = new OperationModeInfo.Builder().name(opmode).build();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<OperationModeInfo> getOperationModes() {
		LOGGER.info("getOperationModes");
		// TODO Get data from AAS
		return null;
	}

	@Override
	public String getWorkState() {
		LOGGER.info("getWorkState");
		String result = null;
		try {
			result = status.getWorkState().get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		LOGGER.info("getExecutionMode");
		ExecutionMode result = null;
		try {
			result = ExecutionMode.get(status.getExecutionMode().get());
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ExecutionState getExecutionState() {
		LOGGER.info("getExecutionState");
		ExecutionState result = null;
		try {
			result = ExecutionState.get(status.getExecutionState().get());
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ComponentOrderStatus occupy(OccupationCommand command) {
		LOGGER.info("OccupationCommand [" + command + "]");
		
		CompletableFuture<NodeId> cf = null;
		switch (command) {
		case FREE:
			cf = operations.getFreeMethodNodeId();
			break;
		case OCCUPY:
			cf = operations.getOccupyMethodNodeId();			
			break;
		case PRIO:
			cf = operations.getPrioMethodNodeId();			
			break;
		default:
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("unknown occupation command").build();			
		}
		
		try {
			NodeId operationsNodeId = operations.getNodeId().get();
			NodeId methodNodeId = cf.get();
			return channel.callMethod(operationsNodeId, methodNodeId).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
	}

	@Override
	public ComponentOrderStatus free() {
		return occupy(OccupationCommand.FREE);
	}

	@Override
	public ComponentOrderStatus occupy() {
		return occupy(OccupationCommand.OCCUPY);
	}

	@Override
	public ComponentOrderStatus occupyPriority() {
		return occupy(OccupationCommand.PRIO);
	}


	@Override
	public ComponentOrderStatus setOperationMode(String mode) {
		LOGGER.info("setOperationMode [" + mode + "]");
				
		CompletableFuture<NodeId> cf = operations.getOperationModeMethodNodeId(mode);
		
		try {
			NodeId operationsNodeId = operations.getNodeId().get();
			NodeId methodNodeId = cf.get();
			return channel.callMethod(operationsNodeId, methodNodeId).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}

	}

	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode) {
		LOGGER.info("ExecutionMode [" + mode + "]");
		
		CompletableFuture<NodeId> cf = null;
		switch (mode) {
		case AUTO:
			cf = operations.getAutoMethodNodeId();
			break;
		case SEMIAUTO:
			cf = operations.getSemiAutoMethodNodeId();			
			break;
		case MANUAL:
			cf = operations.getManualMethodNodeId();			
			break;
		case SIMULATION:
			cf = operations.getSimulateMethodNodeId();			
			break;
		default:
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("unknown execution mode").build();			
		}
		
		try {
			return channel.callMethod(operations.getNodeId().get(), cf.get()).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
	}

	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command) {
		LOGGER.info("ExecutionCommand [" + command + "]");
		
		CompletableFuture<NodeId> cf = null;
		switch (command) {
		case RESET:
			cf = operations.getResetMethodNodeId();
			break;
		case START:
			cf = operations.getStartMethodNodeId();			
			break;
		case STOP:
			cf = operations.getStopMethodNodeId();			
			break;
		case HOLD:
			cf = operations.getHoldMethodNodeId();			
			break;
		case UNHOLD:
			cf = operations.getUnholdMethodNodeId();			
			break;
		case SUSPEND:
			cf = operations.getSuspendMethodNodeId();			
			break;
		case UNSUSPEND:
			cf = operations.getUnsuspendMethodNodeId();			
			break;
		case ABORT:
			cf = operations.getAbortMethodNodeId();			
			break;
		case CLEAR:
			cf = operations.getClearMethodNodeId();			
			break;
		default:
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("unknown execution command").build();			
		}
		
		try {
			return channel.callMethod(operations.getNodeId().get(), cf.get()).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
		

	}

	@Override
	public ComponentOrderStatus reset() {
		return raiseExecutionCommand(ExecutionCommand.RESET);
	}

	@Override
	public ComponentOrderStatus start() {
		return raiseExecutionCommand(ExecutionCommand.START);
	}

	@Override
	public ComponentOrderStatus stop() {
		return raiseExecutionCommand(ExecutionCommand.STOP);
	}

	@Override
	public ComponentOrderStatus hold() {
		return raiseExecutionCommand(ExecutionCommand.HOLD);
	}

	@Override
	public ComponentOrderStatus unhold() {
		return raiseExecutionCommand(ExecutionCommand.UNHOLD);
	}

	@Override
	public ComponentOrderStatus suspend() {
		return raiseExecutionCommand(ExecutionCommand.SUSPEND);
	}

	@Override
	public ComponentOrderStatus unsuspend() {
		return raiseExecutionCommand(ExecutionCommand.UNSUSPEND);
	}

	@Override
	public ComponentOrderStatus abort() {
		return raiseExecutionCommand(ExecutionCommand.ABORT);
	}

	@Override
	public ComponentOrderStatus clear() {
		return raiseExecutionCommand(ExecutionCommand.CLEAR);
	}

	protected void onExecutionModeChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("onExecutionModeChanged: item={}, value={}", item.getReadValueId().getNodeId(), value.getValue());

		// System.out.println("subscription value received: item=" +
		// item.getReadValueId().getNodeId() + ", value="
		// + value.getValue());

		ExecutionMode val = ExecutionMode.get((String) value.getValue().getValue());
		LOGGER.info("NEW ExecutionMode: {}", val.toString());

	}

	protected void onExecutionStateChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("onExecutionStateChanged: item={}, value={}", item.getReadValueId().getNodeId(), value.getValue());

		ExecutionState val = ExecutionState.get((String) value.getValue().getValue());

		ControlComponentInfo info = new ControlComponentInfo();
		info.setExecutionState(val);		
		context.getEventBus().post(info);
		
		if (executionStateChangedHandler != null) {		
			switch (val) {
			case IDLE:
				executionStateChangedHandler.onIdle();
				break;
			case COMPLETE:
				executionStateChangedHandler.onComplete();
				break;
			case STOPPED:
				executionStateChangedHandler.onStopped();
				break;
			case HELD:
				executionStateChangedHandler.onHeld();
				break;
			case SUSPENDED:
				executionStateChangedHandler.onSuspended();
				break;
			case ABORTED:
				executionStateChangedHandler.onAborted();
				break;
	
			default:
				break;
			}
		} else {
			LOGGER.warn("NO executionStateChangedHandler specified");
		}
		LOGGER.info("NEW ExecutionState: {}", val.toString());

	}

	protected void onOccupationStateChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("onOccupationStateChanged: item={}, value={}", item.getReadValueId().getNodeId(), value.getValue());

//		ControlComponentInfo info = new ControlComponentInfo();
//		info.setOccupationStatus(occupationStatus)		
//		context.getEventBus().post(info);
		
		OccupationState val = OccupationState.get((String) value.getValue().getValue());
		LOGGER.info("NEW OccupationState: {}", val.toString());

	}

	// SharedParameterSpace

	@Override
	public List<ParameterInfo> getParameters() throws ComponentException {
		try {			
			List<Node> nodes = channel.getClient().getAddressSpace().browseNode(variables).get();
			List<ParameterInfo> result = new ArrayList<>(nodes.size());

			for (Node node : nodes) {

				ParameterInfo p = null;
				try {
					p = getParameter(node.getBrowseName().get().getName());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (p != null)
					result.add(p);
			}

			return result;
		} catch (InterruptedException | ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new ComponentException(e1);
		}
	}

	@Override
	public ParameterInfo getParameter(String name) throws ComponentException {
		try {
			//variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name)).thenCompose(n -> n.readDataType()).thenCompose(d -> d.getValue())
			
			VariableNode var = variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name)).get();
			Object value = var.getValue().get();
					
			EnumSet<AccessLevel> accessLevel = AccessLevel.fromMask(var.getUserAccessLevel().get());
			ParameterDirection direction = ParameterDirection.OUT;
			if (accessLevel.contains(AccessLevel.CurrentWrite))
				direction = ParameterDirection.IN;

			DataValue dt = var.readDataType().get();
			String typename = (String) dt.getValue().getValue();
			
//			ReadValueId rvid = new ReadValueId(nodeId, AttributeId.DataType.uid(), null, QualifiedName.NULL_VALUE);
//			ReadResponse resp = channel.getClient().read(0, TimestampsToReturn.Both, Collections.singletonList(rvid))
//					.get();
//			UaNode datatypeNode = channel.getClient().getAddressSpace()
//					.getNodeInstance((NodeId) resp.getResults()[0].getValue().getValue()).get();
//			String typename = datatypeNode.getBrowseName().get().getName();

			ParameterInfo parameter = new ParameterInfo.Builder().name(name).value(value).type(typename)
					.access(direction).build();
			return parameter;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object getParameterValue(String name) throws ComponentException {
		try {
			VariableNode var = variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name)).get();
			return var.getValue().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			throw new ComponentException(e);
		}
//		
//		NodeId nodeId = nodeIds.newHierarchicalNodeId(nodeIds.folderVariables, name);
//		try {
//			return channel.readValue(nodeId);
//		} catch (OpcUaException e) {
//			e.printStackTrace();
//			throw new ComponentException(e);
//		}
	}

	@Override
	public void setParameterValue(String name, Object value) throws ComponentException {		
		try {
			VariableNode var = variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name)).get();
			StatusCode status = var.setValue(value).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
//		
//		NodeId nodeId = nodeIds.newHierarchicalNodeId(nodeIds.folderVariables, name);
//		try {
//			StatusCode status = channel.writeValue(nodeId, value);
//		} catch (OpcUaException e) {
//			e.printStackTrace();
//			throw new ComponentException(e);
//		}
	}
	
	protected ControlComponentNode getControlComponentNode() {
		return cc;
	}

}