package de.dfki.cos.basys.controlcomponent.client;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.dfki.cos.basys.common.component.ComponentConfiguration;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ComponentOrderStatus;
import de.dfki.cos.basys.common.component.OrderStatus;
import de.dfki.cos.basys.controlcomponent.CommandInterface;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
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
import de.dfki.cos.basys.controlcomponent.client.util.BaSysOpcUaClient;
import de.dfki.cos.basys.controlcomponent.client.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.client.util.OpcUaException;

public class ControlComponentClient implements StatusInterface, CommandInterface {
	
	public final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
		
	ComponentConfiguration config;
	BaSysOpcUaClient client;
	NodeIds nodeIds;
	
	public ControlComponentClient(ComponentConfiguration config) {
		this.config = config;
		this.client = new BaSysOpcUaClient();
		this.nodeIds = new NodeIds(config.getId());
	}

	public void connect() throws ComponentException {
		try {
			client.connect(config.getExternalConnectionString());	
			client.subscribeToValue(nodeIds.statusExecutionState, this::onExecutionStateChanged);
		} catch (Exception e) {
			throw new ComponentException(e);
		}
	}

	public void disconnect() throws ComponentException {
		try {
			client.disconnect();
		} catch (OpcUaException e) {
			throw new ComponentException(e);
		}
	}

	@Override
	public int getErrorCode() {	
		int result = Integer.MIN_VALUE;
		try {
			result = client.readValue(nodeIds.statusErrorCode);
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;		
	}

	@Override
	public String getErrorMessage() {
		String result = null;
		try {
			result = client.readValue(nodeIds.statusErrorMessage);
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ErrorStatus getErrorStatus() {
		return new ErrorStatus.Builder().errorCode(getErrorCode()).errorMessage(getErrorMessage()).build();
	}

	@Override
	public ControlComponentInfo getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OccupationLevel getOccupationLevel() {
		OccupationLevel result = null;
		try {
			result = OccupationLevel.get(client.readValue(nodeIds.statusOccupationState));
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OccupationStatus getOccupationStatus() {
		return new OccupationStatus.Builder().level(getOccupationLevel()).occupierId(getOccupierId()).build();
	}

	@Override
	public String getOccupierId() {
		String result = null;
		try {
			result = client.readValue(nodeIds.statusOccupierId);
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public OperationModeInfo getOperationMode() {
		OperationModeInfo result = null;
		try {
			String opmode = client.readValue(nodeIds.statusOperationMode);
			//TODO Get data from AAS 
			result = new OperationModeInfo.Builder().name(opmode).build();
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<OperationModeInfo> getOperationModes() {
		// TODO Get data from AAS
		return null;
	}

	@Override
	public String getWorkState() {
		String result = null;
		try {
			result = client.readValue(nodeIds.statusWorkState);
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ExecutionMode getExecutionMode() {
		ExecutionMode result = null;
		try {
			result = ExecutionMode.get(client.readValue(nodeIds.statusExecutionMode));
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ExecutionState getExecutionState() {
		ExecutionState result = null;
		try {
			result = ExecutionState.get(client.readValue(nodeIds.statusExecutionState));
		} catch (OpcUaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ComponentOrderStatus occupy(OccupationLevel level, String occupierId) {
		try {
			return client.callMethod(nodeIds.serviceOccupationCommands, nodeIds.occupationCommandNodes.get(level), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
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
	public ComponentOrderStatus setOperationMode(String opmode, String occupierId) {
		try {
			return client.callMethod(nodeIds.serviceOperationModes, nodeIds.operationModeNodes.get(opmode), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}	
	}

	@Override
	public ComponentOrderStatus setExecutionMode(ExecutionMode mode, String occupierId) {
		try {
			return client.callMethod(nodeIds.serviceExecutionModes, nodeIds.executionModeNodes.get(mode), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return new ComponentOrderStatus.Builder().status(OrderStatus.REJECTED).message(e.getMessage()).build();
		}
	}


	@Override
	public ComponentOrderStatus raiseExecutionCommand(ExecutionCommand command, String occupierId) {
		try {
			return client.callMethod(nodeIds.serviceExecutionCommands, nodeIds.executionCommandNodes.get(command), occupierId).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
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

	protected void onExecutionStateChanged(UaMonitoredItem item, DataValue value) {
		LOGGER.info("subscription value received: item={}, value={}", item.getReadValueId().getNodeId(),
				value.getValue());

		//System.out.println("subscription value received: item=" + item.getReadValueId().getNodeId() + ", value="
		//			+ value.getValue());
		
		ExecutionState exState = ExecutionState.get((String)value.getValue().getValue());
		LOGGER.info("NEW ExecutionState: {}", exState.toString());
		
	}
  }