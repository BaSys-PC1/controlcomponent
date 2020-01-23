package de.dfki.cos.basys.controlcomponent.server.opcua.util;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class NodeIds {

    public static final String NAMESPACE_URI = "http://www.basys40.de/controlcomponent";
    
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

	public static void initNodeIds(UShort nsIndex) {
		ControlComponentType = new NodeId(nsIndex, 0);     
    	ControlComponentType_STATUS = new NodeId(nsIndex, 1);
    	ControlComponentType_STATUS_ERRCODE = new NodeId(nsIndex, 2);
    	ControlComponentType_STATUS_ERRMSG = new NodeId(nsIndex, 3);
    	ControlComponentType_STATUS_EXMODE = new NodeId(nsIndex, 4);
    	ControlComponentType_STATUS_EXST = new NodeId(nsIndex, 5);
    	ControlComponentType_STATUS_OCCST = new NodeId(nsIndex, 6);
    	ControlComponentType_STATUS_OCCUPIER = new NodeId(nsIndex, 7);
    	ControlComponentType_STATUS_OPMODE = new NodeId(nsIndex, 8);
    	ControlComponentType_STATUS_WORKST = new NodeId(nsIndex, 9);   

    	StatusType = new NodeId(nsIndex, 10);
    	StatusType_ERRCODE = new NodeId(nsIndex, 11);
    	StatusType_ERRMSG = new NodeId(nsIndex, 12);
    	StatusType_EXMODE = new NodeId(nsIndex, 13);
    	StatusType_EXST = new NodeId(nsIndex, 14);
    	StatusType_OCCST = new NodeId(nsIndex, 15);
    	StatusType_OCCUPIER = new NodeId(nsIndex, 16);
    	StatusType_OPMODE = new NodeId(nsIndex, 17);
    	StatusType_WORKST = new NodeId(nsIndex, 18);
        
      	StatusDataType = new NodeId(nsIndex, 19);
    	StatusDataType_Description_Encoding_DefaultBinary = new NodeId(nsIndex, 20);
    	StatusDataType_Description_Encoding_DefaultXml = new NodeId(nsIndex, 21);
    	StatusDataType_Encoding_DefaultBinary = new NodeId(nsIndex, 22);
    	StatusDataType_Encoding_DefaultXml = new NodeId(nsIndex, 23);    	
	};
}