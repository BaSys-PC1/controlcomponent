/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ComponentInfo;
import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.ErrorStatus;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ExecutionState;
import de.dfki.cos.basys.controlcomponent.OccupationStatus;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getId <em>Id</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getExecutionState <em>Execution State</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getOperationMode <em>Operation Mode</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getWorkState <em>Work State</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getOccupationStatus <em>Occupation Status</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#getErrorStatus <em>Error Status</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#isActivated <em>Activated</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.ComponentInfoImpl#isConnectedToExternal <em>Connected To External</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentInfoImpl extends MinimalEObjectImpl.Container implements ComponentInfo {
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
	 * The default value of the '{@link #getExecutionState() <em>Execution State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionState()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionState EXECUTION_STATE_EDEFAULT = ExecutionState.UNDEFINED;

	/**
	 * The cached value of the '{@link #getExecutionState() <em>Execution State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionState()
	 * @generated
	 * @ordered
	 */
	protected ExecutionState executionState = EXECUTION_STATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionMode()
	 * @generated
	 * @ordered
	 */
	protected static final ExecutionMode EXECUTION_MODE_EDEFAULT = ExecutionMode.UNDEFINED;

	/**
	 * The cached value of the '{@link #getExecutionMode() <em>Execution Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionMode()
	 * @generated
	 * @ordered
	 */
	protected ExecutionMode executionMode = EXECUTION_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOperationMode() <em>Operation Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationMode()
	 * @generated
	 * @ordered
	 */
	protected static final String OPERATION_MODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperationMode() <em>Operation Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationMode()
	 * @generated
	 * @ordered
	 */
	protected String operationMode = OPERATION_MODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkState() <em>Work State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkState()
	 * @generated
	 * @ordered
	 */
	protected static final String WORK_STATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWorkState() <em>Work State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkState()
	 * @generated
	 * @ordered
	 */
	protected String workState = WORK_STATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOccupationStatus() <em>Occupation Status</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccupationStatus()
	 * @generated
	 * @ordered
	 */
	protected OccupationStatus occupationStatus;

	/**
	 * The cached value of the '{@link #getErrorStatus() <em>Error Status</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorStatus()
	 * @generated
	 * @ordered
	 */
	protected ErrorStatus errorStatus;

	/**
	 * The default value of the '{@link #isActivated() <em>Activated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVATED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isActivated() <em>Activated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActivated()
	 * @generated
	 * @ordered
	 */
	protected boolean activated = ACTIVATED_EDEFAULT;

	/**
	 * The default value of the '{@link #isConnectedToExternal() <em>Connected To External</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConnectedToExternal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONNECTED_TO_EXTERNAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConnectedToExternal() <em>Connected To External</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConnectedToExternal()
	 * @generated
	 * @ordered
	 */
	protected boolean connectedToExternal = CONNECTED_TO_EXTERNAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.COMPONENT_INFO;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_INFO__ID, oldId,
					id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_INFO__NAME, oldName,
					name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionState getExecutionState() {
		return executionState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionState(ExecutionState newExecutionState) {
		ExecutionState oldExecutionState = executionState;
		executionState = newExecutionState == null ? EXECUTION_STATE_EDEFAULT : newExecutionState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.COMPONENT_INFO__EXECUTION_STATE, oldExecutionState, executionState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionMode(ExecutionMode newExecutionMode) {
		ExecutionMode oldExecutionMode = executionMode;
		executionMode = newExecutionMode == null ? EXECUTION_MODE_EDEFAULT : newExecutionMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.COMPONENT_INFO__EXECUTION_MODE, oldExecutionMode, executionMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOperationMode() {
		return operationMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperationMode(String newOperationMode) {
		String oldOperationMode = operationMode;
		operationMode = newOperationMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.COMPONENT_INFO__OPERATION_MODE, oldOperationMode, operationMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWorkState() {
		return workState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkState(String newWorkState) {
		String oldWorkState = workState;
		workState = newWorkState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_INFO__WORK_STATE,
					oldWorkState, workState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccupationStatus getOccupationStatus() {
		if (occupationStatus != null && occupationStatus.eIsProxy()) {
			InternalEObject oldOccupationStatus = (InternalEObject) occupationStatus;
			occupationStatus = (OccupationStatus) eResolveProxy(oldOccupationStatus);
			if (occupationStatus != oldOccupationStatus) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ControlcomponentPackage.COMPONENT_INFO__OCCUPATION_STATUS, oldOccupationStatus,
							occupationStatus));
			}
		}
		return occupationStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccupationStatus basicGetOccupationStatus() {
		return occupationStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOccupationStatus(OccupationStatus newOccupationStatus) {
		OccupationStatus oldOccupationStatus = occupationStatus;
		occupationStatus = newOccupationStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.COMPONENT_INFO__OCCUPATION_STATUS, oldOccupationStatus, occupationStatus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ErrorStatus getErrorStatus() {
		return errorStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetErrorStatus(ErrorStatus newErrorStatus, NotificationChain msgs) {
		ErrorStatus oldErrorStatus = errorStatus;
		errorStatus = newErrorStatus;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS, oldErrorStatus, newErrorStatus);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorStatus(ErrorStatus newErrorStatus) {
		if (newErrorStatus != errorStatus) {
			NotificationChain msgs = null;
			if (errorStatus != null)
				msgs = ((InternalEObject) errorStatus).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS, null, msgs);
			if (newErrorStatus != null)
				msgs = ((InternalEObject) newErrorStatus).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS, null, msgs);
			msgs = basicSetErrorStatus(newErrorStatus, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS,
					newErrorStatus, newErrorStatus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivated(boolean newActivated) {
		boolean oldActivated = activated;
		activated = newActivated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.COMPONENT_INFO__ACTIVATED,
					oldActivated, activated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConnectedToExternal() {
		return connectedToExternal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectedToExternal(boolean newConnectedToExternal) {
		boolean oldConnectedToExternal = connectedToExternal;
		connectedToExternal = newConnectedToExternal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.COMPONENT_INFO__CONNECTED_TO_EXTERNAL, oldConnectedToExternal,
					connectedToExternal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS:
			return basicSetErrorStatus(null, msgs);
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
		case ControlcomponentPackage.COMPONENT_INFO__ID:
			return getId();
		case ControlcomponentPackage.COMPONENT_INFO__NAME:
			return getName();
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_STATE:
			return getExecutionState();
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_MODE:
			return getExecutionMode();
		case ControlcomponentPackage.COMPONENT_INFO__OPERATION_MODE:
			return getOperationMode();
		case ControlcomponentPackage.COMPONENT_INFO__WORK_STATE:
			return getWorkState();
		case ControlcomponentPackage.COMPONENT_INFO__OCCUPATION_STATUS:
			if (resolve)
				return getOccupationStatus();
			return basicGetOccupationStatus();
		case ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS:
			return getErrorStatus();
		case ControlcomponentPackage.COMPONENT_INFO__ACTIVATED:
			return isActivated();
		case ControlcomponentPackage.COMPONENT_INFO__CONNECTED_TO_EXTERNAL:
			return isConnectedToExternal();
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
		case ControlcomponentPackage.COMPONENT_INFO__ID:
			setId((String) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__NAME:
			setName((String) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_STATE:
			setExecutionState((ExecutionState) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_MODE:
			setExecutionMode((ExecutionMode) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__OPERATION_MODE:
			setOperationMode((String) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__WORK_STATE:
			setWorkState((String) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__OCCUPATION_STATUS:
			setOccupationStatus((OccupationStatus) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS:
			setErrorStatus((ErrorStatus) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__ACTIVATED:
			setActivated((Boolean) newValue);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__CONNECTED_TO_EXTERNAL:
			setConnectedToExternal((Boolean) newValue);
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
		case ControlcomponentPackage.COMPONENT_INFO__ID:
			setId(ID_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_STATE:
			setExecutionState(EXECUTION_STATE_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_MODE:
			setExecutionMode(EXECUTION_MODE_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__OPERATION_MODE:
			setOperationMode(OPERATION_MODE_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__WORK_STATE:
			setWorkState(WORK_STATE_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__OCCUPATION_STATUS:
			setOccupationStatus((OccupationStatus) null);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS:
			setErrorStatus((ErrorStatus) null);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__ACTIVATED:
			setActivated(ACTIVATED_EDEFAULT);
			return;
		case ControlcomponentPackage.COMPONENT_INFO__CONNECTED_TO_EXTERNAL:
			setConnectedToExternal(CONNECTED_TO_EXTERNAL_EDEFAULT);
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
		case ControlcomponentPackage.COMPONENT_INFO__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case ControlcomponentPackage.COMPONENT_INFO__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_STATE:
			return executionState != EXECUTION_STATE_EDEFAULT;
		case ControlcomponentPackage.COMPONENT_INFO__EXECUTION_MODE:
			return executionMode != EXECUTION_MODE_EDEFAULT;
		case ControlcomponentPackage.COMPONENT_INFO__OPERATION_MODE:
			return OPERATION_MODE_EDEFAULT == null ? operationMode != null
					: !OPERATION_MODE_EDEFAULT.equals(operationMode);
		case ControlcomponentPackage.COMPONENT_INFO__WORK_STATE:
			return WORK_STATE_EDEFAULT == null ? workState != null : !WORK_STATE_EDEFAULT.equals(workState);
		case ControlcomponentPackage.COMPONENT_INFO__OCCUPATION_STATUS:
			return occupationStatus != null;
		case ControlcomponentPackage.COMPONENT_INFO__ERROR_STATUS:
			return errorStatus != null;
		case ControlcomponentPackage.COMPONENT_INFO__ACTIVATED:
			return activated != ACTIVATED_EDEFAULT;
		case ControlcomponentPackage.COMPONENT_INFO__CONNECTED_TO_EXTERNAL:
			return connectedToExternal != CONNECTED_TO_EXTERNAL_EDEFAULT;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", executionState: ");
		result.append(executionState);
		result.append(", executionMode: ");
		result.append(executionMode);
		result.append(", operationMode: ");
		result.append(operationMode);
		result.append(", workState: ");
		result.append(workState);
		result.append(", activated: ");
		result.append(activated);
		result.append(", connectedToExternal: ");
		result.append(connectedToExternal);
		result.append(')');
		return result.toString();
	}

	public static class Builder {
		private String id;
		private String name;
		private ExecutionState executionState;
		private ExecutionMode executionMode;
		private String operationMode;
		private String workState;
		private OccupationStatus occupationStatus;
		private ErrorStatus errorStatus;
		private boolean activated;
		private boolean connectedToExternal;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder executionState(ExecutionState executionState) {
			this.executionState = executionState;
			return this;
		}

		public Builder executionMode(ExecutionMode executionMode) {
			this.executionMode = executionMode;
			return this;
		}

		public Builder operationMode(String operationMode) {
			this.operationMode = operationMode;
			return this;
		}

		public Builder workState(String workState) {
			this.workState = workState;
			return this;
		}

		public Builder occupationStatus(OccupationStatus occupationStatus) {
			this.occupationStatus = occupationStatus;
			return this;
		}

		public Builder errorStatus(ErrorStatus errorStatus) {
			this.errorStatus = errorStatus;
			return this;
		}

		public Builder activated(boolean activated) {
			this.activated = activated;
			return this;
		}

		public Builder connectedToExternal(boolean connectedToExternal) {
			this.connectedToExternal = connectedToExternal;
			return this;
		}

		public ComponentInfoImpl build() {
			return new ComponentInfoImpl(this);
		}

	}

	private ComponentInfoImpl(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.executionState = builder.executionState;
		this.executionMode = builder.executionMode;
		this.operationMode = builder.operationMode;
		this.workState = builder.workState;
		this.occupationStatus = builder.occupationStatus;
		this.errorStatus = builder.errorStatus;
		this.activated = builder.activated;
		this.connectedToExternal = builder.connectedToExternal;
	}

} //ComponentInfoImpl
