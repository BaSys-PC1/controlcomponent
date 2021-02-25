package de.dfki.cos.basys.controlcomponent.client.opcua.util;

import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import de.dfki.cos.basys.controlcomponent.util.Strings;

public class NodeIds {

    public static final String NAMESPACE_URI = Strings.getString("ControlComponent.NS");
    
	public static ExpandedNodeId ControlComponentType;
	public static ExpandedNodeId ControlComponentType_STATUS;
	public static ExpandedNodeId ControlComponentType_STATUS_ERRCODE;
	public static ExpandedNodeId ControlComponentType_STATUS_ERRMSG;
	public static ExpandedNodeId ControlComponentType_STATUS_EXMODE;
	public static ExpandedNodeId ControlComponentType_STATUS_EXST;
	public static ExpandedNodeId ControlComponentType_STATUS_OCCST;
	public static ExpandedNodeId ControlComponentType_STATUS_OCCUPIER;
	public static ExpandedNodeId ControlComponentType_STATUS_OPMODE;
	public static ExpandedNodeId ControlComponentType_STATUS_WORKST;

	public static ExpandedNodeId ControlComponentType_VARIABLES;

	public static ExpandedNodeId ControlComponentType_OPERATIONS;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_RESET;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_START;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_STOP;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_HOLD;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_UNHOLD;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_SUSPEND;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_UNSUSPEND;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_ABORT;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_CLEAR;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_FREE;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_OCCUPY;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_PRIO;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_AUTO;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_SEMIAUTO;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_MANUAL;
	public static ExpandedNodeId ControlComponentType_OPERATIONS_SIMULATE;

	public static ExpandedNodeId StatusType;
	public static ExpandedNodeId StatusType_ERRCODE;
	public static ExpandedNodeId StatusType_ERRMSG;
	public static ExpandedNodeId StatusType_EXMODE;
	public static ExpandedNodeId StatusType_EXST;
	public static ExpandedNodeId StatusType_OCCST;
	public static ExpandedNodeId StatusType_OCCUPIER;
	public static ExpandedNodeId StatusType_OPMODE;
	public static ExpandedNodeId StatusType_WORKST;

	public static ExpandedNodeId StatusDataType;
	public static ExpandedNodeId StatusDataType_Description_Encoding_DefaultBinary;
	public static ExpandedNodeId StatusDataType_Description_Encoding_DefaultXml;
	public static ExpandedNodeId StatusDataType_Encoding_DefaultBinary;
	public static ExpandedNodeId StatusDataType_Encoding_DefaultXml;


	public static ExpandedNodeId OperationsType;
	public static ExpandedNodeId OperationsType_RESET;
	public static ExpandedNodeId OperationsType_START;
	public static ExpandedNodeId OperationsType_STOP;
	public static ExpandedNodeId OperationsType_HOLD;
	public static ExpandedNodeId OperationsType_UNHOLD;
	public static ExpandedNodeId OperationsType_SUSPEND;
	public static ExpandedNodeId OperationsType_UNSUSPEND;
	public static ExpandedNodeId OperationsType_ABORT;
	public static ExpandedNodeId OperationsType_CLEAR;
	public static ExpandedNodeId OperationsType_FREE;
	public static ExpandedNodeId OperationsType_OCCUPY;
	public static ExpandedNodeId OperationsType_PRIO;
	public static ExpandedNodeId OperationsType_AUTO;
	public static ExpandedNodeId OperationsType_SEMIAUTO;
	public static ExpandedNodeId OperationsType_MANUAL;
	public static ExpandedNodeId OperationsType_SIMULATE;
	
	
	
	public static void initNodeIds(UShort nsIndex) {
		i = 0;
		
		ControlComponentType = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_ERRCODE = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_ERRMSG = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_EXMODE = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_EXST = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_OCCST = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_OCCUPIER = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_OPMODE = newExpandedNodeId(nsIndex);
    	ControlComponentType_STATUS_WORKST = newExpandedNodeId(nsIndex);  

    	StatusType = newExpandedNodeId(nsIndex);
    	StatusType_ERRCODE = newExpandedNodeId(nsIndex);
    	StatusType_ERRMSG = newExpandedNodeId(nsIndex);
    	StatusType_EXMODE = newExpandedNodeId(nsIndex);
    	StatusType_EXST = newExpandedNodeId(nsIndex);
    	StatusType_OCCST = newExpandedNodeId(nsIndex);
    	StatusType_OCCUPIER = newExpandedNodeId(nsIndex);
    	StatusType_OPMODE = newExpandedNodeId(nsIndex);
    	StatusType_WORKST = newExpandedNodeId(nsIndex);
        
      	StatusDataType = newExpandedNodeId(nsIndex);
    	StatusDataType_Description_Encoding_DefaultBinary = newExpandedNodeId(nsIndex);
    	StatusDataType_Description_Encoding_DefaultXml = newExpandedNodeId(nsIndex);
    	StatusDataType_Encoding_DefaultBinary = newExpandedNodeId(nsIndex);
    	StatusDataType_Encoding_DefaultXml = newExpandedNodeId(nsIndex);	
    	
    	ControlComponentType_VARIABLES = newExpandedNodeId(nsIndex);

    	ControlComponentType_OPERATIONS = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_RESET = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_START = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_STOP = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_HOLD = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_UNHOLD = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_SUSPEND = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_UNSUSPEND = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_ABORT = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_CLEAR = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_FREE = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_OCCUPY = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_PRIO = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_AUTO = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_SEMIAUTO = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_MANUAL = newExpandedNodeId(nsIndex);
    	ControlComponentType_OPERATIONS_SIMULATE = newExpandedNodeId(nsIndex);
    	
    	OperationsType = newExpandedNodeId(nsIndex);
    	OperationsType_RESET = newExpandedNodeId(nsIndex);
    	OperationsType_START = newExpandedNodeId(nsIndex);
    	OperationsType_STOP = newExpandedNodeId(nsIndex);
    	OperationsType_HOLD = newExpandedNodeId(nsIndex);
    	OperationsType_UNHOLD = newExpandedNodeId(nsIndex);
    	OperationsType_SUSPEND = newExpandedNodeId(nsIndex);
    	OperationsType_UNSUSPEND = newExpandedNodeId(nsIndex);
    	OperationsType_ABORT = newExpandedNodeId(nsIndex);
    	OperationsType_CLEAR = newExpandedNodeId(nsIndex);
    	OperationsType_FREE = newExpandedNodeId(nsIndex);
    	OperationsType_OCCUPY = newExpandedNodeId(nsIndex);
    	OperationsType_PRIO = newExpandedNodeId(nsIndex);
    	OperationsType_AUTO = newExpandedNodeId(nsIndex);
    	OperationsType_SEMIAUTO = newExpandedNodeId(nsIndex);
    	OperationsType_MANUAL = newExpandedNodeId(nsIndex);
    	OperationsType_SIMULATE = newExpandedNodeId(nsIndex);
	};
	
	private static int i = 0;
	
	private static NodeId newNodeId(UShort nsIndex) {
		return new NodeId(nsIndex, i++);
	}
	
	private static ExpandedNodeId newExpandedNodeId(UShort nsIndex) {
		return new ExpandedNodeId(nsIndex, NAMESPACE_URI, UInteger.valueOf(i++));
	}
}