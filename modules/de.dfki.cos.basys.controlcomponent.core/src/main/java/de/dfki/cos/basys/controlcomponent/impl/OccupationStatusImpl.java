/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.OccupationLevel;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Occupation Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OccupationStatusImpl#getOccupierId <em>Occupier Id</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OccupationStatusImpl#getLevel <em>Level</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OccupationStatusImpl extends MinimalEObjectImpl.Container implements OccupationStatus {
	/**
	 * The default value of the '{@link #getOccupierId() <em>Occupier Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccupierId()
	 * @generated
	 * @ordered
	 */
	protected static final String OCCUPIER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOccupierId() <em>Occupier Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccupierId()
	 * @generated
	 * @ordered
	 */
	protected String occupierId = OCCUPIER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected static final OccupationLevel LEVEL_EDEFAULT = OccupationLevel.FREE;

	/**
	 * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLevel()
	 * @generated
	 * @ordered
	 */
	protected OccupationLevel level = LEVEL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OccupationStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.OCCUPATION_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOccupierId() {
		return occupierId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOccupierId(String newOccupierId) {
		String oldOccupierId = occupierId;
		occupierId = newOccupierId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.OCCUPATION_STATUS__OCCUPIER_ID, oldOccupierId, occupierId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OccupationLevel getLevel() {
		return level;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLevel(OccupationLevel newLevel) {
		OccupationLevel oldLevel = level;
		level = newLevel == null ? LEVEL_EDEFAULT : newLevel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.OCCUPATION_STATUS__LEVEL, oldLevel, level));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlcomponentPackage.OCCUPATION_STATUS__OCCUPIER_ID:
				return getOccupierId();
			case ControlcomponentPackage.OCCUPATION_STATUS__LEVEL:
				return getLevel();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ControlcomponentPackage.OCCUPATION_STATUS__OCCUPIER_ID:
				setOccupierId((String)newValue);
				return;
			case ControlcomponentPackage.OCCUPATION_STATUS__LEVEL:
				setLevel((OccupationLevel)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ControlcomponentPackage.OCCUPATION_STATUS__OCCUPIER_ID:
				setOccupierId(OCCUPIER_ID_EDEFAULT);
				return;
			case ControlcomponentPackage.OCCUPATION_STATUS__LEVEL:
				setLevel(LEVEL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ControlcomponentPackage.OCCUPATION_STATUS__OCCUPIER_ID:
				return OCCUPIER_ID_EDEFAULT == null ? occupierId != null : !OCCUPIER_ID_EDEFAULT.equals(occupierId);
			case ControlcomponentPackage.OCCUPATION_STATUS__LEVEL:
				return level != LEVEL_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder("OccupationStatus ");
		result.append(" (occupierId: ");
		result.append(occupierId);
		result.append(", level: ");
		result.append(level);
		result.append(')');
		return result.toString();
	}

	public static class Builder {
		private String occupierId;
		private OccupationLevel level;

		public Builder occupierId(String occupierId) {
			this.occupierId = occupierId;
			return this;
		}

		public Builder level(OccupationLevel level) {
			this.level = level;
			return this;
		}

		public OccupationStatusImpl build() {
			return new OccupationStatusImpl(this);
		}

	}

	private OccupationStatusImpl(Builder builder) {
		this.occupierId = builder.occupierId;
		this.level = builder.level;
	}

} //OccupationStatusImpl
