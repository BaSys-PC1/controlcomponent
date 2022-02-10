/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getProfileRequirements <em>Profile Requirements</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getNode()
 * @model abstract="true"
 * @generated
 */
public interface Node extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getNode_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Profile Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile Requirements</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getNode_ProfileRequirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProfileRequirement> getProfileRequirements();

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getNode_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.profiles.Node#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // Node
