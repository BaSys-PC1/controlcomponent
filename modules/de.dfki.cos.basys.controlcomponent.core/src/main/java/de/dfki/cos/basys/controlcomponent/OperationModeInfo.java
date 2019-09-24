/**
 */
package de.dfki.cos.basys.controlcomponent;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Mode Info</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getName <em>Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getShortName <em>Short Name</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getDescription <em>Description</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getExecutionModes <em>Execution Modes</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getExecutionCommands <em>Execution Commands</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo()
 * @model
 * @generated
 */
public interface OperationModeInfo extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Name</em>' attribute.
	 * @see #setShortName(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo_ShortName()
	 * @model
	 * @generated
	 */
	String getShortName();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getShortName <em>Short Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Name</em>' attribute.
	 * @see #getShortName()
	 * @generated
	 */
	void setShortName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.dfki.cos.basys.controlcomponent.OperationModeInfo#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Execution Modes</b></em>' attribute list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.ExecutionMode}.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.ExecutionMode}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Modes</em>' attribute list.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionMode
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo_ExecutionModes()
	 * @model
	 * @generated
	 */
	EList<ExecutionMode> getExecutionModes();

	/**
	 * Returns the value of the '<em><b>Execution Commands</b></em>' attribute list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.ExecutionCommand}.
	 * The literals are from the enumeration {@link de.dfki.cos.basys.controlcomponent.ExecutionCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Commands</em>' attribute list.
	 * @see de.dfki.cos.basys.controlcomponent.ExecutionCommand
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo_ExecutionCommands()
	 * @model
	 * @generated
	 */
	EList<ExecutionCommand> getExecutionCommands();

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' reference list.
	 * The list contents are of type {@link de.dfki.cos.basys.controlcomponent.ParameterInfo}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' reference list.
	 * @see de.dfki.cos.basys.controlcomponent.ControlcomponentPackage#getOperationModeInfo_Parameters()
	 * @model
	 * @generated
	 */
	EList<ParameterInfo> getParameters();

} // OperationModeInfo
