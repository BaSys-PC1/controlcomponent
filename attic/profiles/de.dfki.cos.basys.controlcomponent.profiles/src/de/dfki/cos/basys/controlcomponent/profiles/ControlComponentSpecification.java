/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control Component Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getProfiles <em>Profiles</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getRootNode <em>Root Node</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getControlComponentSpecification()
 * @model
 * @generated
 */
public interface ControlComponentSpecification extends EObject {
	/**
	 * Returns the value of the '<em><b>Profiles</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profiles</em>' containment reference.
	 * @see #setProfiles(Profiles)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getControlComponentSpecification_Profiles()
	 * @model containment="true"
	 * @generated
	 */
	Profiles getProfiles();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getProfiles <em>Profiles</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profiles</em>' containment reference.
	 * @see #getProfiles()
	 * @generated
	 */
	void setProfiles(Profiles value);

	/**
	 * Returns the value of the '<em><b>Root Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Node</em>' containment reference.
	 * @see #setRootNode(Node)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getControlComponentSpecification_RootNode()
	 * @model containment="true"
	 * @generated
	 */
	Node getRootNode();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.ControlComponentSpecification#getRootNode <em>Root Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Node</em>' containment reference.
	 * @see #getRootNode()
	 * @generated
	 */
	void setRootNode(Node value);

} // ControlComponentSpecification
