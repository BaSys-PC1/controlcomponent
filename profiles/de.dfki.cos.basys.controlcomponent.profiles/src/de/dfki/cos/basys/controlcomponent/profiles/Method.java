/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Method#getInputParameters <em>Input Parameters</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Method#getOutputParameters <em>Output Parameters</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends Node {
	/**
	 * Returns the value of the '<em><b>Input Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.profiles.InputParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Parameters</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getMethod_InputParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<InputParameter> getInputParameters();

	/**
	 * Returns the value of the '<em><b>Output Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.profiles.OutputParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Parameters</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getMethod_OutputParameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<OutputParameter> getOutputParameters();

} // Method
