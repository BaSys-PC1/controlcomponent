/**
 */
package de.dfki.cos.basys.controlcomponent.profiles.impl;

import de.dfki.cos.basys.controlcomponent.profiles.InputParameter;
import de.dfki.cos.basys.controlcomponent.profiles.Method;
import de.dfki.cos.basys.controlcomponent.profiles.OutputParameter;
import de.dfki.cos.basys.controlcomponent.profiles.ProfilesPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.impl.MethodImpl#getInputParameters <em>Input Parameters</em>}</li>
 *   <li>{@link de.dfki.cos.basys.controlcomponent.profiles.impl.MethodImpl#getOutputParameters <em>Output Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MethodImpl extends NodeImpl implements Method {
	/**
	 * The cached value of the '{@link #getInputParameters() <em>Input Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<InputParameter> inputParameters;

	/**
	 * The cached value of the '{@link #getOutputParameters() <em>Output Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<OutputParameter> outputParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProfilesPackage.Literals.METHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<InputParameter> getInputParameters() {
		if (inputParameters == null) {
			inputParameters = new EObjectContainmentEList<InputParameter>(InputParameter.class, this, ProfilesPackage.METHOD__INPUT_PARAMETERS);
		}
		return inputParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<OutputParameter> getOutputParameters() {
		if (outputParameters == null) {
			outputParameters = new EObjectContainmentEList<OutputParameter>(OutputParameter.class, this, ProfilesPackage.METHOD__OUTPUT_PARAMETERS);
		}
		return outputParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProfilesPackage.METHOD__INPUT_PARAMETERS:
				return ((InternalEList<?>)getInputParameters()).basicRemove(otherEnd, msgs);
			case ProfilesPackage.METHOD__OUTPUT_PARAMETERS:
				return ((InternalEList<?>)getOutputParameters()).basicRemove(otherEnd, msgs);
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
			case ProfilesPackage.METHOD__INPUT_PARAMETERS:
				return getInputParameters();
			case ProfilesPackage.METHOD__OUTPUT_PARAMETERS:
				return getOutputParameters();
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
			case ProfilesPackage.METHOD__INPUT_PARAMETERS:
				getInputParameters().clear();
				getInputParameters().addAll((Collection<? extends InputParameter>)newValue);
				return;
			case ProfilesPackage.METHOD__OUTPUT_PARAMETERS:
				getOutputParameters().clear();
				getOutputParameters().addAll((Collection<? extends OutputParameter>)newValue);
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
			case ProfilesPackage.METHOD__INPUT_PARAMETERS:
				getInputParameters().clear();
				return;
			case ProfilesPackage.METHOD__OUTPUT_PARAMETERS:
				getOutputParameters().clear();
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
			case ProfilesPackage.METHOD__INPUT_PARAMETERS:
				return inputParameters != null && !inputParameters.isEmpty();
			case ProfilesPackage.METHOD__OUTPUT_PARAMETERS:
				return outputParameters != null && !outputParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MethodImpl
