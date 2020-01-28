package de.dfki.cos.basys.controlcomponent.server.opcua.util;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

import java.util.List;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.FolderNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegateChain;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

import com.google.common.base.Function;

import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OccupationCommand;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.server.opcua.methods.ExecutionCommandMethod;
import de.dfki.cos.basys.controlcomponent.server.opcua.methods.ExecutionModeMethod;
import de.dfki.cos.basys.controlcomponent.server.opcua.methods.OccupationCommandMethod;
import de.dfki.cos.basys.controlcomponent.server.opcua.methods.OperationModeMethod;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentOperationsNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.nodes.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.server.opcua.types.ControlComponentStatusDataType;

public class ControlComponentNodeBuilder {
	
    private final UaNodeContext context;
    private final NodeManager<UaNode> nodeManager;
    private final UShort nsIndex;

    private static int nodeCount = 100; // 0-99 reserved for types
    
    private NodeFactory fac;
    
	public ControlComponentNodeBuilder(UaNodeContext context, NodeManager<UaNode> nodeManager, UShort nsIndex) {
		this.context = context;
		this.nodeManager = nodeManager;
		this.nsIndex = nsIndex;
		this.fac = new NodeFactory(context);
	}
	
	public ControlComponentNode build(ControlComponent component) {
		ControlComponentNode node = createControlComponentNode(component);
		configureStatusVariables(node, component);
		configureOperationsMethods(node, component);
		return node;
	}
	
	private ControlComponentNode createControlComponentNode(ControlComponent component) {
	
		ControlComponentNode ccnode = null;
		try {
			ccnode = (ControlComponentNode) fac.createNode(new NodeId(nsIndex,component.getId() + "/ControlComponent"), NodeIds.ControlComponentType, false);
			ccnode.setBrowseName(new QualifiedName(nsIndex, "ControlComponent"));
	    	ccnode.setDisplayName(LocalizedText.english("ControlComponent"));
	    	
	    	addOperationModesToOperationsFolder(ccnode, component);
	    	addVariablesToVariablesFolder(ccnode, component);
		 	//ccnode.addComponent(createOperationsFolder(component));
	    	//ccnode.addComponent(createServicesFolder(component));
	    	//ccnode.addComponent(createVariablesFolder(component));
	    	
	    	nodeManager.addNode(ccnode);
	    	
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return ccnode;
	}   

	private void configureStatusVariables(ControlComponentNode node, ControlComponent component) {
		ControlComponentStatusNode status = node.getControlComponentStatusNode();
		
//		ExtensionObject xo = ExtensionObject.encodeDefaultBinary(
//				context.getServer().getSerializationContext(),
//				new ControlComponentStatusDataType(component),
//				ControlComponentNamespace.StatusDataType_Encoding_DefaultBinary);
//		
//		status.setValue(new DataValue(new Variant(xo)));
//		
		status.setAttributeDelegate(new AttributeDelegate() {
	            @Override
	            public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
	            	ControlComponentStatusNode statusNode = (ControlComponentStatusNode) node;

	            	ControlComponentStatusDataType status = new ControlComponentStatusDataType(component);

	                try {
	                    ExtensionObject xo = ExtensionObject.encode(context.getServer().getSerializationContext(), status);

	                    DataValue value = new DataValue(new Variant(xo));

	                    node.setValue(value);

	                    return value;
	                } catch (UaSerializationException e) {
	                    throw new UaException(e);
	                }
	            }
	        });
		
		status.getErrorCodeNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getErrorCode()));
			}
		});

		status.getErrorMessageNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getErrorMessage()));
			}
		});		

		status.getExecutionModeNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getExecutionMode().getName()));
			}
		});

		status.getExecutionStateNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getExecutionState().getName()));
			}
		});

		status.getOccupationStateNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getOccupationState().getName()));
			}
		});

		status.getOccupierIdNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getOccupierId()));
			}
		});

		status.getOperationModeNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getOperationMode().getShortName()));
			}
		});
		
		status.getWorkStateNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getWorkState()));
			}
		});
		
	}
    
	private void configureOperationsMethods(ControlComponentNode node, ControlComponent component) {
		ControlComponentOperationsNode operations = node.getControlComponentOperationsNode();

		operations.getResetMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getResetMethodNode(), component, ExecutionCommand.RESET));
		operations.getStartMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getStartMethodNode(), component, ExecutionCommand.START));
		operations.getStopMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getStopMethodNode(), component, ExecutionCommand.STOP));
		operations.getHoldMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getHoldMethodNode(), component, ExecutionCommand.HOLD));
		operations.getUnholdMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getUnholdMethodNode(), component, ExecutionCommand.UNHOLD));
		operations.getSuspendMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getSuspendMethodNode(), component, ExecutionCommand.SUSPEND));
		operations.getUnsuspendMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getUnsuspendMethodNode(), component, ExecutionCommand.UNSUSPEND));
		operations.getAbortMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getAbortMethodNode(), component, ExecutionCommand.ABORT));
		operations.getClearMethodNode().setInvocationHandler(
				new ExecutionCommandMethod(operations.getClearMethodNode(), component, ExecutionCommand.CLEAR));

		operations.getAutoMethodNode().setInvocationHandler(
				new ExecutionModeMethod(operations.getAutoMethodNode(), component, ExecutionMode.AUTO));
		operations.getSemiAutoMethodNode().setInvocationHandler(
				new ExecutionModeMethod(operations.getSemiAutoMethodNode(), component, ExecutionMode.SEMIAUTO));
		operations.getManualMethodNode().setInvocationHandler(
				new ExecutionModeMethod(operations.getManualMethodNode(), component, ExecutionMode.MANUAL));
		operations.getSimulateMethodNode().setInvocationHandler(
				new ExecutionModeMethod(operations.getSimulateMethodNode(), component, ExecutionMode.SIMULATION));

		operations.getFreeMethodNode().setInvocationHandler(
				new OccupationCommandMethod(operations.getFreeMethodNode(), component, OccupationCommand.FREE));
		operations.getOccupyMethodNode().setInvocationHandler(
				new OccupationCommandMethod(operations.getOccupyMethodNode(), component, OccupationCommand.OCCUPY));
		operations.getPrioMethodNode().setInvocationHandler(
				new OccupationCommandMethod(operations.getPrioMethodNode(), component, OccupationCommand.PRIO));				
		
	}
	
	private void addOperationModesToOperationsFolder(ControlComponentNode ccnode, ControlComponent component) {
		List<OperationModeInfo> opmodes = component.getOperationModes();
        for (OperationModeInfo info : opmodes) {
        	ccnode.getControlComponentOperationsNode().addComponent(createOperationModeMethod(component, info));
		}		
	}
	
	private void addVariablesToVariablesFolder(ControlComponentNode ccnode, ControlComponent component) {
		FolderNode folder = ccnode.getControlComponentVariables();
		
		try {
			List<ParameterInfo> parameters = component.getParameters();      
	    	for (ParameterInfo p : parameters) {        		
	            String name = p.getName();
	            NodeId typeId = (NodeId) getTypeId(p.getType());	           
	
	            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(context)
	                    .setNodeId(newNodeId())
	                    .setAccessLevel(getAccessLevel(p.getAccess()))
	                    .setUserAccessLevel(getAccessLevel(p.getAccess()))
	                    .setBrowseName(new QualifiedName(nsIndex, name))
	                    .setDisplayName(LocalizedText.english(name))
	                    .setDataType(typeId)
	                    .setTypeDefinition(Identifiers.BaseDataVariableType)
	                    .build();
	
				node.setValue(new DataValue(new Variant(p.getValue())));
				
				AttributeDelegate delegate = AttributeDelegateChain.create(new AttributeDelegate() {
					@Override
					public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
						try {
							Object value = component.getParameterValue(name);
							return new DataValue(new Variant(value));
						} catch (ComponentException e) {
							e.printStackTrace();
							return new DataValue(StatusCode.BAD);
						}
					}
					@Override
					public void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {    					
						try {
							component.setParameterValue(name, value.getValue().getValue());
						} catch (ComponentException e) {								
							e.printStackTrace();
							throw new UaException(StatusCode.BAD, e.getLocalizedMessage());
						}	    					
						// TODO Auto-generated method stub
						//AttributeDelegate.super.setValue(context, node, value);
					}
				}, ValueLoggingDelegate::new);
	
				node.setAttributeDelegate(delegate);				
				//node.setAttributeDelegate(new ValueLoggingDelegate());
	
				folder.addComponent(node);				
				nodeManager.addNode(node);			
	    	}    
    	} catch (ComponentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
    }
    
    private UaNode createServicesFolder(ControlComponent component) {
	   UaFolderNode folder = new UaFolderNode(
            context,
            newNodeId(),
            new QualifiedName(nsIndex, "SERVICES"),
            LocalizedText.english("Services"));

        folder.addComponent(createExecutionModesFolder(component));
        folder.addComponent(createExecutionCommandsFolder(component));
        folder.addComponent(createOccupationCommandsFolder(component));
        folder.addComponent(createOperationModesFolder(component));

		nodeManager.addNode(folder);
		return folder;
    }
    
	private UaNode createExecutionModesFolder(ControlComponent component) {
		UaFolderNode folder = new UaFolderNode(
				context, 
				newNodeId(), 
				new QualifiedName(nsIndex, "EXMODE"),
				LocalizedText.english("ExecutionModes"));

		folder.addComponent(createExecutionModeMethod(component, ExecutionMode.AUTO));
		folder.addComponent(createExecutionModeMethod(component, ExecutionMode.SEMIAUTO));
		folder.addComponent(createExecutionModeMethod(component, ExecutionMode.SIMULATION));

		nodeManager.addNode(folder);
		return folder;
	}
  
    private UaNode createExecutionCommandsFolder(ControlComponent component) {
		UaFolderNode folder = new UaFolderNode(
				context, 
				newNodeId(), 
				new QualifiedName(nsIndex, "EXST"),
				LocalizedText.english("ExecutionCommands")); 	   
 	   
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.ABORT));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.CLEAR));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.HOLD));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.RESET));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.START));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.STOP));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.SUSPEND));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.UNHOLD));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.UNSUSPEND));
         

         nodeManager.addNode(folder);
         return folder;
     }
    
    private UaNode createOccupationCommandsFolder(ControlComponent component) {
		UaFolderNode folder = new UaFolderNode(
				context, 
				newNodeId(), 
				new QualifiedName(nsIndex, "OCCST"),
				LocalizedText.english("OccupationCommands"));

		folder.addComponent(createOccupationCommandMethod(component, OccupationCommand.FREE));
		folder.addComponent(createOccupationCommandMethod(component, OccupationCommand.OCCUPY));
		folder.addComponent(createOccupationCommandMethod(component, OccupationCommand.PRIO));

		nodeManager.addNode(folder);
		return folder;
	}
    
    private UaNode createOperationModesFolder(ControlComponent component) {
		UaFolderNode folder = new UaFolderNode(
				context, newNodeId(), 
				new QualifiedName(nsIndex, "OperationModes"),
				LocalizedText.english("OperationModes"));
		
        List<OperationModeInfo> opmodes = component.getOperationModes();
        for (OperationModeInfo info : opmodes) {
        	folder.addComponent(createOperationModeMethod(component, info));
		}

		nodeManager.addNode(folder);
		return folder;
    }
	
    private UaNode createOccupationCommandMethod(ControlComponent component, OccupationCommand cmd) {
        UaMethodNode methodNode = UaMethodNode.builder(context)
            .setNodeId(newNodeId())
            .setBrowseName(new QualifiedName(nsIndex, cmd.getName()))
            .setDisplayName(new LocalizedText(null, cmd.getName()))
            .setDescription(
            	LocalizedText.english("Triggers the occupation command " + cmd.getName() +  " in the control component."))
            .build();

        OccupationCommandMethod method = new OccupationCommandMethod(methodNode, component, cmd);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);

        nodeManager.addNode(methodNode);        
        return methodNode;
    }
	
    private UaNode createExecutionCommandMethod(ControlComponent component, ExecutionCommand command) {
        UaMethodNode methodNode = UaMethodNode.builder(context)
            .setNodeId(newNodeId())
            .setBrowseName(new QualifiedName(nsIndex, command.getName()))
            .setDisplayName(new LocalizedText(null, command.getName()))
            .setDescription(
                LocalizedText.english("Triggers the execution command " + command.getName() + " in the control component."))
            .build();

        ExecutionCommandMethod method = new ExecutionCommandMethod(methodNode, component, command);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);

        nodeManager.addNode(methodNode);
        return methodNode;
    }	
    
    private UaNode createExecutionModeMethod(ControlComponent component, ExecutionMode mode) {
        UaMethodNode methodNode = UaMethodNode.builder(context)
            .setNodeId(newNodeId())
            .setBrowseName(new QualifiedName(nsIndex, mode.getName()))
            .setDisplayName(new LocalizedText(null, mode.getName()))
            .setDescription(
                LocalizedText.english("Changes the execution mode of the control component to " + mode + "."))
            .build();

        ExecutionModeMethod method = new ExecutionModeMethod(methodNode, component, mode);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);
        
        nodeManager.addNode(methodNode);        
 		return methodNode;
    }
    
    private UaNode createOperationModeMethod(ControlComponent component, OperationModeInfo info) {
        UaMethodNode methodNode = UaMethodNode.builder(context)
            .setNodeId(newNodeId())
            .setBrowseName(new QualifiedName(nsIndex, info.getShortName()))
            .setDisplayName(new LocalizedText(null, info.getName()))
            .setDescription(
                LocalizedText.english(info.getDescription()))
            .build();

        OperationModeMethod method = new OperationModeMethod(methodNode, component, info);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setProperty(OperationModeMethod.ExecutionModes, method.getExecutionModes());
        methodNode.setProperty(OperationModeMethod.ExecutionCommands, method.getExecutionCommands());
        methodNode.setInvocationHandler(method);

        nodeManager.addNode(methodNode);        
		return methodNode;
	}
 
    
    /*
     * HELPER
     */
    
    
    public NodeId newNodeId() {
    	return new NodeId(nsIndex, nodeCount++);
    }
    
	private UByte getAccessLevel(ParameterDirection direction) {
		switch (direction) {
//		case IN:
//			return ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE));
		case OUT:
			return ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY));		
		default:		
			return ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE));	
		}
	}

	private NodeId getTypeId(String type) {
		switch (type) {
		case "Boolean":
		case "boolean":
			return Identifiers.Boolean;
		case "Date":
			return Identifiers.DateTime;
		case "Double":
		case "double":
			return Identifiers.Double;
		case "Integer":
		case "int":
			return Identifiers.Integer;
		case "Long":
		case "long":
			return Identifiers.Int64;
		case "String":
			return Identifiers.String;
		default:
			return null;
		}
	}
		
}

