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
import de.dfki.cos.basys.common.component.ComponentManager;
import de.dfki.cos.basys.common.component.impl.ComponentManagerImpl;
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
    
    private static final Object[][] STATIC_SCALAR_NODES = new Object[][]{
        {"Boolean", Identifiers.Boolean, new Variant(false)},
        {"Byte", Identifiers.Byte, new Variant(ubyte(0x00))},
        {"SByte", Identifiers.SByte, new Variant((byte) 0x00)},
        {"Integer", Identifiers.Integer, new Variant(32)},
        {"Int16", Identifiers.Int16, new Variant((short) 16)},
        {"Int32", Identifiers.Int32, new Variant(32)},
        {"Int64", Identifiers.Int64, new Variant(64L)},
        {"UInteger", Identifiers.UInteger, new Variant(uint(32))},
        {"UInt16", Identifiers.UInt16, new Variant(ushort(16))},
        {"UInt32", Identifiers.UInt32, new Variant(uint(32))},
        {"UInt64", Identifiers.UInt64, new Variant(ulong(64L))},
        {"Float", Identifiers.Float, new Variant(3.14f)},
        {"Double", Identifiers.Double, new Variant(3.14d)},
        {"String", Identifiers.String, new Variant("string value")},
        {"DateTime", Identifiers.DateTime, new Variant(DateTime.now())},
        {"Guid", Identifiers.Guid, new Variant(UUID.randomUUID())},
        {"ByteString", Identifiers.ByteString, new Variant(new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04}))},
        {"XmlElement", Identifiers.XmlElement, new Variant(new XmlElement("<a>hello</a>"))},
        {"LocalizedText", Identifiers.LocalizedText, new Variant(LocalizedText.english("localized text"))},
        {"QualifiedName", Identifiers.QualifiedName, new Variant(new QualifiedName(1234, "defg"))},
        {"NodeId", Identifiers.NodeId, new Variant(new NodeId(1234, "abcd"))},
        {"Variant", Identifiers.BaseDataType, new Variant(32)},
        {"Duration", Identifiers.Duration, new Variant(1.0)},
        {"UtcTime", Identifiers.UtcTime, new Variant(DateTime.now())},
    };

    private static final Object[][] STATIC_ARRAY_NODES = new Object[][]{
        {"BooleanArray", Identifiers.Boolean, false},
        {"ByteArray", Identifiers.Byte, ubyte(0)},
        {"SByteArray", Identifiers.SByte, (byte) 0x00},
        {"Int16Array", Identifiers.Int16, (short) 16},
        {"Int32Array", Identifiers.Int32, 32},
        {"Int64Array", Identifiers.Int64, 64L},
        {"UInt16Array", Identifiers.UInt16, ushort(16)},
        {"UInt32Array", Identifiers.UInt32, uint(32)},
        {"UInt64Array", Identifiers.UInt64, ulong(64L)},
        {"FloatArray", Identifiers.Float, 3.14f},
        {"DoubleArray", Identifiers.Double, 3.14d},
        {"StringArray", Identifiers.String, "string value"},
        {"DateTimeArray", Identifiers.DateTime, DateTime.now()},
        {"GuidArray", Identifiers.Guid, UUID.randomUUID()},
        {"ByteStringArray", Identifiers.ByteString, new ByteString(new byte[]{0x01, 0x02, 0x03, 0x04})},
        {"XmlElementArray", Identifiers.XmlElement, new XmlElement("<a>hello</a>")},
        {"LocalizedTextArray", Identifiers.LocalizedText, LocalizedText.english("localized text")},
        {"QualifiedNameArray", Identifiers.QualifiedName, new QualifiedName(1234, "defg")},
        {"NodeIdArray", Identifiers.NodeId, new NodeId(1234, "abcd")}
    };

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
    
    private final ComponentManager componentManager;

    ControlComponentNamespace(OpcUaServer server) {
        super(server, NAMESPACE_URI);
        
       	Properties config = new Properties();
    	config.put(Component.id, "component-manager");
		config.put(Component.name, "component-manager");
		config.put(Component.implementationJavaClass, "de.dfki.cos.basys.common.component.impl.ComponentManagerImpl");
		config.put(Component.connectionString, "src/test/resources/components");
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
        
        UaFolderNode folderNode = new UaFolderNode(
            getNodeContext(),
            newNodeId("ControlComponents"),
            newQualifiedName("ControlComponents"),
            LocalizedText.english("ControlComponents")
        );

        getNodeManager().addNode(folderNode);

        // Make sure our new folder shows up under the server's Objects folder.
        folderNode.addReference(new Reference(
            folderNode.getNodeId(),
            Identifiers.Organizes,
            Identifiers.ObjectsFolder.expanded(),
            false
        ));

        List<Component> components = componentManager.getComponents();
        for (Component component : components) {
        	if (component instanceof ControlComponent) {
        		addComponentNode((ControlComponent)component, folderNode);
        	}
        	else {
        		//skip for now
        	}
		}
    }

    private void addComponentNode(ControlComponent component, UaFolderNode rootNode) {
    	UaFolderNode componentFolder = new UaFolderNode(
                getNodeContext(),
                newNodeId(component.getName()),
                newQualifiedName(component.getName()),
                LocalizedText.english(component.getName())
            );
    	getNodeManager().addNode(componentFolder);
        rootNode.addOrganizes(componentFolder);
          
    	addStatusNode(component, componentFolder);
    	addServicesNode(component, componentFolder);
    	addVariablesNode(component, componentFolder);
	}   
    
    private void addStatusNode(ControlComponent component, UaFolderNode rootNode) {
    	UaFolderNode statusFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId(component.getName() + "/status"),
            newQualifiedName("Status"),
            LocalizedText.english("Status")
        );
        getNodeManager().addNode(statusFolder);
        rootNode.addOrganizes(statusFolder);
        
        for (Object[] os : CC_STATUS_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];
            Function<ControlComponent,Variant> fn = (Function<ControlComponent,Variant>) os[3];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(component.getName() + "/status/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    .build();

			node.setValue(new DataValue(variant));

			AttributeDelegate delegate = AttributeDelegateChain.create(new AttributeDelegate() {
				@Override
				public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
					return new DataValue(fn.apply(component));
				}
			}, ValueLoggingDelegate::new);

			node.setAttributeDelegate(delegate);

			getNodeManager().addNode(node);
			statusFolder.addOrganizes(node);
        }
    }
    
    private void addServicesNode(ControlComponent component, UaFolderNode rootNode) {
	   UaFolderNode servicesFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId(component.getName() + "/service"),
            newQualifiedName("Services"),
            LocalizedText.english("Services")
        );
        getNodeManager().addNode(servicesFolder);
        rootNode.addOrganizes(servicesFolder);

        addExecutionModesFolder(component, servicesFolder);
        addExecutionCommandsFolder(component, servicesFolder);
        addOccupationCommandsFolder(component, servicesFolder);
        addOperationModesFolder(component, servicesFolder);
        
    }
    
    private void addExecutionModesFolder(ControlComponent component, UaFolderNode rootNode) {
	   UaFolderNode servicesFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId(component.getName() + "/service/ExecutionModes"),
            newQualifiedName("ExecutionModes"),
            LocalizedText.english("ExecutionModes")
        );
        getNodeManager().addNode(servicesFolder);
        rootNode.addOrganizes(servicesFolder);

//        addExecutionModeMethod(component, ExecutionMode.CHANGE_OVER, servicesFolder);
//        addExecutionModeMethod(component, ExecutionMode.CLEAN, servicesFolder);
//        addExecutionModeMethod(component, ExecutionMode.EMPTY_OUT, servicesFolder);
//        addExecutionModeMethod(component, ExecutionMode.MAINTENANCE, servicesFolder);
//        addExecutionModeMethod(component, ExecutionMode.MANUAL, servicesFolder);
        addExecutionModeMethod(component, ExecutionMode.PRODUCTION, servicesFolder);
//        addExecutionModeMethod(component, ExecutionMode.SET_UP, servicesFolder);
        addExecutionModeMethod(component, ExecutionMode.SIMULATION, servicesFolder);
        
    }
    
    private void addExecutionModeMethod(ControlComponent component, ExecutionMode mode, UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newNodeId(component.getName() + "/service/" + mode.getName()))
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
            folderNode.getNodeId().expanded(),
            false
        ));
    }
  
    private void addExecutionCommandsFolder(ControlComponent component, UaFolderNode rootNode) {
 	   UaFolderNode servicesFolder = new UaFolderNode(
             getNodeContext(),
             newNodeId(component.getName() + "/service/ExecutionCommands"),
             newQualifiedName("ExecutionCommands"),
             LocalizedText.english("ExecutionCommands")
         );
         getNodeManager().addNode(servicesFolder);
         rootNode.addOrganizes(servicesFolder);

         addExecutionCommandMethod(component, ExecutionCommand.ABORT, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.CLEAR, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.HOLD, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.RESET, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.START, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.STOP, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.SUSPEND, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.UNHOLD, servicesFolder);
         addExecutionCommandMethod(component, ExecutionCommand.UNSUSPEND, servicesFolder);
         
     }
    
    private void addExecutionCommandMethod(ControlComponent component, ExecutionCommand command, UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newNodeId(component.getName() + "/service/" + command.getName()))
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
            folderNode.getNodeId().expanded(),
            false
        ));
    }
    
    private void addOccupationCommandsFolder(ControlComponent component, UaFolderNode rootNode) {
 	   UaFolderNode servicesFolder = new UaFolderNode(
             getNodeContext(),
             newNodeId(component.getName() + "/service/OccupationCommands"),
             newQualifiedName("OccupationCommands"),
             LocalizedText.english("OccupationCommands")
         );
         getNodeManager().addNode(servicesFolder);
         rootNode.addOrganizes(servicesFolder);
         
         addOccupationCommandMethod(component, OccupationLevel.FREE, servicesFolder);
         addOccupationCommandMethod(component, OccupationLevel.OCCUPIED, servicesFolder);
         addOccupationCommandMethod(component, OccupationLevel.LOCAL, servicesFolder);
         addOccupationCommandMethod(component, OccupationLevel.PRIORITY, servicesFolder);
     }

    private void addOccupationCommandMethod(ControlComponent component, OccupationLevel level, UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
            .setNodeId(newNodeId(component.getName() + "/service/" + level.getName()))
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
            folderNode.getNodeId().expanded(),
            false
        ));
    }
    
    private void addOperationModesFolder(ControlComponent component, UaFolderNode rootNode) {
	   UaFolderNode servicesFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId(component.getName() + "/service/OperationModes"),
            newQualifiedName("OperationModes"),
            LocalizedText.english("OperationModes")
        );
        getNodeManager().addNode(servicesFolder);
        rootNode.addOrganizes(servicesFolder);
        
        List<OperationModeInfo> opmodes = component.getOperationModes();
        for (OperationModeInfo info : opmodes) {
        	addOperationModeMethod(component, info, servicesFolder);
		}
    }
    
    private void addOperationModeMethod(ControlComponent component, OperationModeInfo info,	UaFolderNode folderNode) {
        UaMethodNode methodNode = UaMethodNode.builder(getNodeContext())
                .setNodeId(newNodeId(component.getName() + "/service/" + info.getName()))
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
                folderNode.getNodeId().expanded(),
                false
            ));
		
	}

    private void addVariablesNode(ControlComponent component, UaFolderNode rootNode) {
    	UaFolderNode variablesFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId(component.getName() + "/variable"),
            newQualifiedName("Variables"),
            LocalizedText.english("Variables")
        );
        getNodeManager().addNode(variablesFolder);
        rootNode.addOrganizes(variablesFolder);
        
        List<OperationModeInfo> opmodes = component.getOperationModes();
        for (OperationModeInfo info : opmodes) {
        	for (ParameterInfo p : info.getParameters()) {        		
                String name = p.getName();
                NodeId typeId = (NodeId) getTypeId(p.getType());
                //Variant variant = (Variant) os[2];
                //Function<ControlComponent,Variant> fn = (Function<ControlComponent,Variant>) os[3];

                UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                        .setNodeId(newNodeId(component.getName() + "/variable/" + name))
                        .setAccessLevel(getAccessLevel(p.getAccess()))
                        .setUserAccessLevel(getAccessLevel(p.getAccess()))
                        .setBrowseName(newQualifiedName(name))
                        .setDisplayName(LocalizedText.english(name))
                        .setDataType(typeId)
                        .setTypeDefinition(Identifiers.BaseDataVariableType)
                        .build();

    			node.setValue(new DataValue(new Variant(p.getValue())));

//    			AttributeDelegate delegate = AttributeDelegateChain.create(new AttributeDelegate() {
//    				@Override
//    				public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
//    					return new DataValue(fn.apply(component));
//    				}
//    				@Override
//    				public void setValue(AttributeContext context, VariableNode node, DataValue value) throws UaException {
//    					// TODO Auto-generated method stub
//    					AttributeDelegate.super.setValue(context, node, value);
//    				}
//    			}, ValueLoggingDelegate::new);
//
//    			node.setAttributeDelegate(delegate);
    			
    			node.setAttributeDelegate(new ValueLoggingDelegate());

    			getNodeManager().addNode(node);
    			variablesFolder.addOrganizes(node);            		
			
        	}        
        }
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
			return Identifiers.Boolean;
		case "Date":
			return Identifiers.DateTime;
		case "Double":
			return Identifiers.Double;
		case "Integer":
			return Identifiers.Int32;
		case "Long":
			return Identifiers.Int64;
		case "String":
			return Identifiers.String;
		default:
			return null;
		}
	}

//	private NodeId getTypeId(ParameterType type) {
//		switch (type) {
//		case BOOLEAN:
//			return Identifiers.Boolean;
//		case DATE:
//			return Identifiers.DateTime;
//		case DOUBLE:
//			return Identifiers.Double;
//		case INTEGER:
//			return Identifiers.Int32;
//		case LONG:
//			return Identifiers.Int64;
//		case STRING:
//			return Identifiers.String;
//		case OBJECT:
//		default:
//			return null;
//		}
//	}
	private void addOperationModeNode(OperationModeInfo info, UaFolderNode rootNode) {
    	
    	UaFolderNode opmodeFolder = new UaFolderNode(
            getNodeContext(),
            newNodeId(rootNode.getNodeId().getIdentifier().toString() + "/"+ info.getName()),
            newQualifiedName(info.getName()),
            LocalizedText.english(info.getName())
        );
        getNodeManager().addNode(opmodeFolder);
        rootNode.addOrganizes(opmodeFolder);
    	
        for (Object[] os : OPMODE_PROPERTY_NODES) {
            String name = (String) os[0];
            NodeId typeId = (NodeId) os[1];
            Variant variant = (Variant) os[2];
            Function<OperationModeInfo,Variant> fn = (Function<OperationModeInfo,Variant>) os[3];

            UaVariableNode node = new UaVariableNode.UaVariableNodeBuilder(getNodeContext())
                    .setNodeId(newNodeId(info.getName() + "/Properties/" + name))
                    .setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                    .setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)))
                    .setBrowseName(newQualifiedName(name))
                    .setDisplayName(LocalizedText.english(name))
                    .setDataType(typeId)
                    .setTypeDefinition(Identifiers.BaseDataVariableType)
                    //.setValueRank(ValueRank.OneDimension.getValue())
                    //.setArrayDimensions(new UInteger[]{uint(0)})
                    .build();

			node.setValue(new DataValue(variant));

			AttributeDelegate delegate = AttributeDelegateChain.create(new AttributeDelegate() {
				@Override
				public DataValue getValue(AttributeContext context, VariableNode node) throws UaException {
					return new DataValue(fn.apply(info));
				}
			}, ValueLoggingDelegate::new);

			node.setAttributeDelegate(delegate);

			getNodeManager().addNode(node);
			opmodeFolder.addOrganizes(node);
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
