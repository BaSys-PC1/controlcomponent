package de.dfki.cos.basys.controlcomponent.opcua.loader;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDescriptionNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import de.dfki.cos.basys.controlcomponent.opcua.ControlComponentNamespace;
import de.dfki.cos.basys.controlcomponent.opcua.objects.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.opcua.objects.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.opcua.types.ControlComponentStatusDataType;

public class ControlComponentVariableTypeLoader {

	private final UaNodeContext context;
	private final NodeManager<UaNode> nodeManager;
	private final UShort nsIndex;

    private final Object[][] ControlComponentType_VARIABLE_NODES = new Object[][]{
    	{ControlComponentNamespace.ControlComponentType_STATUS, "STATUS", "Status", ControlComponentNamespace.StatusDataType, new Variant(null)},  	    	
    };    
	
    private final Object[][] ControlComponentType_STATUS_VARIABLE_NODES = new Object[][]{
    	{ControlComponentNamespace.ControlComponentType_STATUS_ERRCODE, "ERRCODE", "ErrorCode", Identifiers.Integer, new Variant(0)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_ERRMSG, "ERRMSG", "ErrorMessage", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_EXMODE, "EXMODE", "ExecutionMode", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_EXST, "EXST", "ExecutionState", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_OCCST, "OCCST", "OccupationState", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_OCCUPIER, "OCCUPIER", "OccupierId", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_OPMODE, "OPMODE", "OperationMode", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.ControlComponentType_STATUS_WORKST, "WORKST", "WorkState", Identifiers.String, new Variant(null)},    	
    }; 
    
    private final Object[][] StatusType_VARIABLE_NODES = new Object[][]{
    	{ControlComponentNamespace.StatusType_ERRCODE, "ERRCODE", "ErrorCode", Identifiers.Integer, new Variant(0)},
    	{ControlComponentNamespace.StatusType_ERRMSG, "ERRMSG", "ErrorMessage", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.StatusType_EXMODE, "EXMODE", "ExecutionMode", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.StatusType_EXST, "EXST", "ExecutionState", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.StatusType_OCCST, "OCCST", "OccupationState", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.StatusType_OCCUPIER, "OCCUPIER", "OccupierId", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.StatusType_OPMODE, "OPMODE", "OperationMode", Identifiers.String, new Variant(null)},
    	{ControlComponentNamespace.StatusType_WORKST, "WORKST", "WorkState", Identifiers.String, new Variant(null)},    	
    };    
	
	public ControlComponentVariableTypeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager, UShort nsIndex) {
		this.context = context;
		this.nodeManager = nodeManager;
		this.nsIndex = nsIndex;
	}

	public void buildNodes() {
		buildControlComponentType();
		
		for (Object[] var : ControlComponentType_VARIABLE_NODES) {
			buildControlComponentVariableNode(var, ControlComponentNamespace.ControlComponentType);
		}
		
		for (Object[] var : ControlComponentType_STATUS_VARIABLE_NODES) {
			buildVariableNode(var, ControlComponentNamespace.ControlComponentType_STATUS);
		}
		
		buildStatusTypeNode();	
		
		for (Object[] var : StatusType_VARIABLE_NODES) {
			buildVariableNode(var, ControlComponentNamespace.StatusType);
		}		

		buildStatusDataTypeNode();
	}
	
	private void buildControlComponentType() {
        UaObjectTypeNode node = new UaObjectTypeNode(context, ControlComponentNamespace.ControlComponentType, 
        		new QualifiedName(nsIndex,"ControlComponentType"), LocalizedText.english("ControlComponentType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);

        node.addReference(new Reference(node.getNodeId(), Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));

        nodeManager.addNode(node);
        
        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
		context.getServer().getObjectTypeManager().registerObjectType(node.getNodeId(), ControlComponentNode.class,	ControlComponentNode::new); 		
	}
	
	private void buildVariableNode(Object[] var, NodeId containerNode) {
		NodeId id = (NodeId)var[0];
		String browseName = (String)var[1];
		String displayName = (String)var[2];
		NodeId dataType = (NodeId)var[3];
		Variant variant = (Variant)var[4];
		
		UaVariableNode node = new BaseDataVariableNode(context,
				id, new QualifiedName(nsIndex, browseName), LocalizedText.english(displayName), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
				new DataValue(variant), dataType, ValueRank.Scalar.getValue(), new UInteger[]{}, 
				ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);
        
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasComponent, containerNode.expanded(), false));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.BaseDataVariableType.expanded(), true));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
		
		nodeManager.addNode(node);
	}

	private void buildControlComponentVariableNode(Object[] var, NodeId containerNode) {
		NodeId id = (NodeId)var[0];
		String browseName = (String)var[1];
		String displayName = (String)var[2];
		NodeId dataType = (NodeId)var[3];
		Variant variant = (Variant)var[4];
		
		UaVariableNode node = new BaseDataVariableNode(context,
				id, new QualifiedName(nsIndex, browseName), LocalizedText.english(displayName), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
				new DataValue(variant), dataType, ValueRank.Scalar.getValue(), new UInteger[]{}, 
				ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);
        
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasComponent, containerNode.expanded(), false));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasTypeDefinition, ControlComponentNamespace.StatusType.expanded(), true));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
		
		nodeManager.addNode(node);
	}
	
	private void buildStatusDataTypeNode() {
	  	    
        UaObjectNode binaryEncodingNode = new DataTypeEncodingNode(context, ControlComponentNamespace.StatusDataType_Encoding_DefaultBinary, 
        		new QualifiedName(nsIndex, "Default Binary"), LocalizedText.english("Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), UByte.valueOf(0));
        binaryEncodingNode.addReference(new Reference(binaryEncodingNode.getNodeId(), Identifiers.HasEncoding, ControlComponentNamespace.StatusDataType.expanded(), false));
        binaryEncodingNode.addReference(new Reference(binaryEncodingNode.getNodeId(), Identifiers.HasDescription, ControlComponentNamespace.StatusDataType_Description_Encoding_DefaultBinary.expanded(), true));
        binaryEncodingNode.addReference(new Reference(binaryEncodingNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        nodeManager.addNode(binaryEncodingNode);

        UaObjectNode xmlEncodingNode = new DataTypeEncodingNode(context, ControlComponentNamespace.StatusDataType_Encoding_DefaultXml, 
        		new QualifiedName(nsIndex, "Default XML"), LocalizedText.english("Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), UByte.valueOf(0));
        xmlEncodingNode.addReference(new Reference(xmlEncodingNode.getNodeId(), Identifiers.HasEncoding, ControlComponentNamespace.StatusDataType.expanded(), false));
        xmlEncodingNode.addReference(new Reference(xmlEncodingNode.getNodeId(), Identifiers.HasDescription, ControlComponentNamespace.StatusDataType_Description_Encoding_DefaultXml.expanded(), true));
        xmlEncodingNode.addReference(new Reference(xmlEncodingNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        nodeManager.addNode(xmlEncodingNode);
           
    	UaVariableNode binaryDescNode = new DataTypeDescriptionNode(context, ControlComponentNamespace.StatusDataType_Description_Encoding_DefaultBinary,
    			new QualifiedName(nsIndex, "ControlComponentStatusDataType"), LocalizedText.english("ControlComponentStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L),
    			new DataValue(Variant.NULL_VALUE), Identifiers.String, ValueRank.Scalar.getValue(), new UInteger[]{}, 
    			ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);    	
    	binaryDescNode.addReference(new Reference(binaryDescNode.getNodeId(), Identifiers.HasDescription, ControlComponentNamespace.StatusDataType_Encoding_DefaultBinary.expanded(), false));
    	binaryDescNode.addReference(new Reference(binaryDescNode.getNodeId(), Identifiers.HasComponent, Identifiers.OpcUa_BinarySchema.expanded(), false));
    	binaryDescNode.addReference(new Reference(binaryDescNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeDescriptionType.expanded(), true));
    	nodeManager.addNode(binaryDescNode);        
    	
    	UaVariableNode xmlDescNode = new DataTypeDescriptionNode(context, ControlComponentNamespace.StatusDataType_Description_Encoding_DefaultXml, 
    			new QualifiedName(nsIndex, "ControlComponentStatusDataType"), LocalizedText.english("ControlComponentStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
    			new DataValue(Variant.NULL_VALUE), Identifiers.String, ValueRank.Scalar.getValue(), new UInteger[]{}, 
    			ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);
    	xmlDescNode.addReference(new Reference(xmlDescNode.getNodeId(), Identifiers.HasDescription, ControlComponentNamespace.StatusDataType_Encoding_DefaultXml.expanded(),  false));
    	xmlDescNode.addReference(new Reference(xmlDescNode.getNodeId(), Identifiers.HasComponent, Identifiers.OpcUa_XmlSchema.expanded(), false));
    	xmlDescNode.addReference(new Reference(xmlDescNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeDescriptionType.expanded(), true));
    	nodeManager.addNode(xmlDescNode);
    	    	
    	context.getServer().getDataTypeManager().registerCodec(ControlComponentNamespace.StatusDataType_Encoding_DefaultBinary, new ControlComponentStatusDataType.Codec().asBinaryCodec());
    	context.getServer().getDataTypeManager().registerCodec(ControlComponentNamespace.StatusDataType_Encoding_DefaultXml, new ControlComponentStatusDataType.Codec().asXmlCodec());

    	
    	UaDataTypeNode statusDataTypeNode = new UaDataTypeNode(context,	ControlComponentNamespace.StatusDataType, 
    			new QualifiedName(nsIndex,"ControlComponentStatusDataType"), LocalizedText.english("ControlComponentStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
    	statusDataTypeNode.addReference(new Reference(statusDataTypeNode.getNodeId(), Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
    	statusDataTypeNode.addReference(new Reference(statusDataTypeNode.getNodeId(), Identifiers.HasEncoding, ControlComponentNamespace.StatusDataType_Encoding_DefaultBinary.expanded(), true));
    	statusDataTypeNode.addReference(new Reference(statusDataTypeNode.getNodeId(), Identifiers.HasEncoding, ControlComponentNamespace.StatusDataType_Encoding_DefaultXml.expanded(), true));
    	nodeManager.addNode(statusDataTypeNode);   	
    	
	}
	
	private void buildStatusTypeNode() {
    	UaVariableTypeNode node = new UaVariableTypeNode(context,
    			ControlComponentNamespace.StatusType, new QualifiedName(nsIndex, "ControlComponentStatusType"), LocalizedText.english("ControlComponentStatusType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
    			new DataValue(Variant.NULL_VALUE), ControlComponentNamespace.StatusDataType, ValueRank.Scalar.getValue(), new UInteger[]{}, false);    	
    	node.addReference(new Reference(node.getNodeId(), Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));        
        nodeManager.addNode(node);
        
    	context.getServer().getVariableTypeManager().registerVariableType(node.getNodeId(), ControlComponentStatusNode.class, ControlComponentStatusNode::new);
	}
}