/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesFactory
 * @model kind="package"
 * @generated
 */
public interface ProfilesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "profiles";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.dfki.de/iui/basys/model/base/ccprofiles";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "profiles";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProfilesPackage eINSTANCE = de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ControlComponentSpecificationImpl <em>Control Component Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ControlComponentSpecificationImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getControlComponentSpecification()
	 * @generated
	 */
	int CONTROL_COMPONENT_SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Profiles</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_COMPONENT_SPECIFICATION__PROFILES = 0;

	/**
	 * The feature id for the '<em><b>Root Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_COMPONENT_SPECIFICATION__ROOT_NODE = 1;

	/**
	 * The number of structural features of the '<em>Control Component Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_COMPONENT_SPECIFICATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Control Component Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_COMPONENT_SPECIFICATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesImpl <em>Profiles</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getProfiles()
	 * @generated
	 */
	int PROFILES = 1;

	/**
	 * The feature id for the '<em><b>Profile</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILES__PROFILE = 0;

	/**
	 * The number of structural features of the '<em>Profiles</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Profiles</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileImpl <em>Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getProfile()
	 * @generated
	 */
	int PROFILE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE__ID = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE__EXTENDS = 3;

	/**
	 * The number of structural features of the '<em>Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.NodeImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Profile Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PROFILE_REQUIREMENTS = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DESCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.NodeSetImpl <em>Node Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.NodeSetImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getNodeSet()
	 * @generated
	 */
	int NODE_SET = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_SET__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Profile Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_SET__PROFILE_REQUIREMENTS = NODE__PROFILE_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_SET__DESCRIPTION = NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_SET__NODES = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_SET_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Node Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_SET_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.VariableImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Profile Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__PROFILE_REQUIREMENTS = NODE__PROFILE_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__DESCRIPTION = NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Writeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__WRITEABLE = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.MethodImpl <em>Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.MethodImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getMethod()
	 * @generated
	 */
	int METHOD = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Profile Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__PROFILE_REQUIREMENTS = NODE__PROFILE_REQUIREMENTS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__DESCRIPTION = NODE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Input Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__INPUT_PARAMETERS = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Output Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__OUTPUT_PARAMETERS = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileRequirementImpl <em>Profile Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileRequirementImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getProfileRequirement()
	 * @generated
	 */
	int PROFILE_REQUIREMENT = 7;

	/**
	 * The feature id for the '<em><b>Profile Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_REQUIREMENT__PROFILE_DEFINITION = 0;

	/**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_REQUIREMENT__MANDATORY = 1;

	/**
	 * The number of structural features of the '<em>Profile Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_REQUIREMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Profile Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROFILE_REQUIREMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ParameterImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.InputParameterImpl <em>Input Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.InputParameterImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getInputParameter()
	 * @generated
	 */
	int INPUT_PARAMETER = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__NAME = PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER__TYPE = PARAMETER__TYPE;

	/**
	 * The number of structural features of the '<em>Input Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Input Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PARAMETER_OPERATION_COUNT = PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.OutputParameterImpl <em>Output Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.OutputParameterImpl
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getOutputParameter()
	 * @generated
	 */
	int OUTPUT_PARAMETER = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER__NAME = PARAMETER__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER__TYPE = PARAMETER__TYPE;

	/**
	 * The number of structural features of the '<em>Output Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_FEATURE_COUNT = PARAMETER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Output Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PARAMETER_OPERATION_COUNT = PARAMETER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.dfki.cos.basys.controlcomponent.profiles.VariableType <em>Variable Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
	 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getVariableType()
	 * @generated
	 */
	int VARIABLE_TYPE = 11;


	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification <em>Control Component Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Component Specification</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification
	 * @generated
	 */
	EClass getControlComponentSpecification();

	/**
	 * Returns the meta object for the containment reference '{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getProfiles <em>Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Profiles</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getProfiles()
	 * @see #getControlComponentSpecification()
	 * @generated
	 */
	EReference getControlComponentSpecification_Profiles();

	/**
	 * Returns the meta object for the containment reference '{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getRootNode <em>Root Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root Node</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getRootNode()
	 * @see #getControlComponentSpecification()
	 * @generated
	 */
	EReference getControlComponentSpecification_RootNode();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.Profiles <em>Profiles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profiles</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profiles
	 * @generated
	 */
	EClass getProfiles();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.profiles.Profiles#getProfile <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Profile</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profiles#getProfile()
	 * @see #getProfiles()
	 * @generated
	 */
	EReference getProfiles_Profile();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.Profile <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profile</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profile
	 * @generated
	 */
	EClass getProfile();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Profile#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profile#getName()
	 * @see #getProfile()
	 * @generated
	 */
	EAttribute getProfile_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Profile#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profile#getId()
	 * @see #getProfile()
	 * @generated
	 */
	EAttribute getProfile_Id();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Profile#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profile#getDescription()
	 * @see #getProfile()
	 * @generated
	 */
	EAttribute getProfile_Description();

	/**
	 * Returns the meta object for the reference list '{@link de.dfki.cos.basys.controlcomponent.profiles.Profile#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extends</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Profile#getExtends()
	 * @see #getProfile()
	 * @generated
	 */
	EReference getProfile_Extends();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Node#getName()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getProfileRequirements <em>Profile Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Profile Requirements</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Node#getProfileRequirements()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_ProfileRequirements();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Node#getDescription()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Description();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.NodeSet <em>Node Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Set</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.NodeSet
	 * @generated
	 */
	EClass getNodeSet();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.profiles.NodeSet#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.NodeSet#getNodes()
	 * @see #getNodeSet()
	 * @generated
	 */
	EReference getNodeSet_Nodes();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Variable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Variable#getType()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Type();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Variable#isWriteable <em>Writeable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Writeable</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Variable#isWriteable()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Writeable();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Method
	 * @generated
	 */
	EClass getMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.profiles.Method#getInputParameters <em>Input Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Parameters</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Method#getInputParameters()
	 * @see #getMethod()
	 * @generated
	 */
	EReference getMethod_InputParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link de.dfki.cos.basys.controlcomponent.profiles.Method#getOutputParameters <em>Output Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output Parameters</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Method#getOutputParameters()
	 * @see #getMethod()
	 * @generated
	 */
	EReference getMethod_OutputParameters();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement <em>Profile Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profile Requirement</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement
	 * @generated
	 */
	EClass getProfileRequirement();

	/**
	 * Returns the meta object for the reference '{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#getProfileDefinition <em>Profile Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Profile Definition</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#getProfileDefinition()
	 * @see #getProfileRequirement()
	 * @generated
	 */
	EReference getProfileRequirement_ProfileDefinition();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#isMandatory <em>Mandatory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#isMandatory()
	 * @see #getProfileRequirement()
	 * @generated
	 */
	EAttribute getProfileRequirement_Mandatory();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.Parameter#getType()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Type();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.InputParameter <em>Input Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Parameter</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.InputParameter
	 * @generated
	 */
	EClass getInputParameter();

	/**
	 * Returns the meta object for class '{@link de.dfki.cos.basys.controlcomponent.profiles.OutputParameter <em>Output Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Parameter</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.OutputParameter
	 * @generated
	 */
	EClass getOutputParameter();

	/**
	 * Returns the meta object for enum '{@link de.dfki.cos.basys.controlcomponent.profiles.VariableType <em>Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Variable Type</em>'.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
	 * @generated
	 */
	EEnum getVariableType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProfilesFactory getProfilesFactory();

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
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ControlComponentSpecificationImpl <em>Control Component Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ControlComponentSpecificationImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getControlComponentSpecification()
		 * @generated
		 */
		EClass CONTROL_COMPONENT_SPECIFICATION = eINSTANCE.getControlComponentSpecification();

		/**
		 * The meta object literal for the '<em><b>Profiles</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_COMPONENT_SPECIFICATION__PROFILES = eINSTANCE.getControlComponentSpecification_Profiles();

		/**
		 * The meta object literal for the '<em><b>Root Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_COMPONENT_SPECIFICATION__ROOT_NODE = eINSTANCE.getControlComponentSpecification_RootNode();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesImpl <em>Profiles</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getProfiles()
		 * @generated
		 */
		EClass PROFILES = eINSTANCE.getProfiles();

		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILES__PROFILE = eINSTANCE.getProfiles_Profile();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileImpl <em>Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getProfile()
		 * @generated
		 */
		EClass PROFILE = eINSTANCE.getProfile();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE__NAME = eINSTANCE.getProfile_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE__ID = eINSTANCE.getProfile_Id();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE__DESCRIPTION = eINSTANCE.getProfile_Description();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE__EXTENDS = eINSTANCE.getProfile_Extends();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.NodeImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__NAME = eINSTANCE.getNode_Name();

		/**
		 * The meta object literal for the '<em><b>Profile Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__PROFILE_REQUIREMENTS = eINSTANCE.getNode_ProfileRequirements();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__DESCRIPTION = eINSTANCE.getNode_Description();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.NodeSetImpl <em>Node Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.NodeSetImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getNodeSet()
		 * @generated
		 */
		EClass NODE_SET = eINSTANCE.getNodeSet();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_SET__NODES = eINSTANCE.getNodeSet_Nodes();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.VariableImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__TYPE = eINSTANCE.getVariable_Type();

		/**
		 * The meta object literal for the '<em><b>Writeable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__WRITEABLE = eINSTANCE.getVariable_Writeable();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.MethodImpl <em>Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.MethodImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getMethod()
		 * @generated
		 */
		EClass METHOD = eINSTANCE.getMethod();

		/**
		 * The meta object literal for the '<em><b>Input Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD__INPUT_PARAMETERS = eINSTANCE.getMethod_InputParameters();

		/**
		 * The meta object literal for the '<em><b>Output Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD__OUTPUT_PARAMETERS = eINSTANCE.getMethod_OutputParameters();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileRequirementImpl <em>Profile Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileRequirementImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getProfileRequirement()
		 * @generated
		 */
		EClass PROFILE_REQUIREMENT = eINSTANCE.getProfileRequirement();

		/**
		 * The meta object literal for the '<em><b>Profile Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE_REQUIREMENT__PROFILE_DEFINITION = eINSTANCE.getProfileRequirement_ProfileDefinition();

		/**
		 * The meta object literal for the '<em><b>Mandatory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE_REQUIREMENT__MANDATORY = eINSTANCE.getProfileRequirement_Mandatory();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ParameterImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.InputParameterImpl <em>Input Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.InputParameterImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getInputParameter()
		 * @generated
		 */
		EClass INPUT_PARAMETER = eINSTANCE.getInputParameter();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.impl.OutputParameterImpl <em>Output Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.OutputParameterImpl
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getOutputParameter()
		 * @generated
		 */
		EClass OUTPUT_PARAMETER = eINSTANCE.getOutputParameter();

		/**
		 * The meta object literal for the '{@link de.dfki.cos.basys.controlcomponent.profiles.VariableType <em>Variable Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
		 * @see de.dfki.cos.basys.controlcomponent.profiles.impl.ProfilesPackageImpl#getVariableType()
		 * @generated
		 */
		EEnum VARIABLE_TYPE = eINSTANCE.getVariableType();

	}

} //ProfilesPackage
