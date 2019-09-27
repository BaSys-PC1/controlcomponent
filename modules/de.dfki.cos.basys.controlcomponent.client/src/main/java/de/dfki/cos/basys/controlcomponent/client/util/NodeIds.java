package de.dfki.cos.basys.controlcomponent.client.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;

public class NodeIds {

	public int namespaceIndex = 2;
	public String componentId = null;

	public NodeId statusOccupationState;
	public NodeId statusOccupierId;
	public NodeId statusExecutionMode;
	public NodeId statusExecutionState;
	public NodeId statusOperationMode;
	public NodeId statusWorkState;
	public NodeId statusErrorCode;
	public NodeId statusErrorMessage;

	public NodeId serviceExecutionCommands;
	public NodeId serviceExecutionModes;
	public NodeId serviceOccupationCommands;

	public Map<OccupationLevel, NodeId> occupationCommandNodes = new HashMap<>();
	public Map<ExecutionMode, NodeId> executionModeNodes = new HashMap<>();
	public Map<ExecutionCommand, NodeId> executionCommandNodes = new HashMap<>();

	public NodeIds(String componentId) {
		this(2, componentId);
	};

	public NodeIds(int namespaceIndex, String componentId) {
		this.namespaceIndex = namespaceIndex;
		this.componentId = componentId;
		initNodeIds();
	};

	private void initNodeIds() {
		statusOccupierId = init("status/OccupierId");
		statusOccupationState = init("status/OccupationState");
		statusExecutionMode = init("status/ExecutionMode");
		statusExecutionState = init("status/ExecutionState");
		statusOperationMode = init("status/OperationMode");
		statusWorkState = init("status/WorkState");
		statusErrorCode = init("status/ErrorCode");
		statusErrorMessage = init("status/ErrorMessage");

		serviceExecutionCommands = init("service/ExecutionCommands");
		executionCommandNodes.put(ExecutionCommand.ABORT, init("service/ABORT"));
		executionCommandNodes.put(ExecutionCommand.CLEAR, init("service/CLEAR"));
		executionCommandNodes.put(ExecutionCommand.HOLD, init("service/HOLD"));
		executionCommandNodes.put(ExecutionCommand.RESET, init("service/RESET"));
		executionCommandNodes.put(ExecutionCommand.START, init("service/START"));
		executionCommandNodes.put(ExecutionCommand.STOP, init("service/STOP"));
		executionCommandNodes.put(ExecutionCommand.SUSPEND, init("service/SUSPEND"));
		executionCommandNodes.put(ExecutionCommand.UNHOLD, init("service/UNHOLD"));
		executionCommandNodes.put(ExecutionCommand.UNSUSPEND, init("service/UNSUSPEND"));

		serviceOccupationCommands = init("service/OccupationCommands");
		occupationCommandNodes.put(OccupationLevel.FREE, init("service/FREE"));
		occupationCommandNodes.put(OccupationLevel.OCCUPIED, init("service/OCCUPIED"));
		occupationCommandNodes.put(OccupationLevel.PRIORITY, init("service/PRIORITY"));
		occupationCommandNodes.put(OccupationLevel.LOCAL, init("service/LOCAL"));

		serviceExecutionModes = init("service/ExecutionModes");
		executionModeNodes.put(ExecutionMode.CHANGE_OVER, init("service/CHANGE_OVER"));
		executionModeNodes.put(ExecutionMode.CLEAN, init("service/CLEAN"));
		executionModeNodes.put(ExecutionMode.EMPTY_OUT, init("service/EMPTY_OUT"));
		executionModeNodes.put(ExecutionMode.MAINTENANCE, init("service/MAINTENANCE"));
		executionModeNodes.put(ExecutionMode.MANUAL, init("service/MANUAL"));
		executionModeNodes.put(ExecutionMode.PRODUCTION, init("service/PRODUCTION"));
		executionModeNodes.put(ExecutionMode.SET_UP, init("service/SET_UP"));
		executionModeNodes.put(ExecutionMode.SIMULATION, init("service/SIMULATION"));

	};

//	private NodeId init(int value) {
//		return new NodeId(Unsigned.ushort(namespaceIndex), Unsigned.uint(value));
//	}

	private NodeId init(String value) {
		return new NodeId(Unsigned.ushort(namespaceIndex), componentId + "/" + value);
	}

}
