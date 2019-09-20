/**
 */
package de.dfki.cos.basys.controlcomponent.impl;

import de.dfki.cos.basys.controlcomponent.ControlcomponentPackage;
import de.dfki.cos.basys.controlcomponent.SimulationConfiguration;
import de.dfki.cos.basys.controlcomponent.Variable;

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
 * An implementation of the model object '<em><b>Simulation Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnResettingDuration <em>On Resetting Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnStartingDuration <em>On Starting Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnExecuteDuration <em>On Execute Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnCompletingDuration <em>On Completing Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnStoppingDuration <em>On Stopping Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnAbortingDuration <em>On Aborting Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnClearingDuration <em>On Clearing Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnHoldingDuration <em>On Holding Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnUnholdingDuration <em>On Unholding Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnSuspendingDuration <em>On Suspending Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnUnsuspendingDuration <em>On Unsuspending Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnCompletingVariables <em>On Completing Variables</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnCompletingErrorCode <em>On Completing Error Code</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnCompletingErrorMessage <em>On Completing Error Message</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnStoppingStatusCode <em>On Stopping Status Code</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnStoppingErrorMessage <em>On Stopping Error Message</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.impl.SimulationConfigurationImpl#getOnStoppingVariables <em>On Stopping Variables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SimulationConfigurationImpl extends MinimalEObjectImpl.Container implements SimulationConfiguration {
	/**
	 * The default value of the '{@link #getOnResettingDuration() <em>On Resetting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnResettingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_RESETTING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnResettingDuration() <em>On Resetting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnResettingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onResettingDuration = ON_RESETTING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnStartingDuration() <em>On Starting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStartingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_STARTING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnStartingDuration() <em>On Starting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStartingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onStartingDuration = ON_STARTING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnExecuteDuration() <em>On Execute Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnExecuteDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_EXECUTE_DURATION_EDEFAULT = 3000;

	/**
	 * The cached value of the '{@link #getOnExecuteDuration() <em>On Execute Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnExecuteDuration()
	 * @generated
	 * @ordered
	 */
	protected int onExecuteDuration = ON_EXECUTE_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnCompletingDuration() <em>On Completing Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_COMPLETING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnCompletingDuration() <em>On Completing Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onCompletingDuration = ON_COMPLETING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnStoppingDuration() <em>On Stopping Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_STOPPING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnStoppingDuration() <em>On Stopping Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onStoppingDuration = ON_STOPPING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnAbortingDuration() <em>On Aborting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnAbortingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_ABORTING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnAbortingDuration() <em>On Aborting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnAbortingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onAbortingDuration = ON_ABORTING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnClearingDuration() <em>On Clearing Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnClearingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_CLEARING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnClearingDuration() <em>On Clearing Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnClearingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onClearingDuration = ON_CLEARING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnHoldingDuration() <em>On Holding Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnHoldingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_HOLDING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnHoldingDuration() <em>On Holding Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnHoldingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onHoldingDuration = ON_HOLDING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnUnholdingDuration() <em>On Unholding Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnUnholdingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_UNHOLDING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnUnholdingDuration() <em>On Unholding Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnUnholdingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onUnholdingDuration = ON_UNHOLDING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnSuspendingDuration() <em>On Suspending Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnSuspendingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_SUSPENDING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnSuspendingDuration() <em>On Suspending Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnSuspendingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onSuspendingDuration = ON_SUSPENDING_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnUnsuspendingDuration() <em>On Unsuspending Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnUnsuspendingDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_UNSUSPENDING_DURATION_EDEFAULT = 1000;

	/**
	 * The cached value of the '{@link #getOnUnsuspendingDuration() <em>On Unsuspending Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnUnsuspendingDuration()
	 * @generated
	 * @ordered
	 */
	protected int onUnsuspendingDuration = ON_UNSUSPENDING_DURATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOnCompletingVariables() <em>On Completing Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> onCompletingVariables;

	/**
	 * The default value of the '{@link #getOnCompletingErrorCode() <em>On Completing Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingErrorCode()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_COMPLETING_ERROR_CODE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOnCompletingErrorCode() <em>On Completing Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingErrorCode()
	 * @generated
	 * @ordered
	 */
	protected int onCompletingErrorCode = ON_COMPLETING_ERROR_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnCompletingErrorMessage() <em>On Completing Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingErrorMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String ON_COMPLETING_ERROR_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnCompletingErrorMessage() <em>On Completing Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnCompletingErrorMessage()
	 * @generated
	 * @ordered
	 */
	protected String onCompletingErrorMessage = ON_COMPLETING_ERROR_MESSAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnStoppingStatusCode() <em>On Stopping Status Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingStatusCode()
	 * @generated
	 * @ordered
	 */
	protected static final int ON_STOPPING_STATUS_CODE_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getOnStoppingStatusCode() <em>On Stopping Status Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingStatusCode()
	 * @generated
	 * @ordered
	 */
	protected int onStoppingStatusCode = ON_STOPPING_STATUS_CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnStoppingErrorMessage() <em>On Stopping Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingErrorMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String ON_STOPPING_ERROR_MESSAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnStoppingErrorMessage() <em>On Stopping Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingErrorMessage()
	 * @generated
	 * @ordered
	 */
	protected String onStoppingErrorMessage = ON_STOPPING_ERROR_MESSAGE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOnStoppingVariables() <em>On Stopping Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnStoppingVariables()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> onStoppingVariables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SimulationConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ControlcomponentPackage.Literals.SIMULATION_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnResettingDuration() {
		return onResettingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnResettingDuration(int newOnResettingDuration) {
		int oldOnResettingDuration = onResettingDuration;
		onResettingDuration = newOnResettingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_RESETTING_DURATION, oldOnResettingDuration, onResettingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnStartingDuration() {
		return onStartingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnStartingDuration(int newOnStartingDuration) {
		int oldOnStartingDuration = onStartingDuration;
		onStartingDuration = newOnStartingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STARTING_DURATION, oldOnStartingDuration, onStartingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnExecuteDuration() {
		return onExecuteDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnExecuteDuration(int newOnExecuteDuration) {
		int oldOnExecuteDuration = onExecuteDuration;
		onExecuteDuration = newOnExecuteDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION, oldOnExecuteDuration, onExecuteDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnCompletingDuration() {
		return onCompletingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnCompletingDuration(int newOnCompletingDuration) {
		int oldOnCompletingDuration = onCompletingDuration;
		onCompletingDuration = newOnCompletingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION, oldOnCompletingDuration, onCompletingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnStoppingDuration() {
		return onStoppingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnStoppingDuration(int newOnStoppingDuration) {
		int oldOnStoppingDuration = onStoppingDuration;
		onStoppingDuration = newOnStoppingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_DURATION, oldOnStoppingDuration, onStoppingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnAbortingDuration() {
		return onAbortingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnAbortingDuration(int newOnAbortingDuration) {
		int oldOnAbortingDuration = onAbortingDuration;
		onAbortingDuration = newOnAbortingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_ABORTING_DURATION, oldOnAbortingDuration, onAbortingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnClearingDuration() {
		return onClearingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnClearingDuration(int newOnClearingDuration) {
		int oldOnClearingDuration = onClearingDuration;
		onClearingDuration = newOnClearingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_CLEARING_DURATION, oldOnClearingDuration, onClearingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnHoldingDuration() {
		return onHoldingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnHoldingDuration(int newOnHoldingDuration) {
		int oldOnHoldingDuration = onHoldingDuration;
		onHoldingDuration = newOnHoldingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_HOLDING_DURATION, oldOnHoldingDuration, onHoldingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnUnholdingDuration() {
		return onUnholdingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnUnholdingDuration(int newOnUnholdingDuration) {
		int oldOnUnholdingDuration = onUnholdingDuration;
		onUnholdingDuration = newOnUnholdingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION, oldOnUnholdingDuration, onUnholdingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnSuspendingDuration() {
		return onSuspendingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnSuspendingDuration(int newOnSuspendingDuration) {
		int oldOnSuspendingDuration = onSuspendingDuration;
		onSuspendingDuration = newOnSuspendingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION, oldOnSuspendingDuration, onSuspendingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnUnsuspendingDuration() {
		return onUnsuspendingDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnUnsuspendingDuration(int newOnUnsuspendingDuration) {
		int oldOnUnsuspendingDuration = onUnsuspendingDuration;
		onUnsuspendingDuration = newOnUnsuspendingDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION, oldOnUnsuspendingDuration, onUnsuspendingDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getOnCompletingVariables() {
		if (onCompletingVariables == null) {
			onCompletingVariables = new EObjectContainmentEList<Variable>(Variable.class, this, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES);
		}
		return onCompletingVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnCompletingErrorCode() {
		return onCompletingErrorCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnCompletingErrorCode(int newOnCompletingErrorCode) {
		int oldOnCompletingErrorCode = onCompletingErrorCode;
		onCompletingErrorCode = newOnCompletingErrorCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE, oldOnCompletingErrorCode, onCompletingErrorCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnCompletingErrorMessage() {
		return onCompletingErrorMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnCompletingErrorMessage(String newOnCompletingErrorMessage) {
		String oldOnCompletingErrorMessage = onCompletingErrorMessage;
		onCompletingErrorMessage = newOnCompletingErrorMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE, oldOnCompletingErrorMessage, onCompletingErrorMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOnStoppingStatusCode() {
		return onStoppingStatusCode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnStoppingStatusCode(int newOnStoppingStatusCode) {
		int oldOnStoppingStatusCode = onStoppingStatusCode;
		onStoppingStatusCode = newOnStoppingStatusCode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE, oldOnStoppingStatusCode, onStoppingStatusCode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnStoppingErrorMessage() {
		return onStoppingErrorMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnStoppingErrorMessage(String newOnStoppingErrorMessage) {
		String oldOnStoppingErrorMessage = onStoppingErrorMessage;
		onStoppingErrorMessage = newOnStoppingErrorMessage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE, oldOnStoppingErrorMessage, onStoppingErrorMessage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getOnStoppingVariables() {
		if (onStoppingVariables == null) {
			onStoppingVariables = new EObjectContainmentEList<Variable>(Variable.class, this, ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES);
		}
		return onStoppingVariables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES:
				return ((InternalEList<?>)getOnCompletingVariables()).basicRemove(otherEnd, msgs);
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES:
				return ((InternalEList<?>)getOnStoppingVariables()).basicRemove(otherEnd, msgs);
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
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_RESETTING_DURATION:
				return getOnResettingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STARTING_DURATION:
				return getOnStartingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION:
				return getOnExecuteDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION:
				return getOnCompletingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_DURATION:
				return getOnStoppingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_ABORTING_DURATION:
				return getOnAbortingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_CLEARING_DURATION:
				return getOnClearingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_HOLDING_DURATION:
				return getOnHoldingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION:
				return getOnUnholdingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION:
				return getOnSuspendingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION:
				return getOnUnsuspendingDuration();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES:
				return getOnCompletingVariables();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE:
				return getOnCompletingErrorCode();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE:
				return getOnCompletingErrorMessage();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE:
				return getOnStoppingStatusCode();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE:
				return getOnStoppingErrorMessage();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES:
				return getOnStoppingVariables();
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
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_RESETTING_DURATION:
				setOnResettingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STARTING_DURATION:
				setOnStartingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION:
				setOnExecuteDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION:
				setOnCompletingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_DURATION:
				setOnStoppingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_ABORTING_DURATION:
				setOnAbortingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_CLEARING_DURATION:
				setOnClearingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_HOLDING_DURATION:
				setOnHoldingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION:
				setOnUnholdingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION:
				setOnSuspendingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION:
				setOnUnsuspendingDuration((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES:
				getOnCompletingVariables().clear();
				getOnCompletingVariables().addAll((Collection<? extends Variable>)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE:
				setOnCompletingErrorCode((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE:
				setOnCompletingErrorMessage((String)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE:
				setOnStoppingStatusCode((Integer)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE:
				setOnStoppingErrorMessage((String)newValue);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES:
				getOnStoppingVariables().clear();
				getOnStoppingVariables().addAll((Collection<? extends Variable>)newValue);
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
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_RESETTING_DURATION:
				setOnResettingDuration(ON_RESETTING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STARTING_DURATION:
				setOnStartingDuration(ON_STARTING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION:
				setOnExecuteDuration(ON_EXECUTE_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION:
				setOnCompletingDuration(ON_COMPLETING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_DURATION:
				setOnStoppingDuration(ON_STOPPING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_ABORTING_DURATION:
				setOnAbortingDuration(ON_ABORTING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_CLEARING_DURATION:
				setOnClearingDuration(ON_CLEARING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_HOLDING_DURATION:
				setOnHoldingDuration(ON_HOLDING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION:
				setOnUnholdingDuration(ON_UNHOLDING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION:
				setOnSuspendingDuration(ON_SUSPENDING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION:
				setOnUnsuspendingDuration(ON_UNSUSPENDING_DURATION_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES:
				getOnCompletingVariables().clear();
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE:
				setOnCompletingErrorCode(ON_COMPLETING_ERROR_CODE_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE:
				setOnCompletingErrorMessage(ON_COMPLETING_ERROR_MESSAGE_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE:
				setOnStoppingStatusCode(ON_STOPPING_STATUS_CODE_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE:
				setOnStoppingErrorMessage(ON_STOPPING_ERROR_MESSAGE_EDEFAULT);
				return;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES:
				getOnStoppingVariables().clear();
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
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_RESETTING_DURATION:
				return onResettingDuration != ON_RESETTING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STARTING_DURATION:
				return onStartingDuration != ON_STARTING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_EXECUTE_DURATION:
				return onExecuteDuration != ON_EXECUTE_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_DURATION:
				return onCompletingDuration != ON_COMPLETING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_DURATION:
				return onStoppingDuration != ON_STOPPING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_ABORTING_DURATION:
				return onAbortingDuration != ON_ABORTING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_CLEARING_DURATION:
				return onClearingDuration != ON_CLEARING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_HOLDING_DURATION:
				return onHoldingDuration != ON_HOLDING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNHOLDING_DURATION:
				return onUnholdingDuration != ON_UNHOLDING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_SUSPENDING_DURATION:
				return onSuspendingDuration != ON_SUSPENDING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_UNSUSPENDING_DURATION:
				return onUnsuspendingDuration != ON_UNSUSPENDING_DURATION_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_VARIABLES:
				return onCompletingVariables != null && !onCompletingVariables.isEmpty();
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_CODE:
				return onCompletingErrorCode != ON_COMPLETING_ERROR_CODE_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_COMPLETING_ERROR_MESSAGE:
				return ON_COMPLETING_ERROR_MESSAGE_EDEFAULT == null ? onCompletingErrorMessage != null : !ON_COMPLETING_ERROR_MESSAGE_EDEFAULT.equals(onCompletingErrorMessage);
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_STATUS_CODE:
				return onStoppingStatusCode != ON_STOPPING_STATUS_CODE_EDEFAULT;
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_ERROR_MESSAGE:
				return ON_STOPPING_ERROR_MESSAGE_EDEFAULT == null ? onStoppingErrorMessage != null : !ON_STOPPING_ERROR_MESSAGE_EDEFAULT.equals(onStoppingErrorMessage);
			case ControlcomponentPackage.SIMULATION_CONFIGURATION__ON_STOPPING_VARIABLES:
				return onStoppingVariables != null && !onStoppingVariables.isEmpty();
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
		result.append(" (onResettingDuration: ");
		result.append(onResettingDuration);
		result.append(", onStartingDuration: ");
		result.append(onStartingDuration);
		result.append(", onExecuteDuration: ");
		result.append(onExecuteDuration);
		result.append(", onCompletingDuration: ");
		result.append(onCompletingDuration);
		result.append(", onStoppingDuration: ");
		result.append(onStoppingDuration);
		result.append(", onAbortingDuration: ");
		result.append(onAbortingDuration);
		result.append(", onClearingDuration: ");
		result.append(onClearingDuration);
		result.append(", onHoldingDuration: ");
		result.append(onHoldingDuration);
		result.append(", onUnholdingDuration: ");
		result.append(onUnholdingDuration);
		result.append(", onSuspendingDuration: ");
		result.append(onSuspendingDuration);
		result.append(", onUnsuspendingDuration: ");
		result.append(onUnsuspendingDuration);
		result.append(", onCompletingErrorCode: ");
		result.append(onCompletingErrorCode);
		result.append(", onCompletingErrorMessage: ");
		result.append(onCompletingErrorMessage);
		result.append(", onStoppingStatusCode: ");
		result.append(onStoppingStatusCode);
		result.append(", onStoppingErrorMessage: ");
		result.append(onStoppingErrorMessage);
		result.append(')');
		return result.toString();
	}

} //SimulationConfigurationImpl
