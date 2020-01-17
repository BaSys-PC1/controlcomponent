package de.dfki.cos.basys.controlcomponent.client.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OccupationState;

public class NodeIds {

	public int namespaceIndex = 2;
	public String componentId = null;

	public NodeId folderRoot;
	public NodeId folderControlComponent;
	public NodeId folderStatus;
	public NodeId folderVariables;
	public NodeId folderServices;
	public NodeId folderExecutionCommandServices;
	public NodeId folderExecutionModeServices;
	public NodeId folderOccupationCommandServices;
	public NodeId folderOperationModeServices;

	public NodeId statusOccupationState;
	public NodeId statusOccupierId;
	public NodeId statusExecutionMode;
	public NodeId statusExecutionState;
	public NodeId statusOperationMode;
	public NodeId statusWorkState;
	public NodeId statusErrorCode;
	public NodeId statusErrorMessage;	
	
	public Map<OccupationState, NodeId> occupationCommandNodes = new HashMap<>();
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
		folderRoot =  new NodeId(Unsigned.ushort(namespaceIndex), componentId);
		folderControlComponent = newHierarchicalNodeId(folderRoot, "ControlComponent");
		folderStatus = newHierarchicalNodeId(folderControlComponent, "status");
		folderVariables = newHierarchicalNodeId(folderControlComponent, "variable");
		folderServices = newHierarchicalNodeId(folderControlComponent, "service");
		folderExecutionCommandServices = newHierarchicalNodeId(folderServices, "ExecutionCommands");
		folderExecutionModeServices = newHierarchicalNodeId(folderServices, "ExecutionModes");
		folderOccupationCommandServices = newHierarchicalNodeId(folderServices, "OccupationCommands");
		folderOperationModeServices = newHierarchicalNodeId(folderServices, "OperationModes");
		
		statusOccupierId = newHierarchicalNodeId(folderStatus,"OccupierId");
		statusOccupationState = newHierarchicalNodeId(folderStatus,"OccupationState");
		statusExecutionMode = newHierarchicalNodeId(folderStatus,"ExecutionMode");
		statusExecutionState = newHierarchicalNodeId(folderStatus,"ExecutionState");
		statusOperationMode = newHierarchicalNodeId(folderStatus,"OperationMode");
		statusWorkState = newHierarchicalNodeId(folderStatus,"WorkState");
		statusErrorCode = newHierarchicalNodeId(folderStatus,"ErrorCode");
		statusErrorMessage = newHierarchicalNodeId(folderStatus,"ErrorMessage");

		for (ExecutionCommand command : ExecutionCommand.values()) {
			executionCommandNodes.put(command, newHierarchicalNodeId(folderServices,command.getName()));
		}
		
		for (OccupationState level : OccupationState.values()) {
			occupationCommandNodes.put(level, newHierarchicalNodeId(folderServices,level.getName()));
		}
		
		for (ExecutionMode mode : ExecutionMode.values()) {
			executionModeNodes.put(mode, newHierarchicalNodeId(folderServices,mode.getName()));
		}		

	};

    public NodeId newHierarchicalNodeId(NodeId parent, String id) {
    	return new NodeId(Unsigned.ushort(namespaceIndex), parent.getIdentifier().toString() + "/" + id);
    }

	public NodeId getOperationModeNode(String mode) {
		return newHierarchicalNodeId(folderServices,mode);
	}  
}
