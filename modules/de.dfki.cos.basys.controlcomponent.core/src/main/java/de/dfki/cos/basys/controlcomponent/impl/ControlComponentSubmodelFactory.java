package de.dfki.cos.basys.controlcomponent.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.haskind.ModelingKind;
import org.eclipse.basyx.submodel.metamodel.api.reference.IKey;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyElements;
import org.eclipse.basyx.submodel.metamodel.api.reference.enums.KeyType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Key;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.valuetype.ValueType;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.OperationVariable;
import org.eclipse.basyx.vab.modelprovider.lambda.VABLambdaProviderHelper;

import de.dfki.cos.basys.aas.component.AasComponentContext;
import de.dfki.cos.basys.aas.event.EventDirection;
import de.dfki.cos.basys.aas.event.EventState;
import de.dfki.cos.basys.aas.event.impl.ExtendedEvent;
import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.common.component.StringConstants;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentSubmodelFactory {

	protected static Submodel createInterfaceSubmodel(ControlComponent component) {
		Submodel submodel = new Submodel();		
		submodel.setIdShort(component.getId() + "-cc-interface");
		submodel.setIdentification(component.getSubmodelId().getIdType(), component.getSubmodelId().getId());
		submodel.setModelingKind(ModelingKind.INSTANCE);
		submodel.setDescription(new LangStrings("en-US", "ControlComponent interface submodel for component " + component.getId()));
		submodel.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "ControlComponentInterface", IdentifierType.CUSTOM)));
		
//		SubmodelElementCollection status = new SubmodelElementCollection();
//		status.setIdShort("STATUS");
//		submodel.addSubModelElement(status);
//		
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.ErrorCode"),()->component.getErrorCode()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.ErrorMessage"),()->component.getErrorMessage()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionMode"),()->component.getExecutionMode().getName()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionState"),()->component.getExecutionState().getName()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.OccupationState"),()->component.getOccupationState().getName()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.Occupier"),()->component.getOccupierId()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.OperationMode"),()->component.getOperationMode().getName()));
//		status.addElement(createProperty(Strings.getString("ControlComponent.BN.WorkState"),()->component.getWorkState()));
		
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ErrorCode"),       ()-> {return component.getErrorCode();}, ValueType.Integer ));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ErrorMessage"),    ()-> {return component.getErrorMessage();}, ValueType.Integer));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionMode"),   ()-> {return component.getExecutionMode().getName();}, ValueType.String));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionState"),  ()-> {return component.getExecutionState().getName();}, ValueType.String));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.OccupationState"), ()-> {return component.getOccupationState().getName();}, ValueType.String));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.OccupierId"),        ()-> {return component.getOccupierId();}, ValueType.String));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.OperationMode"),   ()-> {return component.getOperationMode().getShortName();}, ValueType.String));
		submodel.addSubmodelElement(createProperty(Strings.getString("ControlComponent.BN.WorkState"),       ()-> {return component.getWorkState();}, ValueType.String));

//		submodel.addSubModelElement(createOperation("Occupy", (arg) -> {return component.occupy((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Prio", (arg) -> {return component.occupyPriority((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Free", (arg) -> {return component.free((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Stop", (arg) -> {return component.stop((String)arg[0]).getStatus().getName();}));
//		submodel.addSubModelElement(createOperation("Reset", (arg) -> {return component.reset((String)arg[0]).getStatus().getName();}));
		
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Reset"), 	(arg) -> {return component.reset((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Start"), 	(arg) -> {return component.start((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Stop"), 		(arg) -> {return component.stop((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Hold"), 		(arg) -> {return component.hold((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Unhold"), 	(arg) -> {return component.unhold((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Suspend"), 	(arg) -> {return component.suspend((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Unsuspend"), (arg) -> {return component.unsuspend((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Abort"), 	(arg) -> {return component.abort((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Clear"), 	(arg) -> {return component.clear((String)arg[0]).getStatus().getName();}));
		
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Free"), 		(arg) -> {return component.free((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Occupy"), 	(arg) -> {return component.occupy((String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Prio"), 		(arg) -> {return component.occupyPriority((String)arg[0]).getStatus().getName();}));
		
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Auto"), 		(arg) -> {return component.setExecutionMode(ExecutionMode.AUTO, (String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.SemiAuto"), 	(arg) -> {return component.setExecutionMode(ExecutionMode.SEMIAUTO,(String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Manual"), 	(arg) -> {return component.setExecutionMode(ExecutionMode.MANUAL,(String)arg[0]).getStatus().getName();}));
		submodel.addSubmodelElement(createOperation(Strings.getString("ControlComponent.BN.Simulate"), 	(arg) -> {return component.setExecutionMode(ExecutionMode.SIMULATE,(String)arg[0]).getStatus().getName();}));
		
		List<IKey> keys = new ArrayList<IKey>();
		keys.add(new Key(KeyElements.ASSETADMINISTRATIONSHELL, false, component.getAasId().getId(), IdentifierType.IRI));
		keys.add(new Key(KeyElements.SUBMODEL, false, component.getSubmodelId().getId().replace("control-component", "control-component-instance"), IdentifierType.IRI));
		keys.add(new Key(KeyElements.SUBMODELELEMENTCOLLECTION, true, "MessageBrokers", KeyType.IDSHORT));	
		keys.add(new Key(KeyElements.SUBMODELELEMENTCOLLECTION, true, "MqttMessageBroker", KeyType.IDSHORT));	
		
		ExtendedEvent updateEvent = new ExtendedEvent(
				new Reference(submodel.getIdentification(), KeyElements.SUBMODEL, true),
				EventDirection.OUTPUT, 
				EventState.ON,
				component.getSubmodelId().getId() + "/update", 
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

	protected static Submodel createInstanceSubmodel(ControlComponent component) {
    	Submodel submodel = new Submodel();		
		submodel.setIdShort(component.getId() + "-cc-instance");
		submodel.setIdentification(component.getSubmodelId().getIdType(), component.getSubmodelId().getId().replace("control-component", "control-component-instance"));
		submodel.setModelingKind(ModelingKind.INSTANCE);
		submodel.setDescription(new LangStrings("en-US", "ControlComponent instance submodel for component " + component.getId()));
		submodel.setSemanticId(new Reference(new Key(KeyElements.CONCEPTDESCRIPTION, false, "ControlComponentInstance", IdentifierType.CUSTOM)));

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
		Component mqttEventTransmitter = AasComponentContext.getStaticContext().getComponentManager().getComponentById("mqtt-event-transmitter");
		String serviceConnectionString = mqttEventTransmitter.getInfo().getProperty(StringConstants.serviceConnectionString);
		
		String patternString = "(?<protocol>.*?):\\/\\/(?<host>.*):(?<port>\\d*)";

		Pattern pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(serviceConnectionString);
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
		
		return submodel;
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
