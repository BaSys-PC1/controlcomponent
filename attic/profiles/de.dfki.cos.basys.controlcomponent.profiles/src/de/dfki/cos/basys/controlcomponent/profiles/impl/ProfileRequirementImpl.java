/**
 */
package de.dfki.cos.basys.controlcomponent.profiles.impl;

import de.dfki.cos.basys.controlcomponent.profiles.Profile;
import de.dfki.cos.basys.controlcomponent.profiles.ProfileRequirement;
import de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile Requirement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileRequirementImpl#getProfileDefinition <em>Profile Definition</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.impl.ProfileRequirementImpl#isMandatory <em>Mandatory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProfileRequirementImpl extends MinimalEObjectImpl.Container implements ProfileRequirement {
	/**
	 * The cached value of the '{@link #getProfileDefinition() <em>Profile Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileDefinition()
	 * @generated
	 * @ordered
	 */
	protected Profile profileDefinition;

	/**
	 * The default value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected boolean mandatory = MANDATORY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileRequirementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProfilesPackage.Literals.PROFILE_REQUIREMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Profile getProfileDefinition() {
		if (profileDefinition != null && profileDefinition.eIsProxy()) {
			InternalEObject oldProfileDefinition = (InternalEObject)profileDefinition;
			profileDefinition = (Profile)eResolveProxy(oldProfileDefinition);
			if (profileDefinition != oldProfileDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ProfilesPackage.PROFILE_REQUIREMENT__PROFILE_DEFINITION, oldProfileDefinition, profileDefinition));
			}
		}
		return profileDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Profile basicGetProfileDefinition() {
		return profileDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProfileDefinition(Profile newProfileDefinition) {
		Profile oldProfileDefinition = profileDefinition;
		profileDefinition = newProfileDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProfilesPackage.PROFILE_REQUIREMENT__PROFILE_DEFINITION, oldProfileDefinition, profileDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isMandatory() {
		return mandatory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMandatory(boolean newMandatory) {
		boolean oldMandatory = mandatory;
		mandatory = newMandatory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProfilesPackage.PROFILE_REQUIREMENT__MANDATORY, oldMandatory, mandatory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProfilesPackage.PROFILE_REQUIREMENT__PROFILE_DEFINITION:
				if (resolve) return getProfileDefinition();
				return basicGetProfileDefinition();
			case ProfilesPackage.PROFILE_REQUIREMENT__MANDATORY:
				return isMandatory();
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
			case ProfilesPackage.PROFILE_REQUIREMENT__PROFILE_DEFINITION:
				setProfileDefinition((Profile)newValue);
				return;
			case ProfilesPackage.PROFILE_REQUIREMENT__MANDATORY:
				setMandatory((Boolean)newValue);
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
			case ProfilesPackage.PROFILE_REQUIREMENT__PROFILE_DEFINITION:
				setProfileDefinition((Profile)null);
				return;
			case ProfilesPackage.PROFILE_REQUIREMENT__MANDATORY:
				setMandatory(MANDATORY_EDEFAULT);
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
			case ProfilesPackage.PROFILE_REQUIREMENT__PROFILE_DEFINITION:
				return profileDefinition != null;
			case ProfilesPackage.PROFILE_REQUIREMENT__MANDATORY:
				return mandatory != MANDATORY_EDEFAULT;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (mandatory: ");
		result.append(mandatory);
		result.append(')');
		return result.toString();
	}

} //ProfileRequirementImpl
