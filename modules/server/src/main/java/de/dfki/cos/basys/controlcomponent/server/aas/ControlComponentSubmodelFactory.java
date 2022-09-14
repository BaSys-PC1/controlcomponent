package de.dfki.cos.basys.controlcomponent.server.aas;

import de.dfki.cos.basys.aas.event.EventDirection;
import de.dfki.cos.basys.aas.event.EventState;
import de.dfki.cos.basys.aas.event.impl.EventMessage;
import de.dfki.cos.basys.aas.event.impl.ExtendedEvent;
import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentContext;
import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.controlcomponent.*;
import de.dfki.cos.basys.controlcomponent.util.Strings;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.haskind.ModelingKind;
import org.eclipse.basyx.submodel.metamodel.api.reference.IKey;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetype.ValueType;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.OperationVariable;
import org.eclipse.basyx.vab.modelprovider.lambda.VABLambdaProviderHelper;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlComponentSubmodelFactory {

	public static String ASSET_ID_TEMPLATE = "https://dfki.de/ids/asset/{0}";
	
	public static String AAS_ID_TEMPLATE = "https://dfki.de/ids/aas/{0}";
	
	public static String SUBMODEL_ID_TEMPLATE = "https://dfki.de/ids/sm/{0}/{1}";

	public static String UPDATE_TOPIC_TEMPLATE = "{0}/update";

	public static String INSTANCE_SUBMODEL_SEMANTIC_ID = "https://wiki.eclipse.org/BaSyx_/_Submodels#Control_Component_Instance";

	public static String INTERFACE_SUBMODEL_SEMANTIC_ID = "https://wiki.eclipse.org/BaSyx_/_Submodels#Control_Component_Interface";

	public static String getAssetId(ControlComponent component) {
		return MessageFormat.format(ASSET_ID_TEMPLATE, component.getId());
	}
	
	public static String getAasId(ControlComponent component) {
		return MessageFormat.format(AAS_ID_TEMPLATE, component.getId());
	}
	
	public static String getInstanceSubmodelId(ControlComponent component) {
		return MessageFormat.format(SUBMODEL_ID_TEMPLATE, component.getId(), "CCInstance");
	}
	
	public static String getInterfaceSubmodelId(ControlComponent component) {
		return MessageFormat.format(SUBMODEL_ID_TEMPLATE, component.getId(), "CCInterface");
	}
	
	public static String getAssetId(ComponentInfo info) {
		return MessageFormat.format(ASSET_ID_TEMPLATE, info.getId());
	}
	
	public static String getAasId(ComponentInfo info) {
		return MessageFormat.format(AAS_ID_TEMPLATE, info.getId());
	}
	
	public static String getInstanceSubmodelId(ComponentInfo info) {
		return MessageFormat.format(SUBMODEL_ID_TEMPLATE, info.getId(), "CCInstance");
	}
	
	public static String getInterfaceSubmodelId(ComponentInfo info) {
		return MessageFormat.format(SUBMODEL_ID_TEMPLATE, info.getId(), "CCInterface");
	}

	public static String getUpdateTopic(ControlComponent component) {
		return MessageFormat.format(UPDATE_TOPIC_TEMPLATE, Base64.getUrlEncoder().encodeToString(component.getId().getBytes(StandardCharsets.UTF_8)));
	}

	public static String getUpdateTopic(ComponentInfo info) {
		return MessageFormat.format(UPDATE_TOPIC_TEMPLATE, Base64.getUrlEncoder().encodeToString(info.getId().getBytes(StandardCharsets.UTF_8)));
	}

	public static Submodel createInterfaceSubmodel(ControlComponent component) {
		Submodel submodel = new Submodel();		
		submodel.setIdShort(component.getId() + "_CCInterface");
		submodel.setIdentification(IdentifierType.CUSTOM, getInterfaceSubmodelId(component));
		submodel.setModelingKind(ModelingKind.INSTANCE);
		submodel.setDescription(new LangStrings("en-US", "ControlComponent interface submodel for component " + component.getId()));
		submodel.setSemanticId(new Reference(new Key(KeyElements.GLOBALREFERENCE, true, INTERFACE_SUBMODEL_SEMANTIC_ID, IdentifierType.IRI)));
		
		SubmodelElementCollection status = new SubmodelElementCollection("Status");
		submodel.addSubmodelElement(status);

		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ErrorCode"),       ()-> {return component.getErrorCode();}, ValueType.Integer ));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ErrorMessage"),    ()-> {return component.getErrorMessage();}, ValueType.Integer));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionMode"),   ()-> {return component.getExecutionMode().getName();}, ValueType.String));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionState"),  ()-> {return component.getExecutionState().getName();}, ValueType.String));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.OccupationState"), ()-> {return component.getOccupationState().getName();}, ValueType.String));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.OccupierId"),        ()-> {return component.getOccupierId();}, ValueType.String));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.OperationMode"),   ()-> {return component.getOperationMode().getShortName();}, ValueType.String));
		status.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.WorkState"),       ()-> {return component.getWorkState();}, ValueType.String));

//		submodel.addSubModelElement(createOperation("Occupy", (arg) -> {return component.occupy((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Prio", (arg) -> {return component.occupyPriority((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Free", (arg) -> {return component.free((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Stop", (arg) -> {return component.stop((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Reset", (arg) -> {return component.reset((String)arg[0]).getStatus().getName();}));
		
		SubmodelElementCollection operations = new SubmodelElementCollection("Operations");
		submodel.addSubmodelElement(operations);
		
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Reset"), 	(arg) -> {return component.reset((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Start"), 	(arg) -> {return component.start((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Stop"), 		(arg) -> {return component.stop((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Hold"), 		(arg) -> {return component.hold((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Unhold"), 	(arg) -> {return component.unhold((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Suspend"), 	(arg) -> {return component.suspend((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Unsuspend"), (arg) -> {return component.unsuspend((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Abort"), 	(arg) -> {return component.abort((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Clear"), 	(arg) -> {return component.clear((String)arg[0]).getStatus().getName();}));
		
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Free"), 		(arg) -> {return component.free((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Occupy"), 	(arg) -> {return component.occupy((String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Prio"), 		(arg) -> {return component.occupyPriority((String)arg[0]).getStatus().getName();}));
		
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Auto"), 		(arg) -> {return component.setExecutionMode(ExecutionMode.AUTO, (String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.SemiAuto"), 	(arg) -> {return component.setExecutionMode(ExecutionMode.SEMIAUTO,(String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Manual"), 	(arg) -> {return component.setExecutionMode(ExecutionMode.MANUAL,(String)arg[0]).getStatus().getName();}));
		operations.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Simulate"), 	(arg) -> {return component.setExecutionMode(ExecutionMode.SIMULATE,(String)arg[0]).getStatus().getName();}));
		
		List<IKey> keys = new ArrayList<IKey>();
		keys.add(new Key(KeyElements.ASSETADMINISTRATIONSHELL, false, getAasId(component), IdentifierType.CUSTOM));
		keys.add(new Key(KeyElements.SUBMODEL, false, getInstanceSubmodelId(component), IdentifierType.CUSTOM));
		keys.add(new Key(KeyElements.SUBMODELELEMENTCOLLECTION, true, "MessageBrokers", KeyType.IDSHORT));	
		keys.add(new Key(KeyElements.SUBMODELELEMENTCOLLECTION, true, "MqttMessageBroker", KeyType.IDSHORT));	
		
		ExtendedEvent updateEvent = new ExtendedEvent(
				new Reference(submodel.getIdentification(), KeyElements.SUBMODEL, true),
				EventDirection.OUTPUT, 
				EventState.ON,
				getUpdateTopic(component),
				new Reference(keys));		
		updateEvent.setIdShort("updateEvent");
		submodel.addSubmodelElement(updateEvent);
		
//		for (OperationModeInfo opmode : component.getOperationModes()) {
//			IOperation operation = createOperation(opmode);
//			submodel.addSubModelElement(operation);
//		}
//		
//		try {
//			for (ParameterInfo p : component.getParameters()) {
//				Property property = createOperationModeProperty(component, p);
//				submodel.addSubModelElement(property);
//			}
//		} catch (ComponentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return submodel;
	}
	
	public static EventMessage createEventMessage(ComponentInfo info) {
		
		String interfaceSubmodelId = ControlComponentSubmodelFactory.getInterfaceSubmodelId(info);
		
		EventMessage message = EventMessage.builder()
				.withObservableReference(new Reference(new Identifier(IdentifierType.CUSTOM, interfaceSubmodelId), KeyElements.SUBMODEL, true))
				.withTimestamp(info.getTimestamp())
				.withTopic(interfaceSubmodelId + "/update")
				.build();
		
		return message;
	}

	public static Submodel createInstanceSubmodel(ControlComponent component) {
		Component mqttEventTransmitter = ComponentContext.getStaticContext().getComponentManager().getComponentById("mqtt-event-transmitter");
		String mqttEndpoint = mqttEventTransmitter.getInfo().getProperty(StringConstants.serviceConnectionString);

		return createInstanceSubmodel(component, mqttEndpoint, null, null);
	}

	public static Submodel createInstanceSubmodel(ControlComponent component, String mqttEndpoint, String nodeId, List<EndpointDescription> serverEndpoints) {
    	Submodel submodel = new Submodel();		
		submodel.setIdShort(component.getId() + "_CCInstance");
		submodel.setIdentification(IdentifierType.IRI, getInstanceSubmodelId(component));
		submodel.setModelingKind(ModelingKind.INSTANCE);
		submodel.setDescription(new LangStrings("en-US", "ControlComponent instance submodel for component " + component.getId()));
		submodel.setSemanticId(new Reference(new Key(KeyElements.GLOBALREFERENCE, true, INSTANCE_SUBMODEL_SEMANTIC_ID, IdentifierType.IRI)));

		ComponentInfo info = component.getInfo();
		SubmodelElementCollection configuration = new SubmodelElementCollection("Configuration");
		configuration.addSubmodelElement(createProperty("disableExecutionModeChange", info.getProperty("disableExecutionModeChange","false"), ValueType.Boolean));
		configuration.addSubmodelElement(createProperty("disableOccupationCheck", info.getProperty("disableOccupationCheck","false"), ValueType.Boolean));
		submodel.addSubmodelElement(configuration);
		
		
		SubmodelElementCollection messageBrokers = new SubmodelElementCollection("MessageBrokers");
		submodel.addSubmodelElement(messageBrokers);
		

		SubmodelElementCollection messageBroker = new SubmodelElementCollection("MqttMessageBroker");
		messageBrokers.addSubmodelElement(messageBroker);
		
		String host = "localhost";

		String patternString = "(?<protocol>.*?):\\/\\/(?<host>.*):(?<port>\\d*)";

		Pattern pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(mqttEndpoint);
		boolean matches = matcher.matches();
		if (matches) {
			host = matcher.group("host");
		}

		SubmodelElementCollection tcpEndpoint = new SubmodelElementCollection("TcpEndpoint");
		tcpEndpoint.addSubmodelElement(createProperty("Protocol", "mqtt.tcp", ValueType.String));
		tcpEndpoint.addSubmodelElement(createProperty("ConnectionString", "tcp://"+host+":1883", ValueType.String));
		SubmodelElementCollection sslEndpoint = new SubmodelElementCollection("SslEndpoint");
		sslEndpoint.addSubmodelElement(createProperty("Protocol", "mqtt.ssl", ValueType.String));
		sslEndpoint.addSubmodelElement(createProperty("ConnectionString", "tcp://"+host+":8883", ValueType.String));
		SubmodelElementCollection wsEndpoint = new SubmodelElementCollection("WsEndpoint");
		wsEndpoint.addSubmodelElement(createProperty("Protocol", "mqtt.ws", ValueType.String));
		wsEndpoint.addSubmodelElement(createProperty("ConnectionString", "ws://"+host+":8083", ValueType.String));
		SubmodelElementCollection wssEndpoint = new SubmodelElementCollection("WssEndpoint");
		wssEndpoint.addSubmodelElement(createProperty("Protocol", "mqtt.wss", ValueType.String));
		wssEndpoint.addSubmodelElement(createProperty("ConnectionString", "wss://"+host+":8084", ValueType.String));
		
		messageBroker.addSubmodelElement(tcpEndpoint);
		messageBroker.addSubmodelElement(sslEndpoint);
		messageBroker.addSubmodelElement(wsEndpoint);
		messageBroker.addSubmodelElement(wssEndpoint);

		if (nodeId != null && serverEndpoints != null) {
			SubmodelElementCollection endpointDescriptions = createEndpointDescription(nodeId, serverEndpoints);
			submodel.addSubmodelElement(endpointDescriptions);
		}

		return submodel;
    }

	private static SubmodelElementCollection createEndpointDescription(String nodeId, List<EndpointDescription> serverEndpointsDescriptions) {

		//configure opcua endpoints
		SubmodelElementCollection endpointDescriptions = new SubmodelElementCollection();
		endpointDescriptions.setIdShort("EndpointDescriptions");

		int i = 0;
		for (EndpointDescription ed : serverEndpointsDescriptions) {

			SubmodelElementCollection endpointDescription = new SubmodelElementCollection();
			endpointDescription.setIdShort("EndpointDescription" + i++);

			Property endpoint = new Property();
			endpoint.setIdShort("Endpoint");
			endpoint.set(ed.getEndpointUrl(), ValueType.String);

			Property nodeIdProperty = new Property();
			nodeIdProperty.setIdShort("NodeId");
			nodeIdProperty.set(nodeId, ValueType.String);

			Property profile = new Property();
			profile.setIdShort("Profile");
			profile.set("4", ValueType.Integer);

			Property transportProfile = new Property();
			transportProfile.setIdShort("TransportProfile");
			transportProfile.set(ed.getTransportProfileUri(), ValueType.String);

			Property securityPolicy = new Property();
			securityPolicy.setIdShort("SecurityPolicy");
			securityPolicy.set(ed.getSecurityPolicyUri(), ValueType.String);

			endpointDescription.addSubmodelElement(endpoint);
			endpointDescription.addSubmodelElement(nodeIdProperty);
			endpointDescription.addSubmodelElement(profile);
			endpointDescription.addSubmodelElement(transportProfile);
			endpointDescription.addSubmodelElement(securityPolicy);
			endpointDescriptions.addSubmodelElement(endpointDescription);
		}

		return endpointDescriptions;
	}
	
	private static Property createProperty(String name, Supplier<Object> getter, ValueType type) {
		return createProperty(name, getter, null, type);
	}
	
	private static Property createProperty(String name, Supplier<Object> getter, Consumer<Object> setter, ValueType type) {
		Property property = new Property(name, type);
		// For lambda properties, the type has to be explicitly specified as it can not be retrieved from the given
		// supplier automatically
        property.set(VABLambdaProviderHelper.createSimple(getter, setter), type);
		return property;
	}
	
	private static Property createProperty(String name, Object value, ValueType type) {
		Property property = new Property(name, type);
        property.set(value, type);
		return property;
	}
	
	public static Operation createOperation(OperationModeInfo opmode) {
		/* Die Operation erzeugen und die IdShort setzen */
		Operation operation = new Operation(opmode.getShortName());
		operation.setDescription(new LangStrings("en-US", opmode.getDescription()));

		List<OperationVariable> in = new ArrayList<>();
		operation.setInputVariables(in);
		List<OperationVariable> out = new ArrayList<>();
		operation.setOutputVariables(out);
		
		for (ParameterInfo p : opmode.getParameters()) {
			OperationVariable var = new OperationVariable();
			//var.setIdShort(p.getName());
			//var.setsetType(p.getType());
			if (p.getAccess() == ParameterDirection.IN) {
				in.add(var);
			} else {
				out.add(var);
			}
		}

		/* Die Funktion für den Schrauberzugriff als Lambda-Funktion definieren */
		Function<Object[], Object> func = (arg) -> {

			/* Die Parameter entpacken */
			String programNo = (String) arg[0];
			int times = (int) arg[1];
			return null;
			/* Das Backend des TighteningService erstellen */
			// TighteningService tighteningService = new TighteningService();
			/* ... und die Funktion aufrufen */
			// return tighteningService.tightenTimes(programNo, times);
		};
	
		operation.setInvokable(func);
		return operation;
	}

	
	
	
//	private static Property createOperationModeProperty(ControlComponent component, ParameterInfo p) {
//		Property property = new Property();
//		property.setIdShort(p.getName());
//		//property.set(p.getValue());
//		property.set(VABLambdaProviderHelper.createSimple((Supplier<Object>) () -> {
//			try {
//				return component.getParameterValue(p.getName());
//			} catch (ComponentException e) {
//				e.printStackTrace();
//				return null;
//			}
//		}, (Consumer<Object>) (value) -> {
//			 try {
//				 if (p.getAccess() == ParameterDirection.IN) {
//					 component.setParameterValue(p.getName(), value);
//				 } else {
//					 // do nothing or throw exception?
//				 }
//			} catch (ComponentException e) {
//				e.printStackTrace();
//			}
//		}));
//
//		return property;
//	}
	
	public static Operation createOperation(String name, Function<Object[], Object> fun) {
		/* Die Operation erzeugen und die IdShort setzen */
		Operation operation = new Operation();
		operation.setIdShort(name);
		//operation.setDescription(new LangStrings("en-US", opmode.getDescription()));

		List<OperationVariable> in = new ArrayList<>();
		operation.setInputVariables(in);
		
		OperationVariable sender = new OperationVariable(new Property("senderId"));				
		in.add(sender);
		
		
		//List<OperationVariable> out = new ArrayList<>();
		//operation.setOutputVariables(out);
		
//		for (ParameterInfo p : opmode.getParameters()) {
//			OperationVariable var = new OperationVariable();
//			//var.setIdShort(p.getName());
//			//var.setsetType(p.getType());
//			if (p.getAccess() == ParameterDirection.IN) {
//				in.add(var);
//			} else {
//				out.add(var);
//			}
//		}

		/* Die Funktion für den Schrauberzugriff als Lambda-Funktion definieren */
		Function<Object[], Object> func = (arg) -> {

			/* Die Parameter entpacken */
			String programNo = (String) arg[0];
			int times = (int) arg[1];
			return null;
			/* Das Backend des TighteningService erstellen */
			// TighteningService tighteningService = new TighteningService();
			/* ... und die Funktion aufrufen */
			// return tighteningService.tightenTimes(programNo, times);
		};
	
		operation.setInvokable(fun);
		return operation;
	}
	

}
