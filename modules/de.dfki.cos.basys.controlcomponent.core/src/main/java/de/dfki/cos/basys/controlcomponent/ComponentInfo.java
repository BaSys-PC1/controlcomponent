/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getId <em>Id</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionState <em>Execution State</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionMode <em>Execution Mode</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getOperationMode <em>Operation Mode</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getWorkState <em>Work State</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getOccupationStatus <em>Occupation Status</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getErrorStatus <em>Error Status</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#isActivated <em>Activated</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#isConnectedToExternal <em>Connected To External</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo()
 * @model
 * @generated
 */
public interface ComponentInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Execution State</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.ExecutionState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution State</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionState
	 * @see #setExecutionState(ExecutionState)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_ExecutionState()
	 * @model
	 * @generated
	 */
	ExecutionState getExecutionState();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionState <em>Execution State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution State</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionState
	 * @see #getExecutionState()
	 * @generated
	 */
	void setExecutionState(ExecutionState value);

	/**
	 * Returns the value of the '<em><b>Execution Mode</b></em>' attribute.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.ExecutionMode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Mode</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionMode
	 * @see #setExecutionMode(ExecutionMode)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_ExecutionMode()
	 * @model
	 * @generated
	 */
	ExecutionMode getExecutionMode();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getExecutionMode <em>Execution Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Mode</em>' attribute.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionMode
	 * @see #getExecutionMode()
	 * @generated
	 */
	void setExecutionMode(ExecutionMode value);

	/**
	 * Returns the value of the '<em><b>Operation Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Mode</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Mode</em>' attribute.
	 * @see #setOperationMode(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_OperationMode()
	 * @model
	 * @generated
	 */
	String getOperationMode();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getOperationMode <em>Operation Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Mode</em>' attribute.
	 * @see #getOperationMode()
	 * @generated
	 */
	void setOperationMode(String value);

	/**
	 * Returns the value of the '<em><b>Work State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work State</em>' attribute.
	 * @see #setWorkState(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_WorkState()
	 * @model
	 * @generated
	 */
	String getWorkState();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getWorkState <em>Work State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work State</em>' attribute.
	 * @see #getWorkState()
	 * @generated
	 */
	void setWorkState(String value);

	/**
	 * Returns the value of the '<em><b>Occupation Status</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occupation Status</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occupation Status</em>' reference.
	 * @see #setOccupationStatus(OccupationStatus)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_OccupationStatus()
	 * @model
	 * @generated
	 */
	OccupationStatus getOccupationStatus();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getOccupationStatus <em>Occupation Status</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occupation Status</em>' reference.
	 * @see #getOccupationStatus()
	 * @generated
	 */
	void setOccupationStatus(OccupationStatus value);

	/**
	 * Returns the value of the '<em><b>Error Status</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Status</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Status</em>' containment reference.
	 * @see #setErrorStatus(ErrorStatus)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_ErrorStatus()
	 * @model containment="true"
	 * @generated
	 */
	ErrorStatus getErrorStatus();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#getErrorStatus <em>Error Status</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Status</em>' containment reference.
	 * @see #getErrorStatus()
	 * @generated
	 */
	void setErrorStatus(ErrorStatus value);

	/**
	 * Returns the value of the '<em><b>Activated</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activated</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activated</em>' attribute.
	 * @see #setActivated(boolean)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_Activated()
	 * @model
	 * @generated
	 */
	boolean isActivated();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#isActivated <em>Activated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activated</em>' attribute.
	 * @see #isActivated()
	 * @generated
	 */
	void setActivated(boolean value);

	/**
	 * Returns the value of the '<em><b>Connected To External</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connected To External</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected To External</em>' attribute.
	 * @see #setConnectedToExternal(boolean)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getComponentInfo_ConnectedToExternal()
	 * @model
	 * @generated
	 */
	boolean isConnectedToExternal();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.ComponentInfo#isConnectedToExternal <em>Connected To External</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connected To External</em>' attribute.
	 * @see #isConnectedToExternal()
	 * @generated
	 */
	void setConnectedToExternal(boolean value);

} // ComponentInfo
