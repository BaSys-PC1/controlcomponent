package de.dfki.cos.basys.controlcomponent.client;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.common.component.FunctionalClient;
import de.dfki.cos.basys.common.component.OrderStatus;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.controlcomponent.CommandInterface;
import de.dfki.cos.basys.controlcomponent.ControlComponentInfo;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.StatusInterface;
import de.dfki.cos.basys.controlcomponent.client.util.OpcUaChannel;
import de.dfki.cos.basys.controlcomponent.client.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.client.util.OpcUaException;
import de.dfki.cos.basys.controlcomponent.packml.PackMLWaitStatesHandler;

public class ControlComponentClient implements FunctionalClient, StatusInterface, CommandInterface {
	
	public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
		
	Properties config;
	OpcUaChannel channel;
	NodeIds nodeIds;
	
	PackMLWaitStatesHandler executionStateChangedHandler = null;
	
	public ControlComponentClient(Properties config) {		
		this.config = config;
		this.channel = new OpcUaChannel();
		this.nodeIds = new NodeIds(config.getProperty(StringConstants.id));
	}
	
	public PackMLWaitStatesHandler getExecutionStateChangedHandler() {
		return executionStateChangedHandler;
	}
	public void setExecutionStateChangedHandler(PackMLWaitStatesHandler executionStateChangedHandler) {
		this.executionStateChangedHandler = executionStateChangedHandler;
	}
	
	@Override
	public boolean connect(ComponentContext context, String connectionString) {
		LOGGER.info("connect");
		try {			
			channel.open(connectionString);	
			//channel.subscribeToValue(nodeIds.statusExecutionMode, this::onExecutionModeChanged);
			channel.subscribeToValue(nodeIds.statusExecutionState, this::onExecutionStateChanged);
			//channel.subscribeToValue(nodeIds.statusOccupationState, this::onOccupationLevelChanged);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}		
	}
	
	@Override
	public boolean disconnect() {
		LOGGER.info("disconnect");
		try {
			channel.close();
			return true;
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}
	
	@Override
	public int getErrorCode() {	
		LOGGER.info("getErrorCode");
		int result = Integer.MIN_VALUE;
		try {
			result = channel.readValue(nodeIds.statusErrorCode);
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;		
	}

	@Override
	public String getErrorMessage() {
		LOGGER.info("getErrorMessage");
		String result = null;
		try {
			result = channel.readValue(nodeIds.statusErrorMessage);
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ErrorStatus getErrorStatus() {
		LOGGER.info("getErrorStatus");
		try {
			List<Object> result = channel.readValues(Arrays.asList(nodeIds.statusErrorCode, nodeIds.statusErrorMessage));
			Integer errorCode  = (Integer) result.get(0);
			String errorMessage = (String) result.get(1);
			return new ErrorStatus.Builder().errorCode(errorCode).errorMessage(errorMessage).build();
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
			return null;
		}		
	}

	@Override
	public OccupationLevel getOccupationLevel() {
		LOGGER.info("getOccupationLevel");
		OccupationLevel result = null;
		try {
			result = OccupationLevel.get(channel.readValue(nodeIds.statusOccupationState));
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public String getOccupierId() {
		LOGGER.info("getOccupierId");
		String result = null;
		try {
			result = channel.readValue(nodeIds.statusOccupierId);
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	public OccupationStatus getOccupationStatus() {
		LOGGER.info("getOccupationStatus");		
		try {
			List<Object> result = channel.readValues(Arrays.asList(nodeIds.statusOccupationState, nodeIds.statusOccupierId));
			OccupationLevel level  = OccupationLevel.get((String)result.get(0));
			String occupierId = (String) result.get(1);
			return new OccupationStatus.Builder().level(level).occupierId(occupierId).build();
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
			return null;
		}		
	}

	@Override
	public OperationModeInfo getOperationMode() {
		LOGGER.info("getOperationMode");
		OperationModeInfo result = null;
		try {
			String opmode = channel.readValue(nodeIds.statusOperationMode);
			//TODO Get data from AAS 
			result = new OperationModeInfo.Builder().name(opmode).build();
		} catch (OpcUaException e) {
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
			result = channel.readValue(nodeIds.statusWorkState);
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		LOGGER.info("getExecutionMode");
		ExecutionMode result = null;
		try {
			result = ExecutionMode.get(channel.readValue(nodeIds.statusExecutionMode));
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ExecutionState getExecutionState() {
		LOGGER.info("getExecutionState");
		ExecutionState result = null;
		try {
			result = ExecutionState.get(channel.readValue(nodeIds.statusExecutionState));
		} catch (OpcUaException e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public ComponentOrderStatus occupy(OccupationLevel level, String occupierId) {
		LOGGER.info("occupy [" + level + "] (occupierId="+ occupierId + ")");
		try {
			return channel.callMethod(nodeIds.folderOccupationCommandServices, nodeIds.occupationCommandNodes.get(level), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
	}
	
	@Override
	public ComponentOrderStatus free(String occupierId) {
		return occupy(OccupationLevel.FREE, occupierId);
	}

	@Override
	public ComponentOrderStatus occupy(String occupierId) {
		return occupy(OccupationLevel.OCCUPIED, occupierId);
	}

	@Override
	public ComponentOrderStatus occupyPriority(String occupierId) {
		return occupy(OccupationLevel.PRIORITY, occupierId);
	}

	@Override
	public ComponentOrderStatus occupyLocal(String occupierId) {
		return occupy(OccupationLevel.LOCAL, occupierId);
	}


	@Override
	public ComponentOrderStatus registerOperationMode(OperationMode opmode, String occupierId) {		
		return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("not (yet) possible via remote").build();
	}

	@Override
	public ComponentOrderStatus unregisterOperationMode(String opmode, String occupierId) {
		return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message("not (yet) possible via remote").build();
	}
	
	@Override
	public ComponentOrderStatus setOperationMode(String mode, String occupierId) {
		LOGGER.info("setOperationMode [" + mode + "] (occupierId="+ occupierId + ")");
		try {
			return channel.callMethod(nodeIds.folderOperationModeServices, nodeIds.getOperationModeNode(mode), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}	
	}

	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode, String occupierId) {
		LOGGER.info("setExecutionMode [" + mode + "] (occupierId="+ occupierId + ")");
		try {
			return channel.callMethod(nodeIds.folderExecutionModeServices, nodeIds.executionModeNodes.get(mode), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
	}


	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String occupierId) {
		LOGGER.info("raiseExecutionCommand [" + command + "] (occupierId="+ occupierId + ")");
		try {
			return channel.callMethod(nodeIds.folderExecutionCommandServices, nodeIds.executionCommandNodes.get(command), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			LOGGER.error(e.getMessage());
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
	}

	@Override
	public ComponentOrderStatus reset(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.RESET, occupierId);
	}

	@Override
	public ComponentOrderStatus start(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.START, occupierId);
	}

	@Override
	public ComponentOrderStatus stop(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.STOP, occupierId);
	}

	@Override
	public ComponentOrderStatus hold(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.HOLD, occupierId);
	}

	@Override
	public ComponentOrderStatus unhold(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.UNHOLD, occupierId);
	}

	@Override
	public ComponentOrderStatus suspend(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.SUSPEND, occupierId);
	}

	@Override
	public ComponentOrderStatus unsuspend(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.UNSUSPEND, occupierId);
	}

	@Override
	public ComponentOrderStatus abort(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.ABORT, occupierId);
	}

	@Override
	public ComponentOrderStatus clear(String occupierId) {
		return raiseExecutionCommand(ExecutionCommand.CLEAR, occupierId);
	}

	protected void onExecutionModeChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("onExecutionModeChanged: item={}, value={}", item.getReadValueId().getNodeId(),
				value.getValue());

		//System.out.println("subscription value received: item=" + item.getReadValueId().getNodeId() + ", value="
		//			+ value.getValue());
		
		ExecutionMode val = ExecutionMode.get((String)value.getValue().getValue());
		LOGGER.info("NEW ExecutionMode: {}", val.toString());
		
	}
	
	protected void onExecutionStateChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("onExecutionStateChanged: item={}, value={}", item.getReadValueId().getNodeId(),
				value.getValue());

		//System.out.println("subscription value received: item=" + item.getReadValueId().getNodeId() + ", value="
		//			+ value.getValue());
		
		ExecutionState val = ExecutionState.get((String)value.getValue().getValue());
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
		
		LOGGER.info("NEW ExecutionState: {}", val.toString());
		
	}
	
	protected void onOccupationLevelChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("onOccupationLevelChanged: item={}, value={}", item.getReadValueId().getNodeId(),
				value.getValue());

		//System.out.println("subscription value received: item=" + item.getReadValueId().getNodeId() + ", value="
		//			+ value.getValue());
		
		OccupationLevel val = OccupationLevel.get((String)value.getValue().getValue());
		LOGGER.info("NEW OccupationLevel: {}", val.toString());
		
	}
	

  }