/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Error Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorMessage <em>Error Message</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getErrorStatus()
 * @model
 * @generated
 */
public interface ErrorStatus extends EObject {
	/**
	 * Returns the value of the '<em><b>Error Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Code</em>' attribute.
	 * @see #setErrorCode(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getErrorStatus_ErrorCode()
	 * @model
	 * @generated
	 */
	int getErrorCode();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorCode <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Code</em>' attribute.
	 * @see #getErrorCode()
	 * @generated
	 */
	void setErrorCode(int value);

	/**
	 * Returns the value of the '<em><b>Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Message</em>' attribute.
	 * @see #setErrorMessage(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getErrorStatus_ErrorMessage()
	 * @model
	 * @generated
	 */
	String getErrorMessage();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ErrorStatus#getErrorMessage <em>Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Message</em>' attribute.
	 * @see #getErrorMessage()
	 * @generated
	 */
	void setErrorMessage(String value);

} // ErrorStatus
