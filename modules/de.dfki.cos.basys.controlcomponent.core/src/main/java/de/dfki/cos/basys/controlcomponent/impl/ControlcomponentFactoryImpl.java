/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlcomponentFactoryImpl extends EFactoryImpl implements ControlcomponentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ControlcomponentFactory init() {
		try {
			ControlcomponentFactory theControlcomponentFactory = (ControlcomponentFactory)EPackage.Registry.INSTANCE.getEFactory(ControlcomponentPackage.eNS_URI);
			if (theControlcomponentFactory != null) {
				return theControlcomponentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ControlcomponentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlcomponentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ControlcomponentPackage.COMPONENT_CONFIGURATION: return createComponentConfiguration();
			case ControlcomponentPackage.CONFIGURATION_PROPERTY: return createConfigurationProperty();
			case ControlcomponentPackage.VARIABLE: return createVariable();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION: return createSimulationConfiguration();
			case ControlcomponentPackage.COMPONENT_INFO: return createComponentInfo();
			case ControlcomponentPackage.COMPONENT_ORDER: return createComponentOrder();
			case ControlcomponentPackage.COMPONENT_ORDER_STATUS: return createComponentOrderStatus();
			case ControlcomponentPackage.OCCUPATION_STATUS: return createOccupationStatus();
			case ControlcomponentPackage.ERROR_STATUS: return createErrorStatus();
			case ControlcomponentPackage.PARAMETER_INFO: return createParameterInfo();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ControlcomponentPackage.OCCUPATION_LEVEL:
				return createOccupationLevelFromString(eDataType, initialValue);
			case ControlcomponentPackage.EXECUTION_MODE:
				return createExecutionModeFromString(eDataType, initialValue);
			case ControlcomponentPackage.EXECUTION_STATE:
				return createExecutionStateFromString(eDataType, initialValue);
			case ControlcomponentPackage.EXECUTION_COMMAND:
				return createExecutionCommandFromString(eDataType, initialValue);
			case ControlcomponentPackage.ORDER_STATUS:
				return createOrderStatusFromString(eDataType, initialValue);
			case ControlcomponentPackage.VARIABLE_TYPE:
				return createVariableTypeFromString(eDataType, initialValue);
			case ControlcomponentPackage.VARIABLE_ACCESS:
				return createVariableAccessFromString(eDataType, initialValue);
			case ControlcomponentPackage.PARAMETER_DIRECTION:
				return createParameterDirectionFromString(eDataType, initialValue);
			case ControlcomponentPackage.PARAMETER_TYPE:
				return createParameterTypeFromString(eDataType, initialValue);
			case ControlcomponentPackage.OBJECT:
				return createObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ControlcomponentPackage.OCCUPATION_LEVEL:
				return convertOccupationLevelToString(eDataType, instanceValue);
			case ControlcomponentPackage.EXECUTION_MODE:
				return convertExecutionModeToString(eDataType, instanceValue);
			case ControlcomponentPackage.EXECUTION_STATE:
				return convertExecutionStateToString(eDataType, instanceValue);
			case ControlcomponentPackage.EXECUTION_COMMAND:
				return convertExecutionCommandToString(eDataType, instanceValue);
			case ControlcomponentPackage.ORDER_STATUS:
				return convertOrderStatusToString(eDataType, instanceValue);
			case ControlcomponentPackage.VARIABLE_TYPE:
				return convertVariableTypeToString(eDataType, instanceValue);
			case ControlcomponentPackage.VARIABLE_ACCESS:
				return convertVariableAccessToString(eDataType, instanceValue);
			case ControlcomponentPackage.PARAMETER_DIRECTION:
				return convertParameterDirectionToString(eDataType, instanceValue);
			case ControlcomponentPackage.PARAMETER_TYPE:
				return convertParameterTypeToString(eDataType, instanceValue);
			case ControlcomponentPackage.OBJECT:
				return convertObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentConfiguration createComponentConfiguration() {
		ComponentConfigurationImpl componentConfiguration = new ComponentConfigurationImpl();
		return componentConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConfigurationProperty createConfigurationProperty() {
		ConfigurationPropertyImpl configurationProperty = new ConfigurationPropertyImpl();
		return configurationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationConfiguration createSimulationConfiguration() {
		SimulationConfigurationImpl simulationConfiguration = new SimulationConfigurationImpl();
		return simulationConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInfo createComponentInfo() {
		ComponentInfoImpl componentInfo = new ComponentInfoImpl();
		return componentInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentOrder createComponentOrder() {
		ComponentOrderImpl componentOrder = new ComponentOrderImpl();
		return componentOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentOrderStatus createComponentOrderStatus() {
		ComponentOrderStatusImpl componentOrderStatus = new ComponentOrderStatusImpl();
		return componentOrderStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccupationStatus createOccupationStatus() {
		OccupationStatusImpl occupationStatus = new OccupationStatusImpl();
		return occupationStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorStatus createErrorStatus() {
		ErrorStatusImpl errorStatus = new ErrorStatusImpl();
		return errorStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterInfo createParameterInfo() {
		ParameterInfoImpl parameterInfo = new ParameterInfoImpl();
		return parameterInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccupationLevel createOccupationLevelFromString(EDataType eDataType, String initialValue) {
		OccupationLevel result = OccupationLevel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOccupationLevelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionMode createExecutionModeFromString(EDataType eDataType, String initialValue) {
		ExecutionMode result = ExecutionMode.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionModeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState createExecutionStateFromString(EDataType eDataType, String initialValue) {
		ExecutionState result = ExecutionState.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionStateToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionCommand createExecutionCommandFromString(EDataType eDataType, String initialValue) {
		ExecutionCommand result = ExecutionCommand.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertExecutionCommandToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderStatus createOrderStatusFromString(EDataType eDataType, String initialValue) {
		OrderStatus result = OrderStatus.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrderStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableType createVariableTypeFromString(EDataType eDataType, String initialValue) {
		VariableType result = VariableType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariableTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableAccess createVariableAccessFromString(EDataType eDataType, String initialValue) {
		VariableAccess result = VariableAccess.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVariableAccessToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDirection createParameterDirectionFromString(EDataType eDataType, String initialValue) {
		ParameterDirection result = ParameterDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParameterDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterType createParameterTypeFromString(EDataType eDataType, String initialValue) {
		ParameterType result = ParameterType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParameterTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createObjectFromString(EDataType eDataType, String initialValue) {
		return super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlcomponentPackage getControlcomponentPackage() {
		return (ControlcomponentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ControlcomponentPackage getPackage() {
		return ControlcomponentPackage.eINSTANCE;
	}

} //ControlcomponentFactoryImpl
