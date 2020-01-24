package de.dfki.cos.basys.controlcomponent.server.opcua.loader;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

import org.eclipse.milo.opcua.sdk.client.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.Node;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.DataTypeEncodingNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.BaseDataVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.DataTypeDescriptionNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaPropertyNode;
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
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

import de.dfki.cos.basys.controlcomponent.server.opcua.ControlComponentNamespace;
import de.dfki.cos.basys.controlcomponent.server.opcua.methods.OperationsMethodInvocationHandler;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentStatusDataType;
import de.dfki.cos.basys.controlcomponent.server.opcua.util.NodeIds;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentVariableTypeLoader {

	private final UaNodeContext context;
	private final NodeManager<UaNode> nodeManager;
	private final UShort nsIndex;

    private final Object[][] ControlComponentType_VARIABLE_NODES = new Object[][]{
    	{NodeIds.ControlComponentType_STATUS, 		Strings.getString("ControlComponent.BN.Status"), Strings.getString("ControlComponent.DN.Status"), 			NodeIds.StatusDataType, new Variant(null)}, 
    	{NodeIds.ControlComponentType_OPERATIONS, 	Strings.getString("ControlComponent.BN.Operations"), Strings.getString("ControlComponent.DN.Operations"),	NodeIds.StatusDataType}, 
    	{NodeIds.ControlComponentType_VARIABLES,	Strings.getString("ControlComponent.BN.Variables"), Strings.getString("ControlComponent.DN.Variables"), 	NodeIds.StatusDataType},  	    	
    };    
	

    private final Object[][] ControlComponentType_STATUS_VARIABLE_NODES = new Object[][]{
    	{NodeIds.ControlComponentType_STATUS_ERRCODE,  Strings.getString("ControlComponent.BN.ErrorCode"), Strings.getString("ControlComponent.DN.ErrorCode"), 				Identifiers.Integer, new Variant(0)},
    	{NodeIds.ControlComponentType_STATUS_ERRMSG,   Strings.getString("ControlComponent.BN.ErrorMessage"), Strings.getString("ControlComponent.DN.ErrorMessage"), 		Identifiers.String, new Variant(null)},
    	{NodeIds.ControlComponentType_STATUS_EXMODE,   Strings.getString("ControlComponent.BN.ExecutionMode"), Strings.getString("ControlComponent.DN.ExecutionMode"), 		Identifiers.String, new Variant(null)},
    	{NodeIds.ControlComponentType_STATUS_EXST,     Strings.getString("ControlComponent.BN.ExecutionState"), Strings.getString("ControlComponent.DN.ExecutionState"), 	Identifiers.String, new Variant(null)},
    	{NodeIds.ControlComponentType_STATUS_OCCST,    Strings.getString("ControlComponent.BN.OccupationState"), Strings.getString("ControlComponent.DN.OccupationState"), 	Identifiers.String, new Variant(null)},
    	{NodeIds.ControlComponentType_STATUS_OCCUPIER, Strings.getString("ControlComponent.BN.OccupierId"), Strings.getString("ControlComponent.DN.OccupierId"), 			Identifiers.String, new Variant(null)},
    	{NodeIds.ControlComponentType_STATUS_OPMODE,   Strings.getString("ControlComponent.BN.OperationMode"), Strings.getString("ControlComponent.DN.OperationMode"), 		Identifiers.String, new Variant(null)},
    	{NodeIds.ControlComponentType_STATUS_WORKST,   Strings.getString("ControlComponent.BN.WorkState"), Strings.getString("ControlComponent.DN.WorkState"), 				Identifiers.String, new Variant(null)},    	
    }; 
    
    private final Object[][] StatusType_VARIABLE_NODES = new Object[][]{
    	{NodeIds.StatusType_ERRCODE, 	Strings.getString("ControlComponent.BN.ErrorCode"), Strings.getString("ControlComponent.DN.ErrorCode"), 			Identifiers.Integer, new Variant(0)},
    	{NodeIds.StatusType_ERRMSG, 	Strings.getString("ControlComponent.BN.ErrorMessage"), Strings.getString("ControlComponent.DN.ErrorMessage"), 		Identifiers.String, new Variant(null)},
    	{NodeIds.StatusType_EXMODE, 	Strings.getString("ControlComponent.BN.ExecutionMode"), Strings.getString("ControlComponent.DN.ExecutionMode"), 	Identifiers.String, new Variant(null)},
    	{NodeIds.StatusType_EXST, 		Strings.getString("ControlComponent.BN.ExecutionState"), Strings.getString("ControlComponent.DN.ExecutionState"), 	Identifiers.String, new Variant(null)},
    	{NodeIds.StatusType_OCCST, 		Strings.getString("ControlComponent.BN.OccupationState"), Strings.getString("ControlComponent.DN.OccupationState"), Identifiers.String, new Variant(null)},
    	{NodeIds.StatusType_OCCUPIER, 	Strings.getString("ControlComponent.BN.OccupierId"), Strings.getString("ControlComponent.DN.OccupierId"), 			Identifiers.String, new Variant(null)},
    	{NodeIds.StatusType_OPMODE, 	Strings.getString("ControlComponent.BN.OperationMode"), Strings.getString("ControlComponent.DN.OperationMode"), 	Identifiers.String, new Variant(null)},
    	{NodeIds.StatusType_WORKST, 	Strings.getString("ControlComponent.BN.WorkState"), Strings.getString("ControlComponent.DN.WorkState"), 			Identifiers.String, new Variant(null)},    	
    };    
	
    private final Object[][] ControlComponentType_OPERATIONS_METHODS_NODES = new Object[][]{
    	{NodeIds.ControlComponentType_OPERATIONS_RESET, 	Strings.getString("ControlComponent.BN.Reset"), Strings.getString("ControlComponent.DN.Reset") },
    	{NodeIds.ControlComponentType_OPERATIONS_START, 	Strings.getString("ControlComponent.BN.Start"), Strings.getString("ControlComponent.DN.Start") },
    	{NodeIds.ControlComponentType_OPERATIONS_STOP, 		Strings.getString("ControlComponent.BN.Stop"), Strings.getString("ControlComponent.DN.Stop") },
    	{NodeIds.ControlComponentType_OPERATIONS_HOLD, 		Strings.getString("ControlComponent.BN.Hold"), Strings.getString("ControlComponent.DN.Hold") },
    	{NodeIds.ControlComponentType_OPERATIONS_UNHOLD, 	Strings.getString("ControlComponent.BN.Unhold"), Strings.getString("ControlComponent.DN.Unhold") },
    	{NodeIds.ControlComponentType_OPERATIONS_SUSPEND, 	Strings.getString("ControlComponent.BN.Suspend"), Strings.getString("ControlComponent.DN.Suspend") },
    	{NodeIds.ControlComponentType_OPERATIONS_UNSUSPEND, Strings.getString("ControlComponent.BN.Unsuspend"), Strings.getString("ControlComponent.DN.Unsuspend") },
    	{NodeIds.ControlComponentType_OPERATIONS_ABORT, 	Strings.getString("ControlComponent.BN.Abort"), Strings.getString("ControlComponent.DN.Abort") },
    	{NodeIds.ControlComponentType_OPERATIONS_CLEAR, 	Strings.getString("ControlComponent.BN.Clear"), Strings.getString("ControlComponent.DN.Clear") },

    	{NodeIds.ControlComponentType_OPERATIONS_FREE, 		Strings.getString("ControlComponent.BN.Free"), Strings.getString("ControlComponent.DN.Free") },
    	{NodeIds.ControlComponentType_OPERATIONS_OCCUPY, 	Strings.getString("ControlComponent.BN.Occupy"), Strings.getString("ControlComponent.DN.Occupy") },
    	{NodeIds.ControlComponentType_OPERATIONS_PRIO, 		Strings.getString("ControlComponent.BN.Prio"), Strings.getString("ControlComponent.DN.Prio") },

    	{NodeIds.ControlComponentType_OPERATIONS_AUTO, 		Strings.getString("ControlComponent.BN.Auto"), Strings.getString("ControlComponent.DN.Auto") },
    	{NodeIds.ControlComponentType_OPERATIONS_SEMIAUTO, 	Strings.getString("ControlComponent.BN.SemiAuto"), Strings.getString("ControlComponent.DN.SemiAuto") },
    	{NodeIds.ControlComponentType_OPERATIONS_MANUAL, 	Strings.getString("ControlComponent.BN.Manual"), Strings.getString("ControlComponent.DN.Manual") },
    	{NodeIds.ControlComponentType_OPERATIONS_SIMULATE, 	Strings.getString("ControlComponent.BN.Simulate"), Strings.getString("ControlComponent.DN.Simulate") },
    };    
	
    private final Object[][] OperationsType_METHODS_NODES = new Object[][]{
    	{NodeIds.OperationsType_RESET, 		Strings.getString("ControlComponent.BN.Reset"), Strings.getString("ControlComponent.DN.Reset") },
    	{NodeIds.OperationsType_START, 		Strings.getString("ControlComponent.BN.Start"), Strings.getString("ControlComponent.DN.Start") },
    	{NodeIds.OperationsType_STOP, 		Strings.getString("ControlComponent.BN.Stop"), Strings.getString("ControlComponent.DN.Stop") },
    	{NodeIds.OperationsType_HOLD, 		Strings.getString("ControlComponent.BN.Hold"), Strings.getString("ControlComponent.DN.Hold") },
    	{NodeIds.OperationsType_UNHOLD, 	Strings.getString("ControlComponent.BN.Unhold"), Strings.getString("ControlComponent.DN.Unhold") },
    	{NodeIds.OperationsType_SUSPEND, 	Strings.getString("ControlComponent.BN.Suspend"), Strings.getString("ControlComponent.DN.Suspend") },
    	{NodeIds.OperationsType_UNSUSPEND, 	Strings.getString("ControlComponent.BN.Unsuspend"), Strings.getString("ControlComponent.DN.Unsuspend") },
    	{NodeIds.OperationsType_ABORT, 		Strings.getString("ControlComponent.BN.Abort"), Strings.getString("ControlComponent.DN.Abort") },
    	{NodeIds.OperationsType_CLEAR, 		Strings.getString("ControlComponent.BN.Clear"), Strings.getString("ControlComponent.DN.Clear") },

    	{NodeIds.OperationsType_FREE, 		Strings.getString("ControlComponent.BN.Free"), Strings.getString("ControlComponent.DN.Free") },
    	{NodeIds.OperationsType_OCCUPY, 	Strings.getString("ControlComponent.BN.Occupy"), Strings.getString("ControlComponent.DN.Occupy") },
    	{NodeIds.OperationsType_PRIO, 		Strings.getString("ControlComponent.BN.Prio"), Strings.getString("ControlComponent.DN.Prio") },

    	{NodeIds.OperationsType_AUTO, 		Strings.getString("ControlComponent.BN.Auto"), Strings.getString("ControlComponent.DN.Auto") },
    	{NodeIds.OperationsType_SEMIAUTO, 	Strings.getString("ControlComponent.BN.SemiAuto"), Strings.getString("ControlComponent.DN.SemiAuto") },
    	{NodeIds.OperationsType_MANUAL, 	Strings.getString("ControlComponent.BN.Manual"), Strings.getString("ControlComponent.DN.Manual") },
    	{NodeIds.OperationsType_SIMULATE, 	Strings.getString("ControlComponent.BN.Simulate"), Strings.getString("ControlComponent.DN.Simulate") },
    };   
    
	public ControlComponentVariableTypeLoader(UaNodeContext context, NodeManager<UaNode> nodeManager, UShort nsIndex) {
		this.context = context;
		this.nodeManager = nodeManager;
		this.nsIndex = nsIndex;
	}

	public void buildNodes() {
		buildControlComponentType();
		buildControlComponentType_STATUS();
		buildControlComponentType_OPERATIONS();
		buildControlComponentType_VARIABLES();
		buildOperationsType();
		buildStatusTypeNode();
		buildStatusDataTypeNode();
		
		for (Object[] var : ControlComponentType_STATUS_VARIABLE_NODES) {
			buildVariableNode(var, NodeIds.ControlComponentType_STATUS);
		}
		
		for (Object[] var : ControlComponentType_OPERATIONS_METHODS_NODES) {
			buildOperationsMethodNode(var, NodeIds.ControlComponentType_OPERATIONS);
		}	
		
		for (Object[] var : OperationsType_METHODS_NODES) {
			buildOperationsMethodNode(var, NodeIds.OperationsType);
		}		
		
		for (Object[] var : StatusType_VARIABLE_NODES) {
			buildVariableNode(var, NodeIds.StatusType);
		}
	}

	private void buildControlComponentType() {
        UaObjectTypeNode node = new UaObjectTypeNode(context, NodeIds.ControlComponentType, 
        		new QualifiedName(nsIndex,"ControlComponentType"), LocalizedText.english("ControlComponentType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);

        node.addReference(new Reference(node.getNodeId(), Identifiers.HasSubtype, Identifiers.BaseObjectType.expanded(), false));

        nodeManager.addNode(node);
        
        // Tell the ObjectTypeManager about our new type.
        // This let's us use NodeFactory to instantiate instances of the type.
		context.getServer().getObjectTypeManager().registerObjectType(node.getNodeId(), ControlComponentNode.class,	ControlComponentNode::new); 		
	}
	
	
	private void buildControlComponentType_STATUS() {
		Object[] var = new Object[]	{ NodeIds.ControlComponentType_STATUS, Strings.getString("ControlComponent.BN.Status"), Strings.getString("ControlComponent.DN.Status"), NodeIds.StatusDataType, new Variant(null)};    
	    
		NodeId id = (NodeId)var[0];
		String browseName = (String)var[1];
		String displayName = (String)var[2];
		NodeId dataType = (NodeId)var[3];
		Variant variant = (Variant)var[4];
		
		ControlComponentStatusNode node = new ControlComponentStatusNode(context,
				id, new QualifiedName(nsIndex, browseName), LocalizedText.english(displayName), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
				new DataValue(variant), dataType, ValueRank.Scalar.getValue(), new UInteger[]{}, 
				ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);
        
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasComponent, NodeIds.ControlComponentType.expanded(), false));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasTypeDefinition, NodeIds.StatusType.expanded(), true));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
		
		nodeManager.addNode(node);
	}

	private void buildControlComponentType_OPERATIONS() {
		Object[] var = new Object[]	{ NodeIds.ControlComponentType_OPERATIONS, Strings.getString("ControlComponent.BN.Operations"), Strings.getString("ControlComponent.DN.Operations")};    
	    
		NodeId id = (NodeId)var[0];
		String browseName = (String)var[1];
		String displayName = (String)var[2];
		
		ControlComponentOperationsNode node = new ControlComponentOperationsNode(context, id, new QualifiedName(nsIndex, browseName), LocalizedText.english(displayName), LocalizedText.NULL_VALUE ,UInteger.valueOf(0L), UInteger.valueOf(0L));
        
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasComponent, NodeIds.ControlComponentType.expanded(), false));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasTypeDefinition, NodeIds.OperationsType.expanded(), true));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
		
		nodeManager.addNode(node);
		
	}
	
	private void buildControlComponentType_VARIABLES() {
		Object[] var = new Object[]	{ NodeIds.ControlComponentType_VARIABLES, Strings.getString("ControlComponent.BN.Variables"), Strings.getString("ControlComponent.DN.Variables")};    
	    
		NodeId id = (NodeId)var[0];
		String browseName = (String)var[1];
		String displayName = (String)var[2];
		
		UaFolderNode node = new UaFolderNode(context, id, new QualifiedName(nsIndex, browseName), LocalizedText.english(displayName));
        
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasComponent, NodeIds.ControlComponentType.expanded(), false));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.FolderType.expanded(), true));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));
		
		nodeManager.addNode(node);
		
	}
	
	
	private void buildOperationsType() {
        UaObjectTypeNode node = new UaObjectTypeNode(context, NodeIds.OperationsType, 
        		new QualifiedName(nsIndex,"ControlComponentOperationsType"), LocalizedText.english("ControlComponentOperationsType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);

        node.addReference(new Reference(node.getNodeId(), Identifiers.HasSubtype, Identifiers.FolderType.expanded(), false));

        nodeManager.addNode(node);
        
		context.getServer().getObjectTypeManager().registerObjectType(node.getNodeId(), ControlComponentOperationsNode.class, ControlComponentOperationsNode::new); 		
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

	private void buildOperationsMethodNode(Object[] var, NodeId containerNode) {
		NodeId id = (NodeId)var[0];
		String browseName = (String)var[1];
		String displayName = (String)var[2];
		
		//NodeId dataType = (NodeId)var[3];
		//Variant variant = (Variant)var[4];
		
		UaMethodNode node = new UaMethodNode.UaMethodNodeBuilder(context)
				.setNodeId(id).setBrowseName(new QualifiedName(nsIndex, browseName)).setDisplayName(LocalizedText.english(displayName))
				.setWriteMask(UInteger.valueOf(0L)).setUserWriteMask(UInteger.valueOf(0L))
				.setExecutable(true).setUserExecutable(true)
				.build();
		
		node.setInputArguments(new Argument[] { OperationsMethodInvocationHandler.SENDERID });
		node.setOutputArguments(new Argument[] { OperationsMethodInvocationHandler.STATUS, OperationsMethodInvocationHandler.MSG });		
		
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasComponent, containerNode.expanded(), false));
		//node.addReference(new Reference(node.getNodeId(), Identifiers.HasTypeDefinition, NodeIds.StatusType.expanded(), true));
		node.addReference(new Reference(node.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true));

		node.getPropertyNode(MethodNode.InputArguments)
			.filter(n -> n instanceof UaPropertyNode)
			.map(UaPropertyNode.class::cast)
			.ifPresent(p -> p.addReference(new Reference(p.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true)));
		
		node.getPropertyNode(MethodNode.OutputArguments)
			.filter(n -> n instanceof UaPropertyNode)
			.map(UaPropertyNode.class::cast)
			.ifPresent(p -> p.addReference(new Reference(p.getNodeId(), Identifiers.HasModellingRule, Identifiers.ModellingRule_Mandatory.expanded(), true)));		

		
		nodeManager.addNode(node);
	}
	
	
	private void buildStatusDataTypeNode() {
	  	    
        UaObjectNode binaryEncodingNode = new DataTypeEncodingNode(context, NodeIds.StatusDataType_Encoding_DefaultBinary, 
        		new QualifiedName(nsIndex, "Default Binary"), LocalizedText.english("Default Binary"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), UByte.valueOf(0));
        binaryEncodingNode.addReference(new Reference(binaryEncodingNode.getNodeId(), Identifiers.HasEncoding, NodeIds.StatusDataType.expanded(), false));
        binaryEncodingNode.addReference(new Reference(binaryEncodingNode.getNodeId(), Identifiers.HasDescription, NodeIds.StatusDataType_Description_Encoding_DefaultBinary.expanded(), true));
        binaryEncodingNode.addReference(new Reference(binaryEncodingNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        nodeManager.addNode(binaryEncodingNode);

        UaObjectNode xmlEncodingNode = new DataTypeEncodingNode(context, NodeIds.StatusDataType_Encoding_DefaultXml, 
        		new QualifiedName(nsIndex, "Default XML"), LocalizedText.english("Default XML"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), UByte.valueOf(0));
        xmlEncodingNode.addReference(new Reference(xmlEncodingNode.getNodeId(), Identifiers.HasEncoding, NodeIds.StatusDataType.expanded(), false));
        xmlEncodingNode.addReference(new Reference(xmlEncodingNode.getNodeId(), Identifiers.HasDescription, NodeIds.StatusDataType_Description_Encoding_DefaultXml.expanded(), true));
        xmlEncodingNode.addReference(new Reference(xmlEncodingNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeEncodingType.expanded(), true));
        nodeManager.addNode(xmlEncodingNode);
           
    	UaVariableNode binaryDescNode = new DataTypeDescriptionNode(context, NodeIds.StatusDataType_Description_Encoding_DefaultBinary,
    			new QualifiedName(nsIndex, "ControlComponentStatusDataType"), LocalizedText.english("ControlComponentStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L),
    			new DataValue(Variant.NULL_VALUE), Identifiers.String, ValueRank.Scalar.getValue(), new UInteger[]{}, 
    			ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);    	
    	binaryDescNode.addReference(new Reference(binaryDescNode.getNodeId(), Identifiers.HasDescription, NodeIds.StatusDataType_Encoding_DefaultBinary.expanded(), false));
    	binaryDescNode.addReference(new Reference(binaryDescNode.getNodeId(), Identifiers.HasComponent, Identifiers.OpcUa_BinarySchema.expanded(), false));
    	binaryDescNode.addReference(new Reference(binaryDescNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeDescriptionType.expanded(), true));
    	nodeManager.addNode(binaryDescNode);        
    	
    	UaVariableNode xmlDescNode = new DataTypeDescriptionNode(context, NodeIds.StatusDataType_Description_Encoding_DefaultXml, 
    			new QualifiedName(nsIndex, "ControlComponentStatusDataType"), LocalizedText.english("ControlComponentStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
    			new DataValue(Variant.NULL_VALUE), Identifiers.String, ValueRank.Scalar.getValue(), new UInteger[]{}, 
    			ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)), 100.0D, false);
    	xmlDescNode.addReference(new Reference(xmlDescNode.getNodeId(), Identifiers.HasDescription, NodeIds.StatusDataType_Encoding_DefaultXml.expanded(),  false));
    	xmlDescNode.addReference(new Reference(xmlDescNode.getNodeId(), Identifiers.HasComponent, Identifiers.OpcUa_XmlSchema.expanded(), false));
    	xmlDescNode.addReference(new Reference(xmlDescNode.getNodeId(), Identifiers.HasTypeDefinition, Identifiers.DataTypeDescriptionType.expanded(), true));
    	nodeManager.addNode(xmlDescNode);
    	    	
    	context.getServer().getDataTypeManager().registerCodec(NodeIds.StatusDataType_Encoding_DefaultBinary, new ControlComponentStatusDataType.Codec().asBinaryCodec());
    	context.getServer().getDataTypeManager().registerCodec(NodeIds.StatusDataType_Encoding_DefaultXml, new ControlComponentStatusDataType.Codec().asXmlCodec());

    	
    	UaDataTypeNode statusDataTypeNode = new UaDataTypeNode(context,	NodeIds.StatusDataType, 
    			new QualifiedName(nsIndex,"ControlComponentStatusDataType"), LocalizedText.english("ControlComponentStatusDataType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), false);
    	statusDataTypeNode.addReference(new Reference(statusDataTypeNode.getNodeId(), Identifiers.HasSubtype, Identifiers.Structure.expanded(), false));
    	statusDataTypeNode.addReference(new Reference(statusDataTypeNode.getNodeId(), Identifiers.HasEncoding, NodeIds.StatusDataType_Encoding_DefaultBinary.expanded(), true));
    	statusDataTypeNode.addReference(new Reference(statusDataTypeNode.getNodeId(), Identifiers.HasEncoding, NodeIds.StatusDataType_Encoding_DefaultXml.expanded(), true));
    	nodeManager.addNode(statusDataTypeNode);   	
    	
	}
	
	private void buildStatusTypeNode() {
    	UaVariableTypeNode node = new UaVariableTypeNode(context,
    			NodeIds.StatusType, new QualifiedName(nsIndex, "ControlComponentStatusType"), LocalizedText.english("ControlComponentStatusType"), LocalizedText.NULL_VALUE, UInteger.valueOf(0L), UInteger.valueOf(0L), 
    			new DataValue(Variant.NULL_VALUE), NodeIds.StatusDataType, ValueRank.Scalar.getValue(), new UInteger[]{}, false);    	
    	node.addReference(new Reference(node.getNodeId(), Identifiers.HasSubtype, Identifiers.BaseDataVariableType.expanded(), false));        
        nodeManager.addNode(node);
        
    	context.getServer().getVariableTypeManager().registerVariableType(node.getNodeId(), ControlComponentStatusNode.class, ControlComponentStatusNode::new);
	}
}