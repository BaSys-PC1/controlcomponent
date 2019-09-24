/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Error Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ErrorStatusImpl#getErrorCode <em>Error Code</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ErrorStatusImpl#getErrorMessage <em>Error Message</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ErrorStatusImpl extends MinimalEObjectImpl.Container implements ErrorStatus {
	/**
	 * The default value of the '{@link #getErrorCode() <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorCode()
	 * @generated
	 * @ordered
	 */
	protected static final int ERROR_CODE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getErrorCode() <em>Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorCode()
	 * @generated
	 * @ordered
	 */
	protected int errorCode = ERROR_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getErrorMessage() <em>Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorMessage() <em>Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorMessage()
	 * @generated
	 * @ordered
	 */
	protected String errorMessage = ERROR_MESSAGE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ErrorStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.ERROR_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorCode(int newErrorCode) {
		int oldErrorCode = errorCode;
		errorCode = newErrorCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.ERROR_STATUS__ERROR_CODE,
					oldErrorCode, errorCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorMessage(String newErrorMessage) {
		String oldErrorMessage = errorMessage;
		errorMessage = newErrorMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.ERROR_STATUS__ERROR_MESSAGE,
					oldErrorMessage, errorMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ControlcomponentPackage.ERROR_STATUS__ERROR_CODE:
			return getErrorCode();
		case ControlcomponentPackage.ERROR_STATUS__ERROR_MESSAGE:
			return getErrorMessage();
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
		case ControlcomponentPackage.ERROR_STATUS__ERROR_CODE:
			setErrorCode((Integer) newValue);
			return;
		case ControlcomponentPackage.ERROR_STATUS__ERROR_MESSAGE:
			setErrorMessage((String) newValue);
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
		case ControlcomponentPackage.ERROR_STATUS__ERROR_CODE:
			setErrorCode(ERROR_CODE_EDEFAULT);
			return;
		case ControlcomponentPackage.ERROR_STATUS__ERROR_MESSAGE:
			setErrorMessage(ERROR_MESSAGE_EDEFAULT);
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
		case ControlcomponentPackage.ERROR_STATUS__ERROR_CODE:
			return errorCode != ERROR_CODE_EDEFAULT;
		case ControlcomponentPackage.ERROR_STATUS__ERROR_MESSAGE:
			return ERROR_MESSAGE_EDEFAULT == null ? errorMessage != null : !ERROR_MESSAGE_EDEFAULT.equals(errorMessage);
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
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (errorCode: ");
		result.append(errorCode);
		result.append(", errorMessage: ");
		result.append(errorMessage);
		result.append(')');
		return result.toString();
	}

	public static class Builder {
		private int errorCode;
		private String errorMessage;

		public Builder errorCode(int errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public Builder errorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public ErrorStatusImpl build() {
			return new ErrorStatusImpl(this);
		}

	}

	private ErrorStatusImpl(Builder builder) {
		this.errorCode = builder.errorCode;
		this.errorMessage = builder.errorMessage;
	}

} //ErrorStatusImpl
