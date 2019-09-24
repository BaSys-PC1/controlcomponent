/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL' invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL'"
 * @generated
 */
public interface ControlcomponentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "controlcomponent";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.dfki.de/cos/basys/controlcomponent";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "controlcomponent";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ControlcomponentPackage eINSTANCE = de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl <em>Component Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentConfiguration()
	 * @generated
	 */
	int COMPONENT_CONFIGURATION = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Implementation Java Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS = 2;

	/**
	 * The feature id for the '<em><b>External Connection String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING = 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION__PROPERTIES = 4;

	/**
	 * The number of structural features of the '<em>Component Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Get Property</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION___GET_PROPERTY__STRING = 0;

	/**
	 * The number of operations of the '<em>Component Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_CONFIGURATION_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ConfigurationPropertyImpl <em>Configuration Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ConfigurationPropertyImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getConfigurationProperty()
	 * @generated
	 */
	int CONFIGURATION_PROPERTY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Configuration Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Configuration Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURATION_PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.VariableImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Access</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__ACCESS = 3;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl <em>Simulation Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getSimulationConfiguration()
	 * @generated
	 */
	int SIMULATION_CONFIGURATION = 3;

	/**
	 * The feature id for the '<em><b>On Resetting Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_RESETTING_DURATION = 0;

	/**
	 * The feature id for the '<em><b>On Starting Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_STARTING_DURATION = 1;

	/**
	 * The feature id for the '<em><b>On Execute Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION = 2;

	/**
	 * The feature id for the '<em><b>On Completing Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION = 3;

	/**
	 * The feature id for the '<em><b>On Stopping Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_STOPPING_DURATION = 4;

	/**
	 * The feature id for the '<em><b>On Aborting Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_ABORTING_DURATION = 5;

	/**
	 * The feature id for the '<em><b>On Clearing Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_CLEARING_DURATION = 6;

	/**
	 * The feature id for the '<em><b>On Holding Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_HOLDING_DURATION = 7;

	/**
	 * The feature id for the '<em><b>On Unholding Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION = 8;

	/**
	 * The feature id for the '<em><b>On Suspending Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION = 9;

	/**
	 * The feature id for the '<em><b>On Unsuspending Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION = 10;

	/**
	 * The feature id for the '<em><b>On Completing Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES = 11;

	/**
	 * The feature id for the '<em><b>On Completing Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE = 12;

	/**
	 * The feature id for the '<em><b>On Completing Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE = 13;

	/**
	 * The feature id for the '<em><b>On Stopping Status Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE = 14;

	/**
	 * The feature id for the '<em><b>On Stopping Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE = 15;

	/**
	 * The feature id for the '<em><b>On Stopping Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES = 16;

	/**
	 * The number of structural features of the '<em>Simulation Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION_FEATURE_COUNT = 17;

	/**
	 * The number of operations of the '<em>Simulation Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_CONFIGURATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl <em>Component Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentInfo()
	 * @generated
	 */
	int COMPONENT_INFO = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__NAME = 1;

	/**
	 * The feature id for the '<em><b>Execution State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__EXECUTION_STATE = 2;

	/**
	 * The feature id for the '<em><b>Execution Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__EXECUTION_MODE = 3;

	/**
	 * The feature id for the '<em><b>Operation Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__OPERATION_MODE = 4;

	/**
	 * The feature id for the '<em><b>Work State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__WORK_STATE = 5;

	/**
	 * The feature id for the '<em><b>Occupation Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__OCCUPATION_STATUS = 6;

	/**
	 * The feature id for the '<em><b>Error Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__ERROR_STATUS = 7;

	/**
	 * The feature id for the '<em><b>Activated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__ACTIVATED = 8;

	/**
	 * The feature id for the '<em><b>Connected To External</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO__CONNECTED_TO_EXTERNAL = 9;

	/**
	 * The number of structural features of the '<em>Component Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO_FEATURE_COUNT = 10;

	/**
	 * The number of operations of the '<em>Component Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentOrderImpl <em>Component Order</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentOrderImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentOrder()
	 * @generated
	 */
	int COMPONENT_ORDER = 5;

	/**
	 * The number of structural features of the '<em>Component Order</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ORDER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Component Order</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ORDER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentOrderStatusImpl <em>Component Order Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentOrderStatusImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentOrderStatus()
	 * @generated
	 */
	int COMPONENT_ORDER_STATUS = 6;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ORDER_STATUS__STATUS = 0;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ORDER_STATUS__MESSAGE = 1;

	/**
	 * The number of structural features of the '<em>Component Order Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ORDER_STATUS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Component Order Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ORDER_STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.OccupationStatusImpl <em>Occupation Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.OccupationStatusImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getOccupationStatus()
	 * @generated
	 */
	int OCCUPATION_STATUS = 7;

	/**
	 * The feature id for the '<em><b>Occupier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPATION_STATUS__OCCUPIER_ID = 0;

	/**
	 * The feature id for the '<em><b>Level</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPATION_STATUS__LEVEL = 1;

	/**
	 * The number of structural features of the '<em>Occupation Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPATION_STATUS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Occupation Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPATION_STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ErrorStatusImpl <em>Error Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ErrorStatusImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getErrorStatus()
	 * @generated
	 */
	int ERROR_STATUS = 8;

	/**
	 * The feature id for the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_STATUS__ERROR_CODE = 0;

	/**
	 * The feature id for the '<em><b>Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_STATUS__ERROR_MESSAGE = 1;

	/**
	 * The number of structural features of the '<em>Error Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_STATUS_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Error Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl <em>Parameter Info</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getParameterInfo()
	 * @generated
	 */
	int PARAMETER_INFO = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INFO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INFO__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INFO__DIRECTION = 2;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INFO__VALUE = 3;

	/**
	 * The number of structural features of the '<em>Parameter Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INFO_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Parameter Info</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INFO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.OccupationLevel <em>Occupation Level</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.OccupationLevel
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getOccupationLevel()
	 * @generated
	 */
	int OCCUPATION_LEVEL = 10;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.ExecutionMode <em>Execution Mode</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionMode
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getExecutionMode()
	 * @generated
	 */
	int EXECUTION_MODE = 11;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.ExecutionState <em>Execution State</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionState
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getExecutionState()
	 * @generated
	 */
	int EXECUTION_STATE = 12;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.ExecutionCommand <em>Execution Command</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionCommand
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getExecutionCommand()
	 * @generated
	 */
	int EXECUTION_COMMAND = 13;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.OrderStatus <em>Order Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.OrderStatus
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getOrderStatus()
	 * @generated
	 */
	int ORDER_STATUS = 14;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.VariableType <em>Variable Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.VariableType
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getVariableType()
	 * @generated
	 */
	int VARIABLE_TYPE = 15;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.VariableAccess <em>Variable Access</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.VariableAccess
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getVariableAccess()
	 * @generated
	 */
	int VARIABLE_ACCESS = 16;


	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.ParameterDirection <em>Parameter Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.ParameterDirection
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getParameterDirection()
	 * @generated
	 */
	int PARAMETER_DIRECTION = 17;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.ParameterType <em>Parameter Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.ParameterType
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getParameterType()
	 * @generated
	 */
	int PARAMETER_TYPE = 18;


	/**
	 * The meta object id for the '<em>Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Object
	 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 19;


	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration <em>Component Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Configuration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration
	 * @generated
	 */
	EClass getComponentConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getId()
	 * @see #getComponentConfiguration()
	 * @generated
	 */
	EAttribute getComponentConfiguration_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getName()
	 * @see #getComponentConfiguration()
	 * @generated
	 */
	EAttribute getComponentConfiguration_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getImplementationJavaClass <em>Implementation Java Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Java Class</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getImplementationJavaClass()
	 * @see #getComponentConfiguration()
	 * @generated
	 */
	EAttribute getComponentConfiguration_ImplementationJavaClass();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getExternalConnectionString <em>External Connection String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External Connection String</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getExternalConnectionString()
	 * @see #getComponentConfiguration()
	 * @generated
	 */
	EAttribute getComponentConfiguration_ExternalConnectionString();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getProperties()
	 * @see #getComponentConfiguration()
	 * @generated
	 */
	EReference getComponentConfiguration_Properties();

	/**
	 * Returns the meta object for the '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getProperty(java.lang.String) <em>Get Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Property</em>' operation.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getProperty(java.lang.String)
	 * @generated
	 */
	EOperation getComponentConfiguration__GetProperty__String();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ConfigurationProperty <em>Configuration Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configuration Property</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ConfigurationProperty
	 * @generated
	 */
	EClass getConfigurationProperty();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ConfigurationProperty#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ConfigurationProperty#getKey()
	 * @see #getConfigurationProperty()
	 * @generated
	 */
	EAttribute getConfigurationProperty_Key();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ConfigurationProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ConfigurationProperty#getValue()
	 * @see #getConfigurationProperty()
	 * @generated
	 */
	EAttribute getConfigurationProperty_Value();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.Variable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.Variable#getName()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.Variable#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.Variable#getValue()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Value();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.Variable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.Variable#getType()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.Variable#getAccess <em>Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Access</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.Variable#getAccess()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Access();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration <em>Simulation Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulation Configuration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration
	 * @generated
	 */
	EClass getSimulationConfiguration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnResettingDuration <em>On Resetting Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Resetting Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnResettingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnResettingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStartingDuration <em>On Starting Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Starting Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStartingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnStartingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnExecuteDuration <em>On Execute Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Execute Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnExecuteDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnExecuteDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingDuration <em>On Completing Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Completing Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnCompletingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingDuration <em>On Stopping Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Stopping Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnStoppingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnAbortingDuration <em>On Aborting Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Aborting Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnAbortingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnAbortingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnClearingDuration <em>On Clearing Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Clearing Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnClearingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnClearingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnHoldingDuration <em>On Holding Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Holding Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnHoldingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnHoldingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnholdingDuration <em>On Unholding Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Unholding Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnholdingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnUnholdingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnSuspendingDuration <em>On Suspending Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Suspending Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnSuspendingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnSuspendingDuration();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnsuspendingDuration <em>On Unsuspending Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Unsuspending Duration</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnsuspendingDuration()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnUnsuspendingDuration();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingVariables <em>On Completing Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Completing Variables</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingVariables()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EReference getSimulationConfiguration_OnCompletingVariables();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorCode <em>On Completing Error Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Completing Error Code</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorCode()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnCompletingErrorCode();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorMessage <em>On Completing Error Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Completing Error Message</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorMessage()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnCompletingErrorMessage();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingStatusCode <em>On Stopping Status Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Stopping Status Code</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingStatusCode()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnStoppingStatusCode();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingErrorMessage <em>On Stopping Error Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Stopping Error Message</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingErrorMessage()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EAttribute getSimulationConfiguration_OnStoppingErrorMessage();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingVariables <em>On Stopping Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Stopping Variables</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingVariables()
	 * @see #getSimulationConfiguration()
	 * @generated
	 */
	EReference getSimulationConfiguration_OnStoppingVariables();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo <em>Component Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Info</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo
	 * @generated
	 */
	EClass getComponentInfo();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getId()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getName()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionState <em>Execution State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution State</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionState()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_ExecutionState();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionMode <em>Execution Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Mode</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionMode()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_ExecutionMode();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getOperationMode <em>Operation Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operation Mode</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getOperationMode()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_OperationMode();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getWorkState <em>Work State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Work State</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getWorkState()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_WorkState();

	/**
	 * Returns the meta object for the reference '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getOccupationStatus <em>Occupation Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Occupation Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getOccupationStatus()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EReference getComponentInfo_OccupationStatus();

	/**
	 * Returns the meta object for the containment reference '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getErrorStatus <em>Error Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Error Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#getErrorStatus()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EReference getComponentInfo_ErrorStatus();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#isActivated <em>Activated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Activated</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#isActivated()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_Activated();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#isConnectedToExternal <em>Connected To External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connected To External</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentInfo#isConnectedToExternal()
	 * @see #getComponentInfo()
	 * @generated
	 */
	EAttribute getComponentInfo_ConnectedToExternal();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ComponentOrder <em>Component Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Order</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentOrder
	 * @generated
	 */
	EClass getComponentOrder();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ComponentOrderStatus <em>Component Order Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Order Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentOrderStatus
	 * @generated
	 */
	EClass getComponentOrderStatus();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentOrderStatus#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentOrderStatus#getStatus()
	 * @see #getComponentOrderStatus()
	 * @generated
	 */
	EAttribute getComponentOrderStatus_Status();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ComponentOrderStatus#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ComponentOrderStatus#getMessage()
	 * @see #getComponentOrderStatus()
	 * @generated
	 */
	EAttribute getComponentOrderStatus_Message();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.OccupationStatus <em>Occupation Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occupation Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.OccupationStatus
	 * @generated
	 */
	EClass getOccupationStatus();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.OccupationStatus#getOccupierId <em>Occupier Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Occupier Id</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.OccupationStatus#getOccupierId()
	 * @see #getOccupationStatus()
	 * @generated
	 */
	EAttribute getOccupationStatus_OccupierId();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.OccupationStatus#getLevel <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.OccupationStatus#getLevel()
	 * @see #getOccupationStatus()
	 * @generated
	 */
	EAttribute getOccupationStatus_Level();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ErrorStatus <em>Error Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ErrorStatus
	 * @generated
	 */
	EClass getErrorStatus();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorCode <em>Error Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Code</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorCode()
	 * @see #getErrorStatus()
	 * @generated
	 */
	EAttribute getErrorStatus_ErrorCode();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorMessage <em>Error Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Error Message</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorMessage()
	 * @see #getErrorStatus()
	 * @generated
	 */
	EAttribute getErrorStatus_ErrorMessage();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo <em>Parameter Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Info</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterInfo
	 * @generated
	 */
	EClass getParameterInfo();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterInfo#getName()
	 * @see #getParameterInfo()
	 * @generated
	 */
	EAttribute getParameterInfo_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterInfo#getType()
	 * @see #getParameterInfo()
	 * @generated
	 */
	EAttribute getParameterInfo_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterInfo#getDirection()
	 * @see #getParameterInfo()
	 * @generated
	 */
	EAttribute getParameterInfo_Direction();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterInfo#getValue()
	 * @see #getParameterInfo()
	 * @generated
	 */
	EAttribute getParameterInfo_Value();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.OccupationLevel <em>Occupation Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Occupation Level</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.OccupationLevel
	 * @generated
	 */
	EEnum getOccupationLevel();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.ExecutionMode <em>Execution Mode</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution Mode</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionMode
	 * @generated
	 */
	EEnum getExecutionMode();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.ExecutionState <em>Execution State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution State</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionState
	 * @generated
	 */
	EEnum getExecutionState();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.ExecutionCommand <em>Execution Command</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Execution Command</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionCommand
	 * @generated
	 */
	EEnum getExecutionCommand();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.OrderStatus <em>Order Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Order Status</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.OrderStatus
	 * @generated
	 */
	EEnum getOrderStatus();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.VariableType <em>Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Variable Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.VariableType
	 * @generated
	 */
	EEnum getVariableType();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.VariableAccess <em>Variable Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Variable Access</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.VariableAccess
	 * @generated
	 */
	EEnum getVariableAccess();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.ParameterDirection <em>Parameter Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parameter Direction</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterDirection
	 * @generated
	 */
	EEnum getParameterDirection();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.ParameterType <em>Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Parameter Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterType
	 * @generated
	 */
	EEnum getParameterType();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Object</em>'.
	 * @see java.lang.Object
	 * @model instanceClass="java.lang.Object"
	 * @generated
	 */
	EDataType getObject();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ControlcomponentFactory getControlcomponentFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl <em>Component Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentConfiguration()
		 * @generated
		 */
		EClass COMPONENT_CONFIGURATION = eINSTANCE.getComponentConfiguration();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_CONFIGURATION__ID = eINSTANCE.getComponentConfiguration_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_CONFIGURATION__NAME = eINSTANCE.getComponentConfiguration_Name();

		/**
		 * The meta object literal for the '<em><b>Implementation Java Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS = eINSTANCE.getComponentConfiguration_ImplementationJavaClass();

		/**
		 * The meta object literal for the '<em><b>External Connection String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING = eINSTANCE.getComponentConfiguration_ExternalConnectionString();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_CONFIGURATION__PROPERTIES = eINSTANCE.getComponentConfiguration_Properties();

		/**
		 * The meta object literal for the '<em><b>Get Property</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation COMPONENT_CONFIGURATION___GET_PROPERTY__STRING = eINSTANCE.getComponentConfiguration__GetProperty__String();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ConfigurationPropertyImpl <em>Configuration Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ConfigurationPropertyImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getConfigurationProperty()
		 * @generated
		 */
		EClass CONFIGURATION_PROPERTY = eINSTANCE.getConfigurationProperty();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_PROPERTY__KEY = eINSTANCE.getConfigurationProperty_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONFIGURATION_PROPERTY__VALUE = eINSTANCE.getConfigurationProperty_Value();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.VariableImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__NAME = eINSTANCE.getVariable_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__VALUE = eINSTANCE.getVariable_Value();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__TYPE = eINSTANCE.getVariable_Type();

		/**
		 * The meta object literal for the '<em><b>Access</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__ACCESS = eINSTANCE.getVariable_Access();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl <em>Simulation Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getSimulationConfiguration()
		 * @generated
		 */
		EClass SIMULATION_CONFIGURATION = eINSTANCE.getSimulationConfiguration();

		/**
		 * The meta object literal for the '<em><b>On Resetting Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_RESETTING_DURATION = eINSTANCE.getSimulationConfiguration_OnResettingDuration();

		/**
		 * The meta object literal for the '<em><b>On Starting Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_STARTING_DURATION = eINSTANCE.getSimulationConfiguration_OnStartingDuration();

		/**
		 * The meta object literal for the '<em><b>On Execute Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION = eINSTANCE.getSimulationConfiguration_OnExecuteDuration();

		/**
		 * The meta object literal for the '<em><b>On Completing Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION = eINSTANCE.getSimulationConfiguration_OnCompletingDuration();

		/**
		 * The meta object literal for the '<em><b>On Stopping Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_STOPPING_DURATION = eINSTANCE.getSimulationConfiguration_OnStoppingDuration();

		/**
		 * The meta object literal for the '<em><b>On Aborting Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_ABORTING_DURATION = eINSTANCE.getSimulationConfiguration_OnAbortingDuration();

		/**
		 * The meta object literal for the '<em><b>On Clearing Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_CLEARING_DURATION = eINSTANCE.getSimulationConfiguration_OnClearingDuration();

		/**
		 * The meta object literal for the '<em><b>On Holding Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_HOLDING_DURATION = eINSTANCE.getSimulationConfiguration_OnHoldingDuration();

		/**
		 * The meta object literal for the '<em><b>On Unholding Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION = eINSTANCE.getSimulationConfiguration_OnUnholdingDuration();

		/**
		 * The meta object literal for the '<em><b>On Suspending Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION = eINSTANCE.getSimulationConfiguration_OnSuspendingDuration();

		/**
		 * The meta object literal for the '<em><b>On Unsuspending Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION = eINSTANCE.getSimulationConfiguration_OnUnsuspendingDuration();

		/**
		 * The meta object literal for the '<em><b>On Completing Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES = eINSTANCE.getSimulationConfiguration_OnCompletingVariables();

		/**
		 * The meta object literal for the '<em><b>On Completing Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE = eINSTANCE.getSimulationConfiguration_OnCompletingErrorCode();

		/**
		 * The meta object literal for the '<em><b>On Completing Error Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE = eINSTANCE.getSimulationConfiguration_OnCompletingErrorMessage();

		/**
		 * The meta object literal for the '<em><b>On Stopping Status Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE = eINSTANCE.getSimulationConfiguration_OnStoppingStatusCode();

		/**
		 * The meta object literal for the '<em><b>On Stopping Error Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE = eINSTANCE.getSimulationConfiguration_OnStoppingErrorMessage();

		/**
		 * The meta object literal for the '<em><b>On Stopping Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES = eINSTANCE.getSimulationConfiguration_OnStoppingVariables();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl <em>Component Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentInfo()
		 * @generated
		 */
		EClass COMPONENT_INFO = eINSTANCE.getComponentInfo();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__ID = eINSTANCE.getComponentInfo_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__NAME = eINSTANCE.getComponentInfo_Name();

		/**
		 * The meta object literal for the '<em><b>Execution State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__EXECUTION_STATE = eINSTANCE.getComponentInfo_ExecutionState();

		/**
		 * The meta object literal for the '<em><b>Execution Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__EXECUTION_MODE = eINSTANCE.getComponentInfo_ExecutionMode();

		/**
		 * The meta object literal for the '<em><b>Operation Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__OPERATION_MODE = eINSTANCE.getComponentInfo_OperationMode();

		/**
		 * The meta object literal for the '<em><b>Work State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__WORK_STATE = eINSTANCE.getComponentInfo_WorkState();

		/**
		 * The meta object literal for the '<em><b>Occupation Status</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_INFO__OCCUPATION_STATUS = eINSTANCE.getComponentInfo_OccupationStatus();

		/**
		 * The meta object literal for the '<em><b>Error Status</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_INFO__ERROR_STATUS = eINSTANCE.getComponentInfo_ErrorStatus();

		/**
		 * The meta object literal for the '<em><b>Activated</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__ACTIVATED = eINSTANCE.getComponentInfo_Activated();

		/**
		 * The meta object literal for the '<em><b>Connected To External</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INFO__CONNECTED_TO_EXTERNAL = eINSTANCE.getComponentInfo_ConnectedToExternal();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentOrderImpl <em>Component Order</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentOrderImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentOrder()
		 * @generated
		 */
		EClass COMPONENT_ORDER = eINSTANCE.getComponentOrder();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ComponentOrderStatusImpl <em>Component Order Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ComponentOrderStatusImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getComponentOrderStatus()
		 * @generated
		 */
		EClass COMPONENT_ORDER_STATUS = eINSTANCE.getComponentOrderStatus();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_ORDER_STATUS__STATUS = eINSTANCE.getComponentOrderStatus_Status();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_ORDER_STATUS__MESSAGE = eINSTANCE.getComponentOrderStatus_Message();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.OccupationStatusImpl <em>Occupation Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.OccupationStatusImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getOccupationStatus()
		 * @generated
		 */
		EClass OCCUPATION_STATUS = eINSTANCE.getOccupationStatus();

		/**
		 * The meta object literal for the '<em><b>Occupier Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCUPATION_STATUS__OCCUPIER_ID = eINSTANCE.getOccupationStatus_OccupierId();

		/**
		 * The meta object literal for the '<em><b>Level</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCUPATION_STATUS__LEVEL = eINSTANCE.getOccupationStatus_Level();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ErrorStatusImpl <em>Error Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ErrorStatusImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getErrorStatus()
		 * @generated
		 */
		EClass ERROR_STATUS = eINSTANCE.getErrorStatus();

		/**
		 * The meta object literal for the '<em><b>Error Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_STATUS__ERROR_CODE = eINSTANCE.getErrorStatus_ErrorCode();

		/**
		 * The meta object literal for the '<em><b>Error Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ERROR_STATUS__ERROR_MESSAGE = eINSTANCE.getErrorStatus_ErrorMessage();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl <em>Parameter Info</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getParameterInfo()
		 * @generated
		 */
		EClass PARAMETER_INFO = eINSTANCE.getParameterInfo();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_INFO__NAME = eINSTANCE.getParameterInfo_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_INFO__TYPE = eINSTANCE.getParameterInfo_Type();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_INFO__DIRECTION = eINSTANCE.getParameterInfo_Direction();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_INFO__VALUE = eINSTANCE.getParameterInfo_Value();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.OccupationLevel <em>Occupation Level</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.OccupationLevel
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getOccupationLevel()
		 * @generated
		 */
		EEnum OCCUPATION_LEVEL = eINSTANCE.getOccupationLevel();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.ExecutionMode <em>Execution Mode</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.ExecutionMode
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getExecutionMode()
		 * @generated
		 */
		EEnum EXECUTION_MODE = eINSTANCE.getExecutionMode();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.ExecutionState <em>Execution State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.ExecutionState
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getExecutionState()
		 * @generated
		 */
		EEnum EXECUTION_STATE = eINSTANCE.getExecutionState();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.ExecutionCommand <em>Execution Command</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.ExecutionCommand
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getExecutionCommand()
		 * @generated
		 */
		EEnum EXECUTION_COMMAND = eINSTANCE.getExecutionCommand();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.OrderStatus <em>Order Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.OrderStatus
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getOrderStatus()
		 * @generated
		 */
		EEnum ORDER_STATUS = eINSTANCE.getOrderStatus();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.VariableType <em>Variable Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.VariableType
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getVariableType()
		 * @generated
		 */
		EEnum VARIABLE_TYPE = eINSTANCE.getVariableType();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.VariableAccess <em>Variable Access</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.VariableAccess
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getVariableAccess()
		 * @generated
		 */
		EEnum VARIABLE_ACCESS = eINSTANCE.getVariableAccess();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.ParameterDirection <em>Parameter Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.ParameterDirection
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getParameterDirection()
		 * @generated
		 */
		EEnum PARAMETER_DIRECTION = eINSTANCE.getParameterDirection();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.ParameterType <em>Parameter Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.ParameterType
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getParameterType()
		 * @generated
		 */
		EEnum PARAMETER_TYPE = eINSTANCE.getParameterType();

		/**
		 * The meta object literal for the '<em>Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see de.dfki.cos.basys.controlcomponent.impl.ControlcomponentPackageImpl#getObject()
		 * @generated
		 */
		EDataType OBJECT = eINSTANCE.getObject();

	}

} //ControlcomponentPackage
