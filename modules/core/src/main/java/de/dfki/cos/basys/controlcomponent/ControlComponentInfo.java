/**
 */
package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.common.component.ComponentInfo;
import de.dfki.cos.basys.controlcomponent.util.Strings;

import java.util.Properties;

public class ControlComponentInfo extends ComponentInfo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlComponentInfo() {
		super(new Properties());
	}
	
	public ControlComponentInfo(Properties p) {
		super(p);
	}
	
	public ExecutionState getExecutionState() {
		return ExecutionState.valueOf(getProperty(Strings.getString("ControlComponent.BN.ExecutionState")));
	}

	public ControlComponentInfo setExecutionState(ExecutionState value) {
		setProperty(Strings.getString("ControlComponent.BN.ExecutionState"), value.toString());
		return this;
	}

	public ExecutionMode getExecutionMode() {
		return ExecutionMode.valueOf(getProperty(Strings.getString("ControlComponent.BN.ExecutionMode")));
	}

	public ControlComponentInfo setExecutionMode(ExecutionMode value) {
		setProperty(Strings.getString("ControlComponent.BN.ExecutionMode"), value.toString());
		return this;
	}

	public String getOperationMode() {
		return getProperty(Strings.getString("ControlComponent.BN.OperationMode"));
	}

	public ControlComponentInfo setOperationMode(String value) {
		setProperty(Strings.getString("ControlComponent.BN.OperationMode"), value);
		return this;
	}

	public String getWorkState() {
		return getProperty(Strings.getString("ControlComponent.BN.WorkState"));
	}

	public ControlComponentInfo setWorkState(String value) {
		setProperty(Strings.getString("ControlComponent.BN.WorkState"), value);
		return this;
	}

	public OccupationStatus getOccupationStatus() {
		OccupationState level = OccupationState.valueOf(getProperty(Strings.getString("ControlComponent.BN.OccupationState")));
		String occupierId = getProperty(Strings.getString("ControlComponent.BN.OccupierId"));
		OccupationStatus status = new OccupationStatus.Builder().level(level).occupierId(occupierId).build();
		return status;
	}

	public ControlComponentInfo setOccupationStatus(OccupationStatus occupationStatus) {
		setProperty(Strings.getString("ControlComponent.BN.OccupationState"), occupationStatus.getLevel().toString());
		setProperty(Strings.getString("ControlComponent.BN.OccupierId"), occupationStatus.getOccupierId());
		return this;
	}

	public ErrorStatus getErrorStatus() {
		int code = Integer.parseInt(getProperty(Strings.getString("ControlComponent.BN.ErrorCode")));
		String msg = getProperty(Strings.getString("ControlComponent.BN.ErrorMessage"));
		ErrorStatus status = new ErrorStatus.Builder().errorCode(code).errorMessage(msg).build();
		return status;
	}

	public ControlComponentInfo setErrorStatus(ErrorStatus errorStatus) {
		setProperty(Strings.getString("ControlComponent.BN.ErrorCode"), errorStatus.getErrorCode()+"");
		setProperty(Strings.getString("ControlComponent.BN.ErrorMessage"), errorStatus.getErrorMessage());
		return this;
	}

}
