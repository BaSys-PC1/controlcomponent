/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getType <em>Type</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getDirection <em>Direction</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getParameterInfo()
 * @model
 * @generated
 */
public interface ParameterInfo extends EObject {
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
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getParameterInfo_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.ParameterType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterType
	 * @see #setType(ParameterType)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getParameterInfo_Type()
	 * @model
	 * @generated
	 */
	ParameterType getType();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterType
	 * @see #getType()
	 * @generated
	 */
	void setType(ParameterType value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.ParameterDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterDirection
	 * @see #setDirection(ParameterDirection)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getParameterInfo_Direction()
	 * @model
	 * @generated
	 */
	ParameterDirection getDirection();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ParameterDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(ParameterDirection value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Object)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getParameterInfo_Value()
	 * @model dataType="de.dfki.cos.basys.controlcomponent.Object"
	 * @generated
	 */
	Object getValue();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ParameterInfo#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Object value);

} // ParameterInfo
