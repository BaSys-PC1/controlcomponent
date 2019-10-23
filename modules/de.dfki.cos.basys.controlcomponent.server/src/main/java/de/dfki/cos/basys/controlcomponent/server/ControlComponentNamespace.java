/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package de.dfki.cos.basys.controlcomponent.server;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.AnalogItemNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaDataTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegateChain;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.common.component.manager.ComponentManager;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerImpl;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OperationMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.VariableAccess;
import de.dfki.cos.basys.controlcomponent.server.methods.ExecutionCommandMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.ExecutionModeMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.GenerateEventMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.OccupationCommandMethod;
import de.dfki.cos.basys.controlcomponent.server.methods.OperationModeMethod;
import de.dfki.cos.basys.controlcomponent.server.types.CustomDataType;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class ControlComponentNamespace extends ManagedNamespace {

    static final String NAMESPACE_URI = "urn:dfki:cos:basys";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final Object[][] CC_STATUS_NODES = new Object[][]{
    	{"OccupierId", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getOccupierId());
         	}
 		}},
    	{"OccupationState", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getOccupationLevel().getName());
         	}
 		}},
    	{"ExecutionMode", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getExecutionMode().getName());
         	}
 		}},
    	{"ExecutionState", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getExecutionState().getName());
         	}
 		}},
    	{"OperationMode", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getOperationMode().getName());
         	}
 		}},
    	{"WorkState", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getWorkState());
         	}
 		}},
    	{"ErrorCode", Identifiers.Integer, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getErrorCode());
         	}
 		}},
    	{"ErrorMessage", Identifiers.String, new Variant(null), new Function<ControlComponent, Variant>() {
         	@Override
         	public Variant apply(ControlComponent cc) {
         		return new Variant(cc.getErrorMessage());
         	}
 		}},
    };    
    
    private static final Object[][] OPMODE_PROPERTY_NODES = new Object[][]{
    	{"Name", Identifiers.String, new Variant(null), new Function<OperationModeInfo, Variant>() {
         	@Override
         	public Variant apply(OperationModeInfo info) {
         		return new Variant(info.getName());
         	}
 		}},
    	{"ShortName", Identifiers.String, new Variant(null), new Function<OperationModeInfo, Variant>() {
         	@Override
         	public Variant apply(OperationModeInfo info) {
         		return new Variant(info.getShortName());
         	}
 		}},
    	{"Description", Identifiers.String, new Variant(null), new Function<OperationModeInfo, Variant>() {
         	@Override
         	public Variant apply(OperationModeInfo info) {
         		return new Variant(info.getDescription());
         	}
 		}},
    };    
    
    
    private final Random random = new Random();

    private final SubscriptionModel subscriptionModel;
    
    private final ComponentManagerImpl componentManager;

    ControlComponentNamespace(OpcUaServer server) {
        super(server, NAMESPACE_URI);
        
       	Properties config = new Properties();
    	config.put(StringConstants.id, "component-manager");
		config.put(StringConstants.name, "component-manager");
		config.put(StringConstants.implementationJavaClass, "de.dfki.cos.basys.common.component.impl.ComponentManagerImpl");
		config.put(StringConstants.connectionString, "src/test/resources/components");
		config.put("recursive", "true");
		config.put("async", "false");
       	
        componentManager = new ComponentManagerImpl(config);       
        
        subscriptionModel = new SubscriptionModel(server, this);
    }

    @Override
    protected void onShutdown() {
    	super.onShutdown();
    	
    	try {
			componentManager.deactivate();
		} catch (ComponentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    protected void onStartup() {
        super.onStartup();

        try {
			componentManager.activate(ComponentContext.getStaticContext());
		} catch (ComponentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        List<Component> components = componentManager.getComponents();
        for (Component component : components) {
        	if (component instanceof ControlComponent) {        		
				UaFolderNode folderNode = new UaFolderNode(
					getNodeContext(),
					newNodeId(component.getName()),
			        newQualifiedName(component.getName()),
		            LocalizedText.english(component.getName())
				);
				
				getNodeManager().addNode(folderNode);
				
				  // Make sure our new folder shows up under the server's Objects folder.
				folderNode.addReference(new Reference(
					folderNode.getNodeId(),
					Identifiers.Organizes,
					Identifiers.ObjectsFolder.expanded(),
					false
				));        		
        		
        		addControlComponentNode((ControlComponent)component, folderNode, folderNode.getNodeId());
        
        	}
        	else {
        		//skip for now
        	}
		}
    }

    private void addControlComponentNode(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
    	UaFolderNode folder = new UaFolderNode(
            getNodeContext(),
            newHierarchicalNodeId(parentNodeId, "ControlComponent"),
            newQualifiedName("ControlComponent"),
            LocalizedText.english("ControlComponent")
        );
    	getNodeManager().addNode(folder);
        parentFolder.addOrganizes(folder);
          
    	addStatusNode(component, folder, folder.getNodeId());
    	addServicesNode(component, folder, folder.getNodeId());
    	addVariablesNode(component, folder, folder.getNodeId());
	}   
    
    private void addStatusNode(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
    	UaFolderNode folder = new UaFolderNode(
            getNodeContext(),
            newHierarchicalNodeId(parentNodeId, "status"),
            newQualifiedName("Status"),
            LocalizedText.english("Status")
        );
        getNodeManager().addNode(folder);
        parentFolder.addOrganizes(folder);
        
        for (Object[] os : CC_STATUS_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];
            Function<ControlComponent,Variant> fn = (Function<ControlComponent,Variant>) os[3];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newHierarchicalNodeId(folder.getNodeId(), name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

			node.setValue(new DataValue(variant));

//			AttributeDelegate delegate = AttributeDelegateChain.create(new AttributeDelegate() {
//				@Override
//				public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
//					return new DataValue(fn.apply(component));
//				}
//			}, ValueLoggingDelegate::new);
			
			AttributeDelegate delegate = new AttributeDelegate() {
				@Override
				public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
					return new DataValue(fn.apply(component));
				}
			};

			node.setAttributeDelegate(delegate);

			getNodeManager().addNode(node);
			folder.addOrganizes(node);
        }
    }
    
    private void addServicesNode(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
	   UaFolderNode folder = new UaFolderNode(
            getNodeContext(),
            newNodeId(parentFolder.getNodeId().getIdentifier().toString() + "/service"),
            newQualifiedName("Services"),
            LocalizedText.english("Services")
        );
        getNodeManager().addNode(folder);
        parentFolder.addOrganizes(folder);

        addExecutionModesFolder(component, folder, folder.getNodeId());
        addExecutionCommandsFolder(component, folder, folder.getNodeId());
        addOccupationCommandsFolder(component, folder, folder.getNodeId());
        addOperationModesFolder(component, folder, folder.getNodeId());
        
    }
    
    private void addExecutionModesFolder(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
	   UaFolderNode folder = new UaFolderNode(
            getNodeContext(),
            newHierarchicalNodeId(parentNodeId, "ExecutionModes"),
            newQualifiedName("ExecutionModes"),
            LocalizedText.english("ExecutionModes")
        );
        getNodeManager().addNode(folder);
        parentFolder.addOrganizes(folder);

//        addExecutionModeMethod(component, ExecutionMode.CHANGE_OVER, folder, parentNodeId);
//        addExecutionModeMethod(component, ExecutionMode.CLEAN, folder, parentNodeId);
//        addExecutionModeMethod(component, ExecutionMode.EMPTY_OUT, folder, parentNodeId);
//        addExecutionModeMethod(component, ExecutionMode.MAINTENANCE, folder, parentNodeId);
//        addExecutionModeMethod(component, ExecutionMode.MANUAL, folder, parentNodeId);
        addExecutionModeMethod(component, ExecutionMode.PRODUCTION, folder, parentNodeId);
//        addExecutionModeMethod(component, ExecutionMode.SET_UP, folder, parentNodeId);
        addExecutionModeMethod(component, ExecutionMode.SIMULATION, folder, parentNodeId);
        
    }
    
    private void addExecutionModeMethod(ControlComponent component, ExecutionMode mode, UaFolderNode parentFolder, NodeId parentNodeId) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newHierarchicalNodeId(parentNodeId, mode.getName()))
            .setBrowseName(newQualifiedName(mode.getName()))
            .setDisplayName(new LocalizedText(null, mode.getName()))
            .setDescription(
                LocalizedText.english("Changes the execution mode of the control component to " + mode + "."))
            .build();

        ExecutionModeMethod method = new ExecutionModeMethod(component, mode, methodNode);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);

        getNodeManager().addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            parentFolder.getNodeId().expanded(),
            false
        ));
    }
  
    private void addExecutionCommandsFolder(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
 	   UaFolderNode folder = new UaFolderNode(
             getNodeContext(),
             newHierarchicalNodeId(parentNodeId, "ExecutionCommands"),
             newQualifiedName("ExecutionCommands"),
             LocalizedText.english("ExecutionCommands")
         );
         getNodeManager().addNode(folder);
         parentFolder.addOrganizes(folder);

         addExecutionCommandMethod(component, ExecutionCommand.ABORT, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.CLEAR, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.HOLD, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.RESET, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.START, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.STOP, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.SUSPEND, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.UNHOLD, folder, parentNodeId);
         addExecutionCommandMethod(component, ExecutionCommand.UNSUSPEND, folder, parentNodeId);
         
     }
    
    private void addExecutionCommandMethod(ControlComponent component, ExecutionCommand command, UaFolderNode parentFolder, NodeId parentNodeId) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newHierarchicalNodeId(parentNodeId, command.getName()))
            .setBrowseName(newQualifiedName(command.getName()))
            .setDisplayName(new LocalizedText(null, command.getName()))
            .setDescription(
                LocalizedText.english("Triggers the execution command " + command.getName() + "in the control component."))
            .build();

        ExecutionCommandMethod method = new ExecutionCommandMethod(component, command, methodNode);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);

        getNodeManager().addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            parentFolder.getNodeId().expanded(),
            false
        ));
    }
    
    private void addOccupationCommandsFolder(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
 	   UaFolderNode folder = new UaFolderNode(
             getNodeContext(),
             newHierarchicalNodeId(parentNodeId, "OccupationCommands"),
             newQualifiedName("OccupationCommands"),
             LocalizedText.english("OccupationCommands")
         );
         getNodeManager().addNode(folder);
         parentFolder.addOrganizes(folder);
         
         addOccupationCommandMethod(component, OccupationLevel.FREE, folder, parentNodeId);
         addOccupationCommandMethod(component, OccupationLevel.OCCUPIED, folder, parentNodeId);
         addOccupationCommandMethod(component, OccupationLevel.LOCAL, folder, parentNodeId);
         addOccupationCommandMethod(component, OccupationLevel.PRIORITY, folder, parentNodeId);
     }

    private void addOccupationCommandMethod(ControlComponent component, OccupationLevel level, UaFolderNode parentFolder, NodeId parentNodeId) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newHierarchicalNodeId(parentNodeId, level.getName()))
            .setBrowseName(newQualifiedName(level.getName()))
            .setDisplayName(new LocalizedText(null, level.getName()))
            .setDescription(
            	LocalizedText.english("Changes to occupation level of the control component to " + level.getName() + "."))
            .build();

        OccupationCommandMethod method = new OccupationCommandMethod(component, level, methodNode);
        methodNode.setInputArguments(method.getInputArguments());
        methodNode.setOutputArguments(method.getOutputArguments());
        methodNode.setInvocationHandler(method);

        getNodeManager().addNode(methodNode);

        methodNode.addReference(new Reference(
            methodNode.getNodeId(),
            Identifiers.HasComponent,
            parentFolder.getNodeId().expanded(),
            false
        ));
    }
    
    private void addOperationModesFolder(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
	   UaFolderNode folder = new UaFolderNode(
            getNodeContext(),
            newHierarchicalNodeId(parentNodeId, "OperationModes"),
            newQualifiedName("OperationModes"),
            LocalizedText.english("OperationModes")
        );
        getNodeManager().addNode(folder);
        parentFolder.addOrganizes(folder);
        
        List<OperationModeInfo> opmodes = component.getOperationModes();
        for (OperationModeInfo info : opmodes) {
        	addOperationModeMethod(component, info, folder, parentNodeId);
		}
    }
    
    private void addOperationModeMethod(ControlComponent component, OperationModeInfo info,	UaFolderNode parentFolder, NodeId parentNodeId) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
                .setNodeId(newHierarchicalNodeId(parentNodeId, info.getName()))
                .setBrowseName(newQualifiedName(info.getName()))
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

            getNodeManager().addNode(methodNode);

            methodNode.addReference(new Reference(
                methodNode.getNodeId(),
                Identifiers.HasComponent,
                parentFolder.getNodeId().expanded(),
                false
            ));
		
	}

    private void addVariablesNode(ControlComponent component, UaFolderNode parentFolder, NodeId parentNodeId) {
    	UaFolderNode folder = new UaFolderNode(
            getNodeContext(),
            newHierarchicalNodeId(parentNodeId, "variable"),
            newQualifiedName("Variables"),
            LocalizedText.english("Variables")
        );
        getNodeManager().addNode(folder);
        parentFolder.addOrganizes(folder);
        
		try {
			List<ParameterInfo> parameters = component.getParameters();      
	    	for (ParameterInfo p : parameters) {        		
	            String name = p.getName();
	            NodeId typeId = (NodeId) getTypeId(p.getType());	           
	
	            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
	                    .setNodeId(newHierarchicalNodeId(folder.getNodeId(), name))
	                    .setAccessLevel(getAccessLevel(p.getAccess()))
	                    .setUserAccessLevel(getAccessLevel(p.getAccess()))
	                    .setBrowseName(newQualifiedName(name))
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
	
				getNodeManager().addNode(node);
				folder.addOrganizes(node);            		
			
	    	}    
    	} catch (ComponentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
        
    }
  
    public NodeId newHierarchicalNodeId(NodeId parent, String id) {
    	return newNodeId(parent.getIdentifier().toString() + "/" + id);
    }    
	private UByte getAccessLevel(VariableAccess direction) {
		switch (direction) {
		case READ_ONLY:
			return ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY));
		case WRITE_ONLY:
			return ubyte(AccessLevel.getMask(AccessLevel.WRITE_ONLY));
		case READ_WRITE:
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
			return Identifiers.Int32;
		case "Long":
		case "long":
			return Identifiers.Int64;
		case "String":
			return Identifiers.String;
		default:
			return null;
		}
	}
		
    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }

}
