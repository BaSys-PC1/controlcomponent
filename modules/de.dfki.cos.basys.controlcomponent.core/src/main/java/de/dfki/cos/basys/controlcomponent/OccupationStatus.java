/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Occupation Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OccupationStatus#getOccupierId <em>Occupier Id</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OccupationStatus#getLevel <em>Level</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOccupationStatus()
 * @model
 * @generated
 */
public interface OccupationStatus extends EObject {
	/**
	 * Returns the value of the '<em><b>Occupier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occupier Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occupier Id</em>' attribute.
	 * @see #setOccupierId(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOccupationStatus_OccupierId()
	 * @model
	 * @generated
	 */
	String getOccupierId();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.OccupationStatus#getOccupierId <em>Occupier Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occupier Id</em>' attribute.
	 * @see #getOccupierId()
	 * @generated
	 */
	void setOccupierId(String value);

	/**
	 * Returns the value of the '<em><b>Level</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.OccupationLevel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Level</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Level</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.OccupationLevel
	 * @see #setLevel(OccupationLevel)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOccupationStatus_Level()
	 * @model
	 * @generated
	 */
	OccupationLevel getLevel();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.OccupationStatus#getLevel <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.OccupationLevel
	 * @see #getLevel()
	 * @generated
	 */
	void setLevel(OccupationLevel value);

} // OccupationStatus
