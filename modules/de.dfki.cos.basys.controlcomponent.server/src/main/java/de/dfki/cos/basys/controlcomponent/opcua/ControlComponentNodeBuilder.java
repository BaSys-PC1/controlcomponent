package de.dfki.cos.basys.controlcomponent.opcua;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

import java.util.List;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
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
import de.dfki.cos.basys.controlcomponent.opcua.methods.ExecutionCommandMethod;
import de.dfki.cos.basys.controlcomponent.opcua.methods.ExecutionModeMethod;
import de.dfki.cos.basys.controlcomponent.opcua.methods.OccupationCommandMethod;
import de.dfki.cos.basys.controlcomponent.opcua.methods.OperationModeMethod;
import de.dfki.cos.basys.controlcomponent.opcua.objects.ControlComponentNode;
import de.dfki.cos.basys.controlcomponent.opcua.objects.ControlComponentStatusNode;
import de.dfki.cos.basys.controlcomponent.opcua.types.ControlComponentStatusDataType;

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
		configureControlComponentObject(node, component);
		return node;
	}
	
	private ControlComponentNode createControlComponentNode(ControlComponent component) {
	
		ControlComponentNode ccnode = null;
		try {
			ccnode = (ControlComponentNode) fac.createNode(newNodeId(), ControlComponentNamespace.ControlComponentType, false);
			ccnode.setBrowseName(new QualifiedName(nsIndex, "ControlComponent"));
	    	ccnode.setDisplayName(LocalizedText.english("ControlComponent"));
	    	
		 	ccnode.addComponent(createOperationsFolder(component));
	    	ccnode.addComponent(createServicesFolder(component));
	    	ccnode.addComponent(createVariablesFolder(component));
	    	
	    	nodeManager.addNode(ccnode);
	    	
		} catch (UaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return ccnode;
	}   
	
	private void configureControlComponentObject(ControlComponentNode node, ControlComponent component) {
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
				return new DataValue(new Variant(component.getOperationMode().getName()));
			}
		});
		
		status.getWorkStateNode().setAttributeDelegate(new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(new Variant(component.getWorkState()));
			}
		});
		
	}
    
    private UaNode createOperationsFolder(ControlComponent component) {
		UaFolderNode folder = new UaFolderNode(
				context, 
				newNodeId(), 
				new QualifiedName(nsIndex, "OPERATIONS"),
				LocalizedText.english("Operations"));		

		folder.addComponent(createOccupationCommandMethod(component, OccupationCommand.FREE));
		folder.addComponent(createOccupationCommandMethod(component, OccupationCommand.OCCUPY));
		folder.addComponent(createOccupationCommandMethod(component, OccupationCommand.PRIO));
		
		folder.addComponent(createExecutionModeMethod(component, ExecutionMode.AUTO));
		folder.addComponent(createExecutionModeMethod(component, ExecutionMode.SEMIAUTO));
		folder.addComponent(createExecutionModeMethod(component, ExecutionMode.SIMULATION));
		  
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.ABORT));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.CLEAR));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.HOLD));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.RESET));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.START));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.STOP));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.SUSPEND));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.UNHOLD));
		folder.addComponent(createExecutionCommandMethod(component, ExecutionCommand.UNSUSPEND));
		
		List<OperationModeInfo> opmodes = component.getOperationModes();
        for (OperationModeInfo info : opmodes) {
        	folder.addComponent(createOperationModeMethod(component, info));
		}

		nodeManager.addNode(folder);
        return folder;
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
    


    private UaNode createVariablesFolder(ControlComponent component) {
		UaFolderNode folder = new UaFolderNode(
				context, 
				newNodeId(), 
				new QualifiedName(nsIndex, "VARIABLES"),
				LocalizedText.english("Variables"));		
        
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
	
				nodeManager.addNode(node);
				folder.addOrganizes(node);            		
			
	    	}    
    	} catch (ComponentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    

        nodeManager.addNode(folder);
        return folder;
    }
  
    private UaNode createStatusVariable(ControlComponent component, Object[] os) {
        String name = (String) os[0];
        String browseName = (String) os[1];
        NodeId typeId = (NodeId) os[2];
        Variant variant = (Variant) os[3];
        Function<ControlComponent,Variant> fn = (Function<ControlComponent,Variant>) os[4];

        UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(context)
                .setNodeId(newNodeId())
                .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                .setBrowseName(new QualifiedName(nsIndex, browseName))
                .setDisplayName(LocalizedText.english(name))
                .setDataType(typeId)
                .setTypeDefinition(Identifiers.BaseDataVariableType)
                .build();

		node.setValue(new DataValue(variant));

//		AttributeDelegate delegate = AttributeDelegateChain.create(new AttributeDelegate() {
//			@Override
//			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
//				return new DataValue(fn.apply(component));
//			}
//		}, ValueLoggingDelegate::new);
		
		AttributeDelegate delegate = new AttributeDelegate() {
			@Override
			public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
				return new DataValue(fn.apply(component));
			}
		};

		node.setAttributeDelegate(delegate);

		nodeManager.addNode(node);
		return node;
    }
	
    private UaNode createOccupationCommandMethod(ControlComponent component, OccupationCommand cmd) {
        UaMethodNode methodNode = UaMethodNode.builder(context)
            .setNodeId(newNodeId())
            .setBrowseName(new QualifiedName(nsIndex, cmd.getName()))
            .setDisplayName(new LocalizedText(null, cmd.getName()))
            .setDescription(
            	LocalizedText.english("Triggers the occupation command " + cmd.getName() +  " in the control component."))
            .build();

        OccupationCommandMethod method = new OccupationCommandMethod(component, cmd, methodNode);
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

        ExecutionCommandMethod method = new ExecutionCommandMethod(component, command, methodNode);
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

        ExecutionModeMethod method = new ExecutionModeMethod(component, mode, methodNode);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);
        
        nodeManager.addNode(methodNode);        
 		return methodNode;
    }
    
    private UaNode createOperationModeMethod(ControlComponent component, OperationModeInfo info) {
        UaMethodNode methodNode = UaMethodNode.builder(context)
            .setNodeId(newNodeId())
            .setBrowseName(new QualifiedName(nsIndex, info.getName()))
            .setDisplayName(new LocalizedText(null, info.getName()))
            .setDescription(
                LocalizedText.english(info.getDescription()))
            .build();

        OperationModeMethod method = new OperationModeMethod(component, info, methodNode);
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
