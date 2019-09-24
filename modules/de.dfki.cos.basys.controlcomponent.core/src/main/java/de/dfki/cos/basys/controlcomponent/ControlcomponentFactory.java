/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage
 * @generated
 */
public interface ControlcomponentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ControlcomponentFactory eINSTANCE = de.dfki.cos.basys.controlcomponent.impl.ControlcomponentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Component Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Configuration</em>'.
	 * @generated
	 */
	ComponentConfiguration createComponentConfiguration();

	/**
	 * Returns a new object of class '<em>Configuration Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Configuration Property</em>'.
	 * @generated
	 */
	ConfigurationProperty createConfigurationProperty();

	/**
	 * Returns a new object of class '<em>Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable</em>'.
	 * @generated
	 */
	Variable createVariable();

	/**
	 * Returns a new object of class '<em>Simulation Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simulation Configuration</em>'.
	 * @generated
	 */
	SimulationConfiguration createSimulationConfiguration();

	/**
	 * Returns a new object of class '<em>Component Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Info</em>'.
	 * @generated
	 */
	ComponentInfo createComponentInfo();

	/**
	 * Returns a new object of class '<em>Component Order</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Order</em>'.
	 * @generated
	 */
	ComponentOrder createComponentOrder();

	/**
	 * Returns a new object of class '<em>Component Order Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Order Status</em>'.
	 * @generated
	 */
	ComponentOrderStatus createComponentOrderStatus();

	/**
	 * Returns a new object of class '<em>Occupation Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Occupation Status</em>'.
	 * @generated
	 */
	OccupationStatus createOccupationStatus();

	/**
	 * Returns a new object of class '<em>Error Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Error Status</em>'.
	 * @generated
	 */
	ErrorStatus createErrorStatus();

	/**
	 * Returns a new object of class '<em>Parameter Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Info</em>'.
	 * @generated
	 */
	ParameterInfo createParameterInfo();

	/**
	 * Returns a new object of class '<em>Operation Mode Info</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Mode Info</em>'.
	 * @generated
	 */
	OperationModeInfo createOperationModeInfo();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ControlcomponentPackage getControlcomponentPackage();

} //ControlcomponentFactory
