/**
 */
package de.dfki.cos.basys.controlcomponent;

import java.util.Properties;

import de.dfki.cos.basys.common.component.ComponentInfo;

public class ControlComponentInfo extends ComponentInfo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ControlComponentInfo(Properties p) {
		super(p);
	}
	
	public ExecutionState getExecutionState() {
		return ExecutionState.valueOf(getProperty(StringConstants.executionState));
	}

	public ControlComponentInfo setExecutionState(ExecutionState value) {
		setProperty(StringConstants.executionState, value.toString());
		return this;
	}

	public ExecutionMode getExecutionMode() {
		return ExecutionMode.valueOf(getProperty(StringConstants.executionMode));
	}

	public ControlComponentInfo setExecutionMode(ExecutionMode value) {
		setProperty(StringConstants.executionMode, value.toString());
		return this;
	}

	public String getOperationMode() {
		return getProperty(StringConstants.operationMode);
	}

	public ControlComponentInfo setOperationMode(String value) {
		setProperty(StringConstants.operationMode, value);
		return this;
	}

	public String getWorkState() {
		return getProperty(StringConstants.workState);
	}

	public ControlComponentInfo setWorkState(String value) {
		setProperty(StringConstants.workState, value);
		return this;
	}

	public OccupationStatus getOccupationStatus() {
		OccupationState level = OccupationState.valueOf(getProperty(StringConstants.occupationLevel));
		String occupierId = getProperty(StringConstants.occupierId);
		OccupationStatus status = new OccupationStatus.Builder().level(level).occupierId(occupierId).build();
		return status;
	}

	public ControlComponentInfo setOccupationStatus(OccupationStatus occupationStatus) {
		setProperty(StringConstants.occupationLevel, occupationStatus.getLevel().toString());
		setProperty(StringConstants.occupierId, occupationStatus.getOccupierId());
		return this;
	}

	public ErrorStatus getErrorStatus() {
		int code = Integer.parseInt(getProperty(StringConstants.errorCode));
		String msg = getProperty(StringConstants.errorMessage);
		ErrorStatus status = new ErrorStatus.Builder().errorCode(code).errorMessage(msg).build();
		return status;
	}

	public ControlComponentInfo setErrorStatus(ErrorStatus errorStatus) {
		setProperty(StringConstants.errorCode, errorStatus.getErrorCode()+"");
		setProperty(StringConstants.errorMessage, errorStatus.getErrorMessage());
		return this;
	}

}
