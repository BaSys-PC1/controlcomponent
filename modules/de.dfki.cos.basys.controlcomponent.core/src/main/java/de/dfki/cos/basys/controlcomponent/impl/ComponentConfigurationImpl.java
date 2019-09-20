/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ComponentConfiguration;
import de.dfki.cos.basys.controlcomponent.ConfigurationProperty;
import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl#getImplementationJavaClass <em>Implementation Java Class</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl#getExternalConnectionString <em>External Connection String</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentConfigurationImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentConfigurationImpl extends MinimalEObjectImpl.Container implements ComponentConfiguration {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	 * The default value of the '{@link #getImplementationJavaClass() <em>Implementation Java Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationJavaClass()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_JAVA_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationJavaClass() <em>Implementation Java Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationJavaClass()
	 * @generated
	 * @ordered
	 */
	protected String implementationJavaClass = IMPLEMENTATION_JAVA_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getExternalConnectionString() <em>External Connection String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalConnectionString()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTERNAL_CONNECTION_STRING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExternalConnectionString() <em>External Connection String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalConnectionString()
	 * @generated
	 * @ordered
	 */
	protected String externalConnectionString = EXTERNAL_CONNECTION_STRING_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ConfigurationProperty> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.COMPONENT_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_CONFIGURATION__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_CONFIGURATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImplementationJavaClass() {
		return implementationJavaClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementationJavaClass(String newImplementationJavaClass) {
		String oldImplementationJavaClass = implementationJavaClass;
		implementationJavaClass = newImplementationJavaClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS, oldImplementationJavaClass, implementationJavaClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExternalConnectionString() {
		return externalConnectionString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalConnectionString(String newExternalConnectionString) {
		String oldExternalConnectionString = externalConnectionString;
		externalConnectionString = newExternalConnectionString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING, oldExternalConnectionString, externalConnectionString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConfigurationProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList<ConfigurationProperty>(ConfigurationProperty.class, this, ControlcomponentPackage.COMPONENT_CONFIGURATION__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ConfigurationProperty getProperty(String key) {
		for (ConfigurationProperty p : getProperties()) {
			if (p.getKey().equals(key))
				return p;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__ID:
				return getId();
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__NAME:
				return getName();
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS:
				return getImplementationJavaClass();
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING:
				return getExternalConnectionString();
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__PROPERTIES:
				return getProperties();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__ID:
				setId((String)newValue);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__NAME:
				setName((String)newValue);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS:
				setImplementationJavaClass((String)newValue);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING:
				setExternalConnectionString((String)newValue);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends ConfigurationProperty>)newValue);
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
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__ID:
				setId(ID_EDEFAULT);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS:
				setImplementationJavaClass(IMPLEMENTATION_JAVA_CLASS_EDEFAULT);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING:
				setExternalConnectionString(EXTERNAL_CONNECTION_STRING_EDEFAULT);
				return;
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__PROPERTIES:
				getProperties().clear();
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
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__IMPLEMENTATION_JAVA_CLASS:
				return IMPLEMENTATION_JAVA_CLASS_EDEFAULT == null ? implementationJavaClass != null : !IMPLEMENTATION_JAVA_CLASS_EDEFAULT.equals(implementationJavaClass);
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__EXTERNAL_CONNECTION_STRING:
				return EXTERNAL_CONNECTION_STRING_EDEFAULT == null ? externalConnectionString != null : !EXTERNAL_CONNECTION_STRING_EDEFAULT.equals(externalConnectionString);
			case ControlcomponentPackage.COMPONENT_CONFIGURATION__PROPERTIES:
				return properties != null && !properties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ControlcomponentPackage.COMPONENT_CONFIGURATION___GET_PROPERTY__STRING:
				return getProperty((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", implementationJavaClass: ");
		result.append(implementationJavaClass);
		result.append(", externalConnectionString: ");
		result.append(externalConnectionString);
		result.append(')');
		return result.toString();
	}
	

	public static class Builder {
		private String id;
		private String name;
		private String implementationJavaClass;
		private String externalConnectionString;
		private EList<ConfigurationProperty> properties;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder implementationJavaClass(String implementationJavaClass) {
			this.implementationJavaClass = implementationJavaClass;
			return this;
		}

		public Builder externalConnectionString(String externalConnectionString) {
			this.externalConnectionString = externalConnectionString;
			return this;
		}

		public Builder properties(EList<ConfigurationProperty> properties) {
			this.properties = properties;
			return this;
		}

		public ComponentConfigurationImpl build() {
			return new ComponentConfigurationImpl(this);
		}

	}

	private ComponentConfigurationImpl(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.implementationJavaClass = builder.implementationJavaClass;
		this.externalConnectionString = builder.externalConnectionString;
		this.properties = builder.properties;
	}

} //ComponentConfigurationImpl
