/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.ParameterDirection;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;
import de.dfki.cos.basys.controlcomponent.ParameterType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ParameterInfoImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterInfoImpl extends MinimalEObjectImpl.Container implements ParameterInfo {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterType TYPE_EDEFAULT = ParameterType.OBJECT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ParameterType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterDirection DIRECTION_EDEFAULT = ParameterDirection.IN;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected ParameterDirection direction = DIRECTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Object value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.PARAMETER_INFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.PARAMETER_INFO__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ParameterType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(ParameterType newType) {
		ParameterType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.PARAMETER_INFO__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ParameterDirection getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDirection(ParameterDirection newDirection) {
		ParameterDirection oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.PARAMETER_INFO__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setValue(Object newValue) {
		Object oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.PARAMETER_INFO__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlcomponentPackage.PARAMETER_INFO__NAME:
				return getName();
			case ControlcomponentPackage.PARAMETER_INFO__TYPE:
				return getType();
			case ControlcomponentPackage.PARAMETER_INFO__DIRECTION:
				return getDirection();
			case ControlcomponentPackage.PARAMETER_INFO__VALUE:
				return getValue();
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
			case ControlcomponentPackage.PARAMETER_INFO__NAME:
				setName((String)newValue);
				return;
			case ControlcomponentPackage.PARAMETER_INFO__TYPE:
				setType((ParameterType)newValue);
				return;
			case ControlcomponentPackage.PARAMETER_INFO__DIRECTION:
				setDirection((ParameterDirection)newValue);
				return;
			case ControlcomponentPackage.PARAMETER_INFO__VALUE:
				setValue(newValue);
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
			case ControlcomponentPackage.PARAMETER_INFO__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ControlcomponentPackage.PARAMETER_INFO__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ControlcomponentPackage.PARAMETER_INFO__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case ControlcomponentPackage.PARAMETER_INFO__VALUE:
				setValue((Object)null);
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
			case ControlcomponentPackage.PARAMETER_INFO__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ControlcomponentPackage.PARAMETER_INFO__TYPE:
				return type != TYPE_EDEFAULT;
			case ControlcomponentPackage.PARAMETER_INFO__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case ControlcomponentPackage.PARAMETER_INFO__VALUE:
				return value != null;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", direction: ");
		result.append(direction);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

	public static class Builder {
		private String name;
		private ParameterType type;
		private ParameterDirection direction;
		private Object value;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(ParameterType type) {
			this.type = type;
			return this;
		}

		public Builder direction(ParameterDirection direction) {
			this.direction = direction;
			return this;
		}

		public Builder value(Object value) {
			this.value = value;
			return this;
		}

		public ParameterInfoImpl build() {
			return new ParameterInfoImpl(this);
		}

	}

	private ParameterInfoImpl(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.direction = builder.direction;
		this.value = builder.value;
	}
} //ParameterInfoImpl
