/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profile Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#getProfileDefinition <em>Profile Definition</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#isMandatory <em>Mandatory</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getProfileRequirement()
 * @model
 * @generated
 */
public interface ProfileRequirement extends EObject {
	/**
	 * Returns the value of the '<em><b>Profile Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile Definition</em>' reference.
	 * @see #setProfileDefinition(Profile)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getProfileRequirement_ProfileDefinition()
	 * @model
	 * @generated
	 */
	Profile getProfileDefinition();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#getProfileDefinition <em>Profile Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile Definition</em>' reference.
	 * @see #getProfileDefinition()
	 * @generated
	 */
	void setProfileDefinition(Profile value);

	/**
	 * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory</em>' attribute.
	 * @see #setMandatory(boolean)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getProfileRequirement_Mandatory()
	 * @model
	 * @generated
	 */
	boolean isMandatory();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement#isMandatory <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mandatory</em>' attribute.
	 * @see #isMandatory()
	 * @generated
	 */
	void setMandatory(boolean value);

} // ProfileRequirement
