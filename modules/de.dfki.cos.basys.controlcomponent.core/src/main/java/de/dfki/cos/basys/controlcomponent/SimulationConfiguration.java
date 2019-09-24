/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simulation Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnResettingDuration <em>On Resetting Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStartingDuration <em>On Starting Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnExecuteDuration <em>On Execute Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingDuration <em>On Completing Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingDuration <em>On Stopping Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnAbortingDuration <em>On Aborting Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnClearingDuration <em>On Clearing Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnHoldingDuration <em>On Holding Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnholdingDuration <em>On Unholding Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnSuspendingDuration <em>On Suspending Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnsuspendingDuration <em>On Unsuspending Duration</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingVariables <em>On Completing Variables</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorCode <em>On Completing Error Code</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorMessage <em>On Completing Error Message</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingStatusCode <em>On Stopping Status Code</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingErrorMessage <em>On Stopping Error Message</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingVariables <em>On Stopping Variables</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration()
 * @model
 * @generated
 */
public interface SimulationConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>On Resetting Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Resetting Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Resetting Duration</em>' attribute.
	 * @see #setOnResettingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnResettingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnResettingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnResettingDuration <em>On Resetting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Resetting Duration</em>' attribute.
	 * @see #getOnResettingDuration()
	 * @generated
	 */
	void setOnResettingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Starting Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Starting Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Starting Duration</em>' attribute.
	 * @see #setOnStartingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnStartingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnStartingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStartingDuration <em>On Starting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Starting Duration</em>' attribute.
	 * @see #getOnStartingDuration()
	 * @generated
	 */
	void setOnStartingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Execute Duration</b></em>' attribute.
	 * The default value is <code>"3000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Execute Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Execute Duration</em>' attribute.
	 * @see #setOnExecuteDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnExecuteDuration()
	 * @model default="3000"
	 * @generated
	 */
	int getOnExecuteDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnExecuteDuration <em>On Execute Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Execute Duration</em>' attribute.
	 * @see #getOnExecuteDuration()
	 * @generated
	 */
	void setOnExecuteDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Completing Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Completing Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Completing Duration</em>' attribute.
	 * @see #setOnCompletingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnCompletingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnCompletingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingDuration <em>On Completing Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Completing Duration</em>' attribute.
	 * @see #getOnCompletingDuration()
	 * @generated
	 */
	void setOnCompletingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Stopping Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Stopping Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Stopping Duration</em>' attribute.
	 * @see #setOnStoppingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnStoppingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnStoppingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingDuration <em>On Stopping Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Stopping Duration</em>' attribute.
	 * @see #getOnStoppingDuration()
	 * @generated
	 */
	void setOnStoppingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Aborting Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Aborting Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Aborting Duration</em>' attribute.
	 * @see #setOnAbortingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnAbortingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnAbortingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnAbortingDuration <em>On Aborting Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Aborting Duration</em>' attribute.
	 * @see #getOnAbortingDuration()
	 * @generated
	 */
	void setOnAbortingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Clearing Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Clearing Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Clearing Duration</em>' attribute.
	 * @see #setOnClearingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnClearingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnClearingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnClearingDuration <em>On Clearing Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Clearing Duration</em>' attribute.
	 * @see #getOnClearingDuration()
	 * @generated
	 */
	void setOnClearingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Holding Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Holding Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Holding Duration</em>' attribute.
	 * @see #setOnHoldingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnHoldingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnHoldingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnHoldingDuration <em>On Holding Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Holding Duration</em>' attribute.
	 * @see #getOnHoldingDuration()
	 * @generated
	 */
	void setOnHoldingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Unholding Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Unholding Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Unholding Duration</em>' attribute.
	 * @see #setOnUnholdingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnUnholdingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnUnholdingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnholdingDuration <em>On Unholding Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Unholding Duration</em>' attribute.
	 * @see #getOnUnholdingDuration()
	 * @generated
	 */
	void setOnUnholdingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Suspending Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Suspending Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Suspending Duration</em>' attribute.
	 * @see #setOnSuspendingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnSuspendingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnSuspendingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnSuspendingDuration <em>On Suspending Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Suspending Duration</em>' attribute.
	 * @see #getOnSuspendingDuration()
	 * @generated
	 */
	void setOnSuspendingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Unsuspending Duration</b></em>' attribute.
	 * The default value is <code>"1000"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Unsuspending Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Unsuspending Duration</em>' attribute.
	 * @see #setOnUnsuspendingDuration(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnUnsuspendingDuration()
	 * @model default="1000"
	 * @generated
	 */
	int getOnUnsuspendingDuration();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnUnsuspendingDuration <em>On Unsuspending Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Unsuspending Duration</em>' attribute.
	 * @see #getOnUnsuspendingDuration()
	 * @generated
	 */
	void setOnUnsuspendingDuration(int value);

	/**
	 * Returns the value of the '<em><b>On Completing Variables</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Completing Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Completing Variables</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnCompletingVariables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Variable> getOnCompletingVariables();

	/**
	 * Returns the value of the '<em><b>On Completing Error Code</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Completing Error Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Completing Error Code</em>' attribute.
	 * @see #setOnCompletingErrorCode(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnCompletingErrorCode()
	 * @model default="0"
	 * @generated
	 */
	int getOnCompletingErrorCode();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorCode <em>On Completing Error Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Completing Error Code</em>' attribute.
	 * @see #getOnCompletingErrorCode()
	 * @generated
	 */
	void setOnCompletingErrorCode(int value);

	/**
	 * Returns the value of the '<em><b>On Completing Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Completing Error Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Completing Error Message</em>' attribute.
	 * @see #setOnCompletingErrorMessage(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnCompletingErrorMessage()
	 * @model
	 * @generated
	 */
	String getOnCompletingErrorMessage();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnCompletingErrorMessage <em>On Completing Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Completing Error Message</em>' attribute.
	 * @see #getOnCompletingErrorMessage()
	 * @generated
	 */
	void setOnCompletingErrorMessage(String value);

	/**
	 * Returns the value of the '<em><b>On Stopping Status Code</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Stopping Status Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Stopping Status Code</em>' attribute.
	 * @see #setOnStoppingStatusCode(int)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnStoppingStatusCode()
	 * @model default="1"
	 * @generated
	 */
	int getOnStoppingStatusCode();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingStatusCode <em>On Stopping Status Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Stopping Status Code</em>' attribute.
	 * @see #getOnStoppingStatusCode()
	 * @generated
	 */
	void setOnStoppingStatusCode(int value);

	/**
	 * Returns the value of the '<em><b>On Stopping Error Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Stopping Error Message</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Stopping Error Message</em>' attribute.
	 * @see #setOnStoppingErrorMessage(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnStoppingErrorMessage()
	 * @model ordered="false"
	 * @generated
	 */
	String getOnStoppingErrorMessage();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.SimulationConfiguration#getOnStoppingErrorMessage <em>On Stopping Error Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>On Stopping Error Message</em>' attribute.
	 * @see #getOnStoppingErrorMessage()
	 * @generated
	 */
	void setOnStoppingErrorMessage(String value);

	/**
	 * Returns the value of the '<em><b>On Stopping Variables</b></em>' containment reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>On Stopping Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>On Stopping Variables</em>' containment reference list.
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getSimulationConfiguration_OnStoppingVariables()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Variable> getOnStoppingVariables();

} // SimulationConfiguration