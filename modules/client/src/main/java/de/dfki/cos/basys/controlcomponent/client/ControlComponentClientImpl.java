package de.dfki.cos.basys.controlcomponent.client;

import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ServiceProvider;
import de.dfki.cos.basys.controlcomponent.*;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.nodes.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.OpcUaChannel;
import de.dfki.cos.basys.controlcomponent.client.opcua.util.OpcUaException;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.methods.UaMethod;
import org.eclipse.milo.opcua.sdk.client.model.nodes.objects.FolderTypeNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;

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
	private FolderTypeNode variables = null;
	
	private UaSubscription subscription = null;

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
			NodeId nodeId = new NodeId(channel.getNsIndex(),config.getProperty("nodeId"));
			//channel.getClient().getAddressSpace().getObjectNode(new NodeId(channel.getNsIndex(),config.getProperty(StringConstants.id)), ControlComponentNode.class).get();			
			this.cc = (ControlComponentNode) channel.getClient().getAddressSpace().getObjectNode(nodeId);
			this.status = cc.getControlComponentStatusNode();
			this.operations = cc.getControlComponentOperationsNode();
			this.variables = (FolderTypeNode) cc.getControlComponentVariablesNode();
			this.subscription = channel.subscribeToValue(status.getExecutionStateNode().getNodeId(), this::onExecutionStateChanged);
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
			channel.getClient().deleteSubscriptions(Collections.singletonList(this.subscription.getSubscriptionId())).get();			
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
		int result = status.getErrorCode();
		return result;
	}

	@Override
	public String getErrorMessage() {
		LOGGER.info("getErrorMessage");
		String result = status.getErrorMessage();
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
		OccupationState result = OccupationState.get(status.getOccupationState());
		return result;
	}

	@Override
	public String getOccupierId() {
		LOGGER.info("getOccupierId");
		String result = status.getOccupierId();
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
		String opmode = status.getOperationMode();
		// TODO Get data from AAS
		OperationModeInfo result = new OperationModeInfo.Builder().name(opmode).build();		
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
		String result = status.getWorkState();
		return result;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		LOGGER.info("getExecutionMode");
		ExecutionMode result = ExecutionMode.get(status.getExecutionMode());
		return result;
	}

	@Override
	public ExecutionState getExecutionState() {
		LOGGER.info("getExecutionState");
		ExecutionState result = ExecutionState.get(status.getExecutionState());		
		return result;
	}

	@Override
	public ComponentOrderStatus occupy(OccupationCommand command) {
		LOGGER.info("OccupationCommand [" + command + "]");
		
		UaMethod method = null;
		switch (command) {
		case FREE:
			method = operations.getFreeMethod();
			break;
		case OCCUPY:
			method = operations.getOccupyMethod();			
			break;
		case PRIO:
			method = operations.getPrioMethod();			
			break;
		default:
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("unknown occupation command").build();			
		}
		
		return channel.callMethod(method);
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
		UaMethod method = operations.getOperationModeMethod(mode);
		return channel.callMethod(method);
	}

	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode) {
		LOGGER.info("ExecutionMode [" + mode + "]");
		
		UaMethod method = null;
		switch (mode) {
		case AUTO:
			method = operations.getAutoMethod();
			break;
		case SEMIAUTO:
			method = operations.getSemiAutoMethod();			
			break;
		case MANUAL:
			method = operations.getManualMethod();			
			break;
		case SIMULATE:
			method = operations.getSimulateMethod();			
			break;
		default:
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("unknown execution mode").build();			
		}
		
		return channel.callMethod(method);		
	}

	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command) {
		LOGGER.info("ExecutionCommand [" + command + "]");
		
		UaMethod method = null;
		switch (command) {
		case RESET:
			method = operations.getResetMethod();
			break;
		case START:
			method = operations.getStartMethod();			
			break;
		case STOP:
			method = operations.getStopMethod();			
			break;
		case HOLD:
			method = operations.getHoldMethod();			
			break;
		case UNHOLD:
			method = operations.getUnholdMethod();			
			break;
		case SUSPEND:
			method = operations.getSuspendMethod();			
			break;
		case UNSUSPEND:
			method = operations.getUnsuspendMethod();			
			break;
		case ABORT:
			method = operations.getAbortMethod();			
			break;
		case CLEAR:
			method = operations.getClearMethod();			
			break;
		default:
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("unknown execution command").build();			
		}
		
		return channel.callMethod(method);
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
			List<? extends UaNode> nodes = channel.getClient().getAddressSpace().browseNodes(variables);
			List<ParameterInfo> result = new ArrayList<>(nodes.size());

			for (UaNode node : nodes) {
				ParameterInfo p = getParameter(node.getBrowseName().getName());				
				if (p != null)
					result.add(p);
			}

			return result;
		} catch (UaException e) {
			throw new ComponentException(e);
		}
	}

	@Override
	public ParameterInfo getParameter(String name) throws ComponentException {
		try {
			//variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name)).thenCompose(n -> n.readDataType()).thenCompose(d -> d.getValue())
			
			UaVariableNode var = variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name));
			Object value = var.getValue().getValue().getValue();
					
			EnumSet<AccessLevel> accessLevel = AccessLevel.fromValue(var.getUserAccessLevel());
			ParameterDirection direction = ParameterDirection.OUT;
			if (accessLevel.contains(AccessLevel.CurrentWrite))
				direction = ParameterDirection.IN;

			NodeId dataTypeNodeId = var.getDataType();
			UaNode dataTypeNode = channel.getNode(dataTypeNodeId);
			String typename = (String) dataTypeNode.getBrowseName().getName();
			
//			ReadValueId rvid = new ReadValueId(nodeId, AttributeId.DataType.uid(), null, QualifiedName.NULL_VALUE);
//			ReadResponse resp = channel.getClient().read(0, TimestampsToReturn.Both, Collections.singletonList(rvid))
//					.get();
//			UaNode datatypeNode = channel.getClient().getAddressSpace()
//					.getNodeInstance((NodeId) resp.getResults()[0].getValue().getValue()).get();
//			String typename = datatypeNode.getBrowseName().get().getName();

			ParameterInfo parameter = new ParameterInfo.Builder().name(name).value(value).type(typename)
					.access(direction).build();
			return parameter;
		} catch (UaException e) {
			e.printStackTrace();
			throw new ComponentException(e);
		}
	}

	@Override
	public Object getParameterValue(String name) throws ComponentException {
		try {
			UaVariableNode var = variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name));
			return var.getValue().getValue().getValue();
		} catch (UaException e) {
			throw new ComponentException(e);
		}
	}

	@Override
	public void setParameterValue(String name, Object value) throws ComponentException {		
		try {
			UaVariableNode var = variables.getVariableComponent(new QualifiedName(channel.getNsIndex(),name));						
			var.writeValue(new Variant(value));
		} catch (UaException e) {
			throw new ComponentException(e);
		}
	}
	
	protected ControlComponentNode getControlComponentNode() {
		return cc;
	}

	public OpcUaChannel getChannel() {
		return channel;
	}
	
}