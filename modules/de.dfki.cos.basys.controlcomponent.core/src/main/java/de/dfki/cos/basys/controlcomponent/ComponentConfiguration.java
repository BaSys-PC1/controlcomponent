/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getId <em>Id</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getImplementationJavaClass <em>Implementation Java Class</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getExternalConnectionString <em>External Connection String</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentConfiguration()
 * @model
 * @generated
 */
public interface ComponentConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentConfiguration_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentConfiguration_Name()
	 * @model ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Implementation Java Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Java Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation Java Class</em>' attribute.
	 * @see #setImplementationJavaClass(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentConfiguration_ImplementationJavaClass()
	 * @model
	 * @generated
	 */
	String getImplementationJavaClass();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getImplementationJavaClass <em>Implementation Java Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation Java Class</em>' attribute.
	 * @see #getImplementationJavaClass()
	 * @generated
	 */
	void setImplementationJavaClass(String value);

	/**
	 * Returns the value of the '<em><b>External Connection String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Connection String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Connection String</em>' attribute.
	 * @see #setExternalConnectionString(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentConfiguration_ExternalConnectionString()
	 * @model
	 * @generated
	 */
	String getExternalConnectionString();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentConfiguration#getExternalConnectionString <em>External Connection String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Connection String</em>' attribute.
	 * @see #getExternalConnectionString()
	 * @generated
	 */
	void setExternalConnectionString(String value);

	/**
	 * Returns the value of the '<em><b>Properties</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.ConfigurationProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentConfiguration_Properties()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConfigurationProperty> getProperties();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ConfigurationProperty getProperty(String key);

} // ComponentConfiguration
