/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Profiles</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Profiles#getProfile <em>Profile</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getProfiles()
 * @model
 * @generated
 */
public interface Profiles extends EObject {
	/**
	 * Returns the value of the '<em><b>Profile</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.profiles.Profile}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getProfiles_Profile()
	 * @model containment="true"
	 * @generated
	 */
	EList<Profile> getProfile();

} // Profiles
