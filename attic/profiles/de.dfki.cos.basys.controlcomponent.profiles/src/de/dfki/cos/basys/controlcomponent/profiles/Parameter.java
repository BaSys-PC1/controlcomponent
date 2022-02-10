/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getParameter()
 * @model abstract="true"
 * @generated
 */
public interface Parameter extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getParameter_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.profiles.VariableType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
	 * @see #setType(VariableType)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getParameter_Type()
	 * @model
	 * @generated
	 */
	VariableType getType();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.Parameter#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
	 * @see #getType()
	 * @generated
	 */
	void setType(VariableType value);

} // Parameter
