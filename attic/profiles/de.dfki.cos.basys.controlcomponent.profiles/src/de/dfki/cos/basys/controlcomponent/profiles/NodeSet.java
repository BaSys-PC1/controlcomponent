/**
 */
package de.dfki.cos.basys.controlcomponent.profiles;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.NodeSet#getNodes <em>Nodes</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getNodeSet()
 * @model
 * @generated
 */
public interface NodeSet extends Node {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.profiles.Node}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage#getNodeSet_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getNodes();

} // NodeSet
