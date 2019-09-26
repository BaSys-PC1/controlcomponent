/**
 */
package de.dfki.cos.basys.controlcomponent;

import de.dfki.cos.basys.common.component.ComponentInfo;

public class ControlComponentInfo extends ComponentInfo {

	protected ExecutionState executionState;
	protected ExecutionMode executionMode;
	protected String operationMode;
	protected String workState;
	protected OccupationStatus occupationStatus;
	protected ErrorStatus errorStatus;

	public ExecutionState getExecutionState() {
		return executionState;
	}

	public void setExecutionState(ExecutionState executionState) {
		this.executionState = executionState;
	}

	public ExecutionMode getExecutionMode() {
		return executionMode;
	}

	public void setExecutionMode(ExecutionMode executionMode) {
		this.executionMode = executionMode;
	}

	public String getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(String operationMode) {
		this.operationMode = operationMode;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public OccupationStatus getOccupationStatus() {
		return occupationStatus;
	}

	public void setOccupationStatus(OccupationStatus occupationStatus) {
		this.occupationStatus = occupationStatus;
	}

	public ErrorStatus getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(ErrorStatus errorStatus) {
		this.errorStatus = errorStatus;
	}

	public static class Builder extends ComponentInfo.Builder {
		private ExecutionState executionState;
		private ExecutionMode executionMode;
		private String operationMode;
		private String workState;
		private OccupationStatus occupationStatus;
		private ErrorStatus errorStatus;

		@Override
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		@Override
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public Builder activated(boolean activated) {
			this.activated = activated;
			return this;
		}

		@Override
		public Builder connectedToExternal(boolean connectedToExternal) {
			this.connectedToExternal = connectedToExternal;
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

		public ControlComponentInfo build() {
			return new ControlComponentInfo(this);
		}
	}

	private ControlComponentInfo(Builder builder) {		
		super(builder);
		this.executionState = builder.executionState;
		this.executionMode = builder.executionMode;
		this.operationMode = builder.operationMode;
		this.workState = builder.workState;
		this.occupationStatus = builder.occupationStatus;
		this.errorStatus = builder.errorStatus;
	}
}
