/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Variable#getType <em>Type</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Variable#isWriteable <em>Writeable</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getVariable()
 * @model
 * @generated
 */
public interface Variable extends Node {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.profiles.VariableType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
	 * @see #setType(VariableType)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getVariable_Type()
	 * @model
	 * @generated
	 */
	VariableType getType();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.Variable#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.VariableType
	 * @see #getType()
	 * @generated
	 */
	void setType(VariableType value);

	/**
	 * Returns the value of the '<em><b>Writeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Writeable</em>' attribute.
	 * @see #setWriteable(boolean)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getVariable_Writeable()
	 * @model
	 * @generated
	 */
	boolean isWriteable();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.Variable#isWriteable <em>Writeable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Writeable</em>' attribute.
	 * @see #isWriteable()
	 * @generated
	 */
	void setWriteable(boolean value);

} // Variable
