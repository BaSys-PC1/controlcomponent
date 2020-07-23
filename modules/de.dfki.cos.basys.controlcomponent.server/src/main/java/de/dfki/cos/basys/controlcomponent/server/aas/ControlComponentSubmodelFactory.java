package de.dfki.cos.basys.controlcomponent.server.aas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.basyx.submodel.metamodel.api.ISubModel;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.api.qualifier.haskind.ModelingKind;
import org.eclipse.basyx.submodel.metamodel.api.submodelelement.operation.IOperation;
import org.eclipse.basyx.submodel.metamodel.map.SubModel;
import org.eclipse.basyx.submodel.metamodel.map.qualifier.LangStrings;
import org.eclipse.basyx.submodel.metamodel.map.reference.Reference;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.SubmodelElementCollection;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.Operation;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.operation.OperationVariable;
import org.eclipse.basyx.vab.modelprovider.lambda.VABLambdaProviderHelper;

import com.google.common.eventbus.Subscribe;

import de.dfki.cos.basys.common.component.Component;
import de.dfki.cos.basys.common.component.ComponentException;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent;
import de.dfki.cos.basys.common.component.manager.impl.ComponentManagerEvent.Type;
import de.dfki.cos.basys.controlcomponent.ControlComponent;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.util.Strings;

public class ControlComponentSubmodelFactory {

	public static SubModel createSubmodel(ControlComponent component) {
		SubModel submodel = new SubModel();		
		submodel.setIdShort(component.getId());
		submodel.setIdentification(IdentifierType.CUSTOM, "ControlComponent");		
		submodel.setModelingKind(ModelingKind.INSTANCE);
		submodel.setDescription(new LangStrings("en-US", "ControlComponent submodel for component " + component.getId()));
		
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
		
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.ErrorCode"),()->component.getErrorCode()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.ErrorMessage"),()->component.getErrorMessage()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionMode"),()->component.getExecutionMode().getName()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.ExecutionState"),()->component.getExecutionState().getName()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.OccupationState"),()->component.getOccupationState().getName()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.Occupier"),()->component.getOccupierId()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.OperationMode"),()->component.getOperationMode().getShortName()));
		submodel.addSubModelElement(createProperty(Strings.getString("ControlComponent.BN.WorkState"),()->component.getWorkState()));
		
		
		
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

	private static Property createProperty(String name, Supplier<Object> getter) {
		return createProperty(name, getter, null);
	}
	
	private static Property createProperty(String name, Supplier<Object> getter, Consumer<Object> setter) {
		Property property = new Property();
		property.setIdShort(name);
		//property.set(p.getValue());
		property.set(VABLambdaProviderHelper.createSimple(getter, setter));

		return property;
	}
	
	private static Property createOperationModeProperty(ControlComponent component, ParameterInfo p) {
		Property property = new Property();
		property.setIdShort(p.getName());
		//property.set(p.getValue());
		property.set(VABLambdaProviderHelper.createSimple((Supplier<Object>) () -> {
			try {
				return component.getParameterValue(p.getName());
			} catch (ComponentException e) {
				e.printStackTrace();
				return null;
			}
		}, (Consumer<Object>) (value) -> {
			 try {
				 if (p.getAccess() == ParameterDirection.IN) {
					 component.setParameterValue(p.getName(), value);
				 } else {
					 // do nothing or throw exception?
				 }
			} catch (ComponentException e) {
				e.printStackTrace();
			}
		}));

		return property;
	}
	
	public static Operation createOperation(OperationModeInfo opmode) {
		/* Die Operation erzeugen und die IdShort setzen */
		Operation operation = new Operation();
		operation.setIdShort(opmode.getShortName());
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
	
		operation.setInvocable(func);
		return operation;
	}

}
