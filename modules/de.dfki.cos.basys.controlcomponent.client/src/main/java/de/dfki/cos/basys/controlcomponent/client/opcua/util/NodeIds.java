package de.dfki.cos.basys.controlcomponent.client.opcua.util;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import de.dfki.cos.basys.controlcomponent.util.Strings;

public class NodeIds {

    public static final String NAMESPACE_URI = Strings.getString("ControlComponent.NS");
    
	public static NodeId ControlComponentType;
	public static NodeId ControlComponentType_STATUS;
	public static NodeId ControlComponentType_STATUS_ERRCODE;
	public static NodeId ControlComponentType_STATUS_ERRMSG;
	public static NodeId ControlComponentType_STATUS_EXMODE;
	public static NodeId ControlComponentType_STATUS_EXST;
	public static NodeId ControlComponentType_STATUS_OCCST;
	public static NodeId ControlComponentType_STATUS_OCCUPIER;
	public static NodeId ControlComponentType_STATUS_OPMODE;
	public static NodeId ControlComponentType_STATUS_WORKST;

	public static NodeId ControlComponentType_VARIABLES;

	public static NodeId ControlComponentType_OPERATIONS;
	public static NodeId ControlComponentType_OPERATIONS_RESET;
	public static NodeId ControlComponentType_OPERATIONS_START;
	public static NodeId ControlComponentType_OPERATIONS_STOP;
	public static NodeId ControlComponentType_OPERATIONS_HOLD;
	public static NodeId ControlComponentType_OPERATIONS_UNHOLD;
	public static NodeId ControlComponentType_OPERATIONS_SUSPEND;
	public static NodeId ControlComponentType_OPERATIONS_UNSUSPEND;
	public static NodeId ControlComponentType_OPERATIONS_ABORT;
	public static NodeId ControlComponentType_OPERATIONS_CLEAR;
	public static NodeId ControlComponentType_OPERATIONS_FREE;
	public static NodeId ControlComponentType_OPERATIONS_OCCUPY;
	public static NodeId ControlComponentType_OPERATIONS_PRIO;
	public static NodeId ControlComponentType_OPERATIONS_AUTO;
	public static NodeId ControlComponentType_OPERATIONS_SEMIAUTO;
	public static NodeId ControlComponentType_OPERATIONS_MANUAL;
	public static NodeId ControlComponentType_OPERATIONS_SIMULATE;

	public static NodeId StatusType;
	public static NodeId StatusType_ERRCODE;
	public static NodeId StatusType_ERRMSG;
	public static NodeId StatusType_EXMODE;
	public static NodeId StatusType_EXST;
	public static NodeId StatusType_OCCST;
	public static NodeId StatusType_OCCUPIER;
	public static NodeId StatusType_OPMODE;
	public static NodeId StatusType_WORKST;

	public static NodeId StatusDataType;
	public static NodeId StatusDataType_Description_Encoding_DefaultBinary;
	public static NodeId StatusDataType_Description_Encoding_DefaultXml;
	public static NodeId StatusDataType_Encoding_DefaultBinary;
	public static NodeId StatusDataType_Encoding_DefaultXml;


	public static NodeId OperationsType;
	public static NodeId OperationsType_RESET;
	public static NodeId OperationsType_START;
	public static NodeId OperationsType_STOP;
	public static NodeId OperationsType_HOLD;
	public static NodeId OperationsType_UNHOLD;
	public static NodeId OperationsType_SUSPEND;
	public static NodeId OperationsType_UNSUSPEND;
	public static NodeId OperationsType_ABORT;
	public static NodeId OperationsType_CLEAR;
	public static NodeId OperationsType_FREE;
	public static NodeId OperationsType_OCCUPY;
	public static NodeId OperationsType_PRIO;
	public static NodeId OperationsType_AUTO;
	public static NodeId OperationsType_SEMIAUTO;
	public static NodeId OperationsType_MANUAL;
	public static NodeId OperationsType_SIMULATE;
	
	
	
	public static void initNodeIds(UShort nsIndex) {
		i = 0;
		
		ControlComponentType = newNodeId(nsIndex);
    	ControlComponentType_STATUS = newNodeId(nsIndex);
    	ControlComponentType_STATUS_ERRCODE = newNodeId(nsIndex);
    	ControlComponentType_STATUS_ERRMSG = newNodeId(nsIndex);
    	ControlComponentType_STATUS_EXMODE = newNodeId(nsIndex);
    	ControlComponentType_STATUS_EXST = newNodeId(nsIndex);
    	ControlComponentType_STATUS_OCCST = newNodeId(nsIndex);
    	ControlComponentType_STATUS_OCCUPIER = newNodeId(nsIndex);
    	ControlComponentType_STATUS_OPMODE = newNodeId(nsIndex);
    	ControlComponentType_STATUS_WORKST = newNodeId(nsIndex);  

    	StatusType = newNodeId(nsIndex);
    	StatusType_ERRCODE = newNodeId(nsIndex);
    	StatusType_ERRMSG = newNodeId(nsIndex);
    	StatusType_EXMODE = newNodeId(nsIndex);
    	StatusType_EXST = newNodeId(nsIndex);
    	StatusType_OCCST = newNodeId(nsIndex);
    	StatusType_OCCUPIER = newNodeId(nsIndex);
    	StatusType_OPMODE = newNodeId(nsIndex);
    	StatusType_WORKST = newNodeId(nsIndex);
        
      	StatusDataType = newNodeId(nsIndex);
    	StatusDataType_Description_Encoding_DefaultBinary = newNodeId(nsIndex);
    	StatusDataType_Description_Encoding_DefaultXml = newNodeId(nsIndex);
    	StatusDataType_Encoding_DefaultBinary = newNodeId(nsIndex);
    	StatusDataType_Encoding_DefaultXml = newNodeId(nsIndex);	
    	
    	ControlComponentType_VARIABLES = newNodeId(nsIndex);

    	ControlComponentType_OPERATIONS = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_RESET = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_START = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_STOP = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_HOLD = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_UNHOLD = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_SUSPEND = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_UNSUSPEND = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_ABORT = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_CLEAR = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_FREE = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_OCCUPY = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_PRIO = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_AUTO = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_SEMIAUTO = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_MANUAL = newNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_SIMULATE = newNodeId(nsIndex);
    	
    	OperationsType = newNodeId(nsIndex);
    	OperationsType_RESET = newNodeId(nsIndex);
    	OperationsType_START = newNodeId(nsIndex);
    	OperationsType_STOP = newNodeId(nsIndex);
    	OperationsType_HOLD = newNodeId(nsIndex);
    	OperationsType_UNHOLD = newNodeId(nsIndex);
    	OperationsType_SUSPEND = newNodeId(nsIndex);
    	OperationsType_UNSUSPEND = newNodeId(nsIndex);
    	OperationsType_ABORT = newNodeId(nsIndex);
    	OperationsType_CLEAR = newNodeId(nsIndex);
    	OperationsType_FREE = newNodeId(nsIndex);
    	OperationsType_OCCUPY = newNodeId(nsIndex);
    	OperationsType_PRIO = newNodeId(nsIndex);
    	OperationsType_AUTO = newNodeId(nsIndex);
    	OperationsType_SEMIAUTO = newNodeId(nsIndex);
    	OperationsType_MANUAL = newNodeId(nsIndex);
    	OperationsType_SIMULATE = newNodeId(nsIndex);
	};
	
	private static int i = 0;
	
	private static NodeId newNodeId(UShort nsIndex) {
		return new NodeId(nsIndex, i++);
	}
}