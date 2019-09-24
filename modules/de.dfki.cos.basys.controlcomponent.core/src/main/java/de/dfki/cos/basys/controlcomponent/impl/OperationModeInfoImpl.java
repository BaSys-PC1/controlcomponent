/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.OperationModeInfo;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Mode Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OperationModeInfoImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OperationModeInfoImpl#getShortName <em>Short Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OperationModeInfoImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OperationModeInfoImpl#getExecutionModes <em>Execution Modes</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OperationModeInfoImpl#getExecutionCommands <em>Execution Commands</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.OperationModeInfoImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationModeInfoImpl extends MinimalEObjectImpl.Container implements OperationModeInfo {
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
	 * The default value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortName() <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortName()
	 * @generated
	 * @ordered
	 */
	protected String shortName = SHORT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExecutionModes() <em>Execution Modes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionModes()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionMode> executionModes;

	/**
	 * The cached value of the '{@link #getExecutionCommands() <em>Execution Commands</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionCommands()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionCommand> executionCommands;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterInfo> parameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationModeInfoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.OPERATION_MODE_INFO;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.OPERATION_MODE_INFO__NAME,
					oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getShortName() {
		return shortName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setShortName(String newShortName) {
		String oldShortName = shortName;
		shortName = newShortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.OPERATION_MODE_INFO__SHORT_NAME, oldShortName, shortName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ControlcomponentPackage.OPERATION_MODE_INFO__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExecutionMode> getExecutionModes() {
		if (executionModes == null) {
			executionModes = new EDataTypeUniqueEList<ExecutionMode>(ExecutionMode.class, this,
					ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_MODES);
		}
		return executionModes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ExecutionCommand> getExecutionCommands() {
		if (executionCommands == null) {
			executionCommands = new EDataTypeUniqueEList<ExecutionCommand>(ExecutionCommand.class, this,
					ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_COMMANDS);
		}
		return executionCommands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ParameterInfo> getParameters() {
		if (parameters == null) {
			parameters = new EObjectResolvingEList<ParameterInfo>(ParameterInfo.class, this,
					ControlcomponentPackage.OPERATION_MODE_INFO__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ControlcomponentPackage.OPERATION_MODE_INFO__NAME:
			return getName();
		case ControlcomponentPackage.OPERATION_MODE_INFO__SHORT_NAME:
			return getShortName();
		case ControlcomponentPackage.OPERATION_MODE_INFO__DESCRIPTION:
			return getDescription();
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_MODES:
			return getExecutionModes();
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_COMMANDS:
			return getExecutionCommands();
		case ControlcomponentPackage.OPERATION_MODE_INFO__PARAMETERS:
			return getParameters();
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
		case ControlcomponentPackage.OPERATION_MODE_INFO__NAME:
			setName((String) newValue);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__SHORT_NAME:
			setShortName((String) newValue);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_MODES:
			getExecutionModes().clear();
			getExecutionModes().addAll((Collection<? extends ExecutionMode>) newValue);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_COMMANDS:
			getExecutionCommands().clear();
			getExecutionCommands().addAll((Collection<? extends ExecutionCommand>) newValue);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__PARAMETERS:
			getParameters().clear();
			getParameters().addAll((Collection<? extends ParameterInfo>) newValue);
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
		case ControlcomponentPackage.OPERATION_MODE_INFO__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__SHORT_NAME:
			setShortName(SHORT_NAME_EDEFAULT);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_MODES:
			getExecutionModes().clear();
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_COMMANDS:
			getExecutionCommands().clear();
			return;
		case ControlcomponentPackage.OPERATION_MODE_INFO__PARAMETERS:
			getParameters().clear();
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
		case ControlcomponentPackage.OPERATION_MODE_INFO__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ControlcomponentPackage.OPERATION_MODE_INFO__SHORT_NAME:
			return SHORT_NAME_EDEFAULT == null ? shortName != null : !SHORT_NAME_EDEFAULT.equals(shortName);
		case ControlcomponentPackage.OPERATION_MODE_INFO__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_MODES:
			return executionModes != null && !executionModes.isEmpty();
		case ControlcomponentPackage.OPERATION_MODE_INFO__EXECUTION_COMMANDS:
			return executionCommands != null && !executionCommands.isEmpty();
		case ControlcomponentPackage.OPERATION_MODE_INFO__PARAMETERS:
			return parameters != null && !parameters.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", shortName: ");
		result.append(shortName);
		result.append(", description: ");
		result.append(description);
		result.append(", executionModes: ");
		result.append(executionModes);
		result.append(", executionCommands: ");
		result.append(executionCommands);
		result.append(')');
		return result.toString();
	}

	public static class Builder {
		private String name;
		private String shortName;
		private String description;
		private List<ExecutionMode> executionModes;
		private List<ExecutionCommand> executionCommands;
		private List<ParameterInfo> parameters;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder shortName(String shortName) {
			this.shortName = shortName;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder executionModes(List<ExecutionMode> executionModes) {
			this.executionModes = executionModes;
			return this;
		}

		public Builder executionCommands(List<ExecutionCommand> executionCommands) {
			this.executionCommands = executionCommands;
			return this;
		}

		public Builder parameters(List<ParameterInfo> parameters) {
			this.parameters = parameters;
			return this;
		}

		public OperationModeInfoImpl build() {
			return new OperationModeInfoImpl(this);
		}

	}

	private OperationModeInfoImpl(Builder builder) {
		this.name = builder.name;
		this.shortName = builder.shortName;
		this.description = builder.description;
		this.getExecutionModes().addAll(builder.executionModes);
		this.getExecutionCommands().addAll(builder.executionCommands);
		this.getParameters().addAll(builder.parameters);
	}
	
} //OperationModeInfoImpl
