/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.ComponentInfo;
import de.dfki.cos.basys.controlcomponent.ComponentOrder;
import de.dfki.cos.basys.controlcomponent.ComponentOrderStatus;
import de.dfki.cos.basys.controlcomponent.ConfigurationProperty;
import de.dfki.cos.basys.controlcomponent.ControlcomponentFactory;
import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.OrderStatus;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.ParameterType;
import de.dfki.cos.basys.controlcomponent.SimulationConfiguration;
import de.dfki.cos.basys.controlcomponent.Variable;
import de.dfki.cos.basys.controlcomponent.VariableAccess;
import de.dfki.cos.basys.controlcomponent.VariableType;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ControlcomponentPackageImpl extends EPackageImpl implements ControlcomponentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass simulationConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentOrderEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentOrderStatusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass occupationStatusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorStatusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationModeInfoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum occupationLevelEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionModeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionStateEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum executionCommandEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orderStatusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum variableTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum variableAccessEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parameterDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parameterTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ControlcomponentPackageImpl() {
		super(eNS_URI, ControlcomponentFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ControlcomponentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ControlcomponentPackage init() {
		if (isInited) return (ControlcomponentPackage)EPackage.Registry.INSTANCE.getEPackage(ControlcomponentPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredControlcomponentPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ControlcomponentPackageImpl theControlcomponentPackage = registeredControlcomponentPackage instanceof ControlcomponentPackageImpl ? (ControlcomponentPackageImpl)registeredControlcomponentPackage : new ControlcomponentPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theControlcomponentPackage.createPackageContents();

		// Initialize created meta-data
		theControlcomponentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theControlcomponentPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ControlcomponentPackage.eNS_URI, theControlcomponentPackage);
		return theControlcomponentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponentConfiguration() {
		return componentConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentConfiguration_Id() {
		return (EAttribute)componentConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentConfiguration_Name() {
		return (EAttribute)componentConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentConfiguration_ImplementationJavaClass() {
		return (EAttribute)componentConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentConfiguration_ExternalConnectionString() {
		return (EAttribute)componentConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComponentConfiguration_Properties() {
		return (EReference)componentConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getComponentConfiguration__GetProperty__String() {
		return componentConfigurationEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConfigurationProperty() {
		return configurationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConfigurationProperty_Key() {
		return (EAttribute)configurationPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConfigurationProperty_Value() {
		return (EAttribute)configurationPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVariable() {
		return variableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariable_Name() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariable_Value() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariable_Type() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVariable_Access() {
		return (EAttribute)variableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSimulationConfiguration() {
		return simulationConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnResettingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnStartingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnExecuteDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnCompletingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnStoppingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnAbortingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnClearingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnHoldingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnUnholdingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnSuspendingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnUnsuspendingDuration() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSimulationConfiguration_OnCompletingVariables() {
		return (EReference)simulationConfigurationEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnCompletingErrorCode() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnCompletingErrorMessage() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnStoppingStatusCode() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSimulationConfiguration_OnStoppingErrorMessage() {
		return (EAttribute)simulationConfigurationEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSimulationConfiguration_OnStoppingVariables() {
		return (EReference)simulationConfigurationEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponentInfo() {
		return componentInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_Id() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_Name() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_ExecutionState() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_ExecutionMode() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_OperationMode() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_WorkState() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComponentInfo_OccupationStatus() {
		return (EReference)componentInfoEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComponentInfo_ErrorStatus() {
		return (EReference)componentInfoEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_Activated() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentInfo_ConnectedToExternal() {
		return (EAttribute)componentInfoEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponentOrder() {
		return componentOrderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComponentOrderStatus() {
		return componentOrderStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentOrderStatus_Status() {
		return (EAttribute)componentOrderStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComponentOrderStatus_Message() {
		return (EAttribute)componentOrderStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOccupationStatus() {
		return occupationStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOccupationStatus_OccupierId() {
		return (EAttribute)occupationStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOccupationStatus_Level() {
		return (EAttribute)occupationStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getErrorStatus() {
		return errorStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorStatus_ErrorCode() {
		return (EAttribute)errorStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getErrorStatus_ErrorMessage() {
		return (EAttribute)errorStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameterInfo() {
		return parameterInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameterInfo_Name() {
		return (EAttribute)parameterInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameterInfo_Type() {
		return (EAttribute)parameterInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameterInfo_Direction() {
		return (EAttribute)parameterInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getParameterInfo_Value() {
		return (EAttribute)parameterInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperationModeInfo() {
		return operationModeInfoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperationModeInfo_Name() {
		return (EAttribute)operationModeInfoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperationModeInfo_ShortName() {
		return (EAttribute)operationModeInfoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperationModeInfo_Description() {
		return (EAttribute)operationModeInfoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperationModeInfo_ExecutionModes() {
		return (EAttribute)operationModeInfoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getOperationModeInfo_ExecutionCommands() {
		return (EAttribute)operationModeInfoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationModeInfo_Parameters() {
		return (EReference)operationModeInfoEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getOccupationLevel() {
		return occupationLevelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getExecutionMode() {
		return executionModeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getExecutionState() {
		return executionStateEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getExecutionCommand() {
		return executionCommandEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getOrderStatus() {
		return orderStatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getVariableType() {
		return variableTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getVariableAccess() {
		return variableAccessEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getParameterDirection() {
		return parameterDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getParameterType() {
		return parameterTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getObject() {
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControlcomponentFactory getControlcomponentFactory() {
		return (ControlcomponentFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		componentConfigurationEClass = createEClass(COMPONENT_CONFIGURATION);
		createEAttribute(componentConfigurationEClass, COMPONENT_CONFIGURATION__ID);
		createEAttribute(componentConfigurationEClass, COMPONENT_CONFIGURATION__NAME);
		createEAttribute(componentConfigurationEClass, COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS);
		createEAttribute(componentConfigurationEClass, COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING);
		createEReference(componentConfigurationEClass, COMPONENT_CONFIGURATION__PROPERTIES);
		createEOperation(componentConfigurationEClass, COMPONENT_CONFIGURATION___GET_PROPERTY__STRING);

		configurationPropertyEClass = createEClass(CONFIGURATION_PROPERTY);
		createEAttribute(configurationPropertyEClass, CONFIGURATION_PROPERTY__KEY);
		createEAttribute(configurationPropertyEClass, CONFIGURATION_PROPERTY__VALUE);

		variableEClass = createEClass(VARIABLE);
		createEAttribute(variableEClass, VARIABLE__NAME);
		createEAttribute(variableEClass, VARIABLE__VALUE);
		createEAttribute(variableEClass, VARIABLE__TYPE);
		createEAttribute(variableEClass, VARIABLE__ACCESS);

		simulationConfigurationEClass = createEClass(SIMULATION_CONFIGURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_RESETTING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_STARTING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_STOPPING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_ABORTING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_CLEARING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_HOLDING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION);
		createEReference(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE);
		createEAttribute(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE);
		createEReference(simulationConfigurationEClass, SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES);

		componentInfoEClass = createEClass(COMPONENT_INFO);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__ID);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__NAME);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__EXECUTION_STATE);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__EXECUTION_MODE);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__OPERATION_MODE);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__WORK_STATE);
		createEReference(componentInfoEClass, COMPONENT_INFO__OCCUPATION_STATUS);
		createEReference(componentInfoEClass, COMPONENT_INFO__ERROR_STATUS);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__ACTIVATED);
		createEAttribute(componentInfoEClass, COMPONENT_INFO__CONNECTED_TO_EXTERNAL);

		componentOrderEClass = createEClass(COMPONENT_ORDER);

		componentOrderStatusEClass = createEClass(COMPONENT_ORDER_STATUS);
		createEAttribute(componentOrderStatusEClass, COMPONENT_ORDER_STATUS__STATUS);
		createEAttribute(componentOrderStatusEClass, COMPONENT_ORDER_STATUS__MESSAGE);

		occupationStatusEClass = createEClass(OCCUPATION_STATUS);
		createEAttribute(occupationStatusEClass, OCCUPATION_STATUS__OCCUPIER_ID);
		createEAttribute(occupationStatusEClass, OCCUPATION_STATUS__LEVEL);

		errorStatusEClass = createEClass(ERROR_STATUS);
		createEAttribute(errorStatusEClass, ERROR_STATUS__ERROR_CODE);
		createEAttribute(errorStatusEClass, ERROR_STATUS__ERROR_MESSAGE);

		parameterInfoEClass = createEClass(PARAMETER_INFO);
		createEAttribute(parameterInfoEClass, PARAMETER_INFO__NAME);
		createEAttribute(parameterInfoEClass, PARAMETER_INFO__TYPE);
		createEAttribute(parameterInfoEClass, PARAMETER_INFO__DIRECTION);
		createEAttribute(parameterInfoEClass, PARAMETER_INFO__VALUE);

		operationModeInfoEClass = createEClass(OPERATION_MODE_INFO);
		createEAttribute(operationModeInfoEClass, OPERATION_MODE_INFO__NAME);
		createEAttribute(operationModeInfoEClass, OPERATION_MODE_INFO__SHORT_NAME);
		createEAttribute(operationModeInfoEClass, OPERATION_MODE_INFO__DESCRIPTION);
		createEAttribute(operationModeInfoEClass, OPERATION_MODE_INFO__EXECUTION_MODES);
		createEAttribute(operationModeInfoEClass, OPERATION_MODE_INFO__EXECUTION_COMMANDS);
		createEReference(operationModeInfoEClass, OPERATION_MODE_INFO__PARAMETERS);

		// Create enums
		occupationLevelEEnum = createEEnum(OCCUPATION_LEVEL);
		executionModeEEnum = createEEnum(EXECUTION_MODE);
		executionStateEEnum = createEEnum(EXECUTION_STATE);
		executionCommandEEnum = createEEnum(EXECUTION_COMMAND);
		orderStatusEEnum = createEEnum(ORDER_STATUS);
		variableTypeEEnum = createEEnum(VARIABLE_TYPE);
		variableAccessEEnum = createEEnum(VARIABLE_ACCESS);
		parameterDirectionEEnum = createEEnum(PARAMETER_DIRECTION);
		parameterTypeEEnum = createEEnum(PARAMETER_TYPE);

		// Create data types
		objectEDataType = createEDataType(OBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(componentConfigurationEClass, ComponentConfiguration.class, "ComponentConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentConfiguration_Id(), theEcorePackage.getEString(), "id", null, 0, 1, ComponentConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentConfiguration_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ComponentConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getComponentConfiguration_ImplementationJavaClass(), theEcorePackage.getEString(), "implementationJavaClass", null, 0, 1, ComponentConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentConfiguration_ExternalConnectionString(), theEcorePackage.getEString(), "externalConnectionString", null, 0, 1, ComponentConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentConfiguration_Properties(), this.getConfigurationProperty(), null, "properties", null, 0, -1, ComponentConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getComponentConfiguration__GetProperty__String(), this.getConfigurationProperty(), "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEString(), "key", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(configurationPropertyEClass, ConfigurationProperty.class, "ConfigurationProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConfigurationProperty_Key(), theEcorePackage.getEString(), "key", null, 0, 1, ConfigurationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConfigurationProperty_Value(), theEcorePackage.getEString(), "value", null, 0, 1, ConfigurationProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableEClass, Variable.class, "Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariable_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariable_Value(), theEcorePackage.getEString(), "value", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariable_Type(), this.getVariableType(), "type", "", 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVariable_Access(), this.getVariableAccess(), "access", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(simulationConfigurationEClass, SimulationConfiguration.class, "SimulationConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSimulationConfiguration_OnResettingDuration(), theEcorePackage.getEInt(), "onResettingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnStartingDuration(), theEcorePackage.getEInt(), "onStartingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnExecuteDuration(), theEcorePackage.getEInt(), "onExecuteDuration", "3000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnCompletingDuration(), theEcorePackage.getEInt(), "onCompletingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnStoppingDuration(), theEcorePackage.getEInt(), "onStoppingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnAbortingDuration(), theEcorePackage.getEInt(), "onAbortingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnClearingDuration(), theEcorePackage.getEInt(), "onClearingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnHoldingDuration(), theEcorePackage.getEInt(), "onHoldingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnUnholdingDuration(), theEcorePackage.getEInt(), "onUnholdingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnSuspendingDuration(), theEcorePackage.getEInt(), "onSuspendingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnUnsuspendingDuration(), theEcorePackage.getEInt(), "onUnsuspendingDuration", "1000", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSimulationConfiguration_OnCompletingVariables(), this.getVariable(), null, "onCompletingVariables", null, 0, -1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnCompletingErrorCode(), theEcorePackage.getEInt(), "onCompletingErrorCode", "0", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnCompletingErrorMessage(), theEcorePackage.getEString(), "onCompletingErrorMessage", null, 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnStoppingStatusCode(), theEcorePackage.getEInt(), "onStoppingStatusCode", "1", 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSimulationConfiguration_OnStoppingErrorMessage(), theEcorePackage.getEString(), "onStoppingErrorMessage", null, 0, 1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSimulationConfiguration_OnStoppingVariables(), this.getVariable(), null, "onStoppingVariables", null, 0, -1, SimulationConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentInfoEClass, ComponentInfo.class, "ComponentInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentInfo_Id(), theEcorePackage.getEString(), "id", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_ExecutionState(), this.getExecutionState(), "executionState", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_ExecutionMode(), this.getExecutionMode(), "executionMode", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_OperationMode(), theEcorePackage.getEString(), "operationMode", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_WorkState(), theEcorePackage.getEString(), "workState", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentInfo_OccupationStatus(), this.getOccupationStatus(), null, "occupationStatus", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentInfo_ErrorStatus(), this.getErrorStatus(), null, "errorStatus", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_Activated(), ecorePackage.getEBoolean(), "activated", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentInfo_ConnectedToExternal(), ecorePackage.getEBoolean(), "connectedToExternal", null, 0, 1, ComponentInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentOrderEClass, ComponentOrder.class, "ComponentOrder", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(componentOrderStatusEClass, ComponentOrderStatus.class, "ComponentOrderStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentOrderStatus_Status(), this.getOrderStatus(), "status", null, 0, 1, ComponentOrderStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentOrderStatus_Message(), theEcorePackage.getEString(), "message", null, 0, 1, ComponentOrderStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(occupationStatusEClass, OccupationStatus.class, "OccupationStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOccupationStatus_OccupierId(), theEcorePackage.getEString(), "occupierId", null, 0, 1, OccupationStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOccupationStatus_Level(), this.getOccupationLevel(), "level", null, 0, 1, OccupationStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(errorStatusEClass, ErrorStatus.class, "ErrorStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getErrorStatus_ErrorCode(), theEcorePackage.getEInt(), "errorCode", null, 0, 1, ErrorStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getErrorStatus_ErrorMessage(), theEcorePackage.getEString(), "errorMessage", null, 0, 1, ErrorStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterInfoEClass, ParameterInfo.class, "ParameterInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterInfo_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ParameterInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterInfo_Type(), this.getParameterType(), "type", null, 0, 1, ParameterInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterInfo_Direction(), this.getParameterDirection(), "direction", null, 0, 1, ParameterInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterInfo_Value(), this.getObject(), "value", null, 0, 1, ParameterInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationModeInfoEClass, OperationModeInfo.class, "OperationModeInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperationModeInfo_Name(), theEcorePackage.getEString(), "name", null, 0, 1, OperationModeInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperationModeInfo_ShortName(), theEcorePackage.getEString(), "shortName", null, 0, 1, OperationModeInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperationModeInfo_Description(), theEcorePackage.getEString(), "description", null, 0, 1, OperationModeInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperationModeInfo_ExecutionModes(), this.getExecutionMode(), "executionModes", null, 0, -1, OperationModeInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOperationModeInfo_ExecutionCommands(), this.getExecutionCommand(), "executionCommands", null, 0, -1, OperationModeInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationModeInfo_Parameters(), this.getParameterInfo(), null, "parameters", null, 0, -1, OperationModeInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(occupationLevelEEnum, OccupationLevel.class, "OccupationLevel");
		addEEnumLiteral(occupationLevelEEnum, OccupationLevel.FREE);
		addEEnumLiteral(occupationLevelEEnum, OccupationLevel.OCCUPIED);
		addEEnumLiteral(occupationLevelEEnum, OccupationLevel.PRIORITY);
		addEEnumLiteral(occupationLevelEEnum, OccupationLevel.LOCAL);

		initEEnum(executionModeEEnum, ExecutionMode.class, "ExecutionMode");
		addEEnumLiteral(executionModeEEnum, ExecutionMode.UNDEFINED);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.PRODUCTION);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.MAINTENANCE);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.MANUAL);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.CHANGE_OVER);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.CLEAN);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.SET_UP);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.EMPTY_OUT);
		addEEnumLiteral(executionModeEEnum, ExecutionMode.SIMULATION);

		initEEnum(executionStateEEnum, ExecutionState.class, "ExecutionState");
		addEEnumLiteral(executionStateEEnum, ExecutionState.UNDEFINED);
		addEEnumLiteral(executionStateEEnum, ExecutionState.CLEARING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.STOPPED);
		addEEnumLiteral(executionStateEEnum, ExecutionState.STARTING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.IDLE);
		addEEnumLiteral(executionStateEEnum, ExecutionState.SUSPENDED);
		addEEnumLiteral(executionStateEEnum, ExecutionState.EXECUTE);
		addEEnumLiteral(executionStateEEnum, ExecutionState.STOPPING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.ABORTING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.ABORTED);
		addEEnumLiteral(executionStateEEnum, ExecutionState.HOLDING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.HELD);
		addEEnumLiteral(executionStateEEnum, ExecutionState.UNHOLDING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.SUSPENDING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.UNSUSPENDING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.RESETTING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.COMPLETING);
		addEEnumLiteral(executionStateEEnum, ExecutionState.COMPLETE);

		initEEnum(executionCommandEEnum, ExecutionCommand.class, "ExecutionCommand");
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.NO_COMMAND);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.RESET);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.START);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.STOP);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.HOLD);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.UNHOLD);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.SUSPEND);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.UNSUSPEND);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.ABORT);
		addEEnumLiteral(executionCommandEEnum, ExecutionCommand.CLEAR);

		initEEnum(orderStatusEEnum, OrderStatus.class, "OrderStatus");
		addEEnumLiteral(orderStatusEEnum, OrderStatus.UNDEFINED);
		addEEnumLiteral(orderStatusEEnum, OrderStatus.ACCEPTED);
		addEEnumLiteral(orderStatusEEnum, OrderStatus.REJECTED);
		addEEnumLiteral(orderStatusEEnum, OrderStatus.NOOP);
		addEEnumLiteral(orderStatusEEnum, OrderStatus.QUEUED);

		initEEnum(variableTypeEEnum, VariableType.class, "VariableType");
		addEEnumLiteral(variableTypeEEnum, VariableType.NULL);
		addEEnumLiteral(variableTypeEEnum, VariableType.BOOLEAN);
		addEEnumLiteral(variableTypeEEnum, VariableType.INTEGER);
		addEEnumLiteral(variableTypeEEnum, VariableType.STRING);
		addEEnumLiteral(variableTypeEEnum, VariableType.DOUBLE);
		addEEnumLiteral(variableTypeEEnum, VariableType.LONG);
		addEEnumLiteral(variableTypeEEnum, VariableType.DATE);

		initEEnum(variableAccessEEnum, VariableAccess.class, "VariableAccess");
		addEEnumLiteral(variableAccessEEnum, VariableAccess.READ_ONLY);
		addEEnumLiteral(variableAccessEEnum, VariableAccess.WRITE_ONLY);
		addEEnumLiteral(variableAccessEEnum, VariableAccess.READ_WRITE);

		initEEnum(parameterDirectionEEnum, ParameterDirection.class, "ParameterDirection");
		addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.IN);
		addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.OUT);
		addEEnumLiteral(parameterDirectionEEnum, ParameterDirection.IN_OUT);

		initEEnum(parameterTypeEEnum, ParameterType.class, "ParameterType");
		addEEnumLiteral(parameterTypeEEnum, ParameterType.OBJECT);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.BOOLEAN);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.INTEGER);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.STRING);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.DOUBLE);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.LONG);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.DATE);

		// Initialize data types
		initEDataType(objectEDataType, Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://de.dfki.iui.mmds/CoreModel
		createCoreModelAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://de.dfki.iui.mmds/CoreModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createCoreModelAnnotations() {
		String source = "http://de.dfki.iui.mmds/CoreModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "settingDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL",
			   "invocationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL",
			   "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL"
		   });
	}

} //ControlcomponentPackageImpl
