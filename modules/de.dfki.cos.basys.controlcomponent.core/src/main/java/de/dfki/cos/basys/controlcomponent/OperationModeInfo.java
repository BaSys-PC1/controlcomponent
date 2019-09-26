/**
 */
package de.dfki.cos.basys.controlcomponent;

import java.util.LinkedList;
import java.util.List;

public class OperationModeInfo {

	String name;
	String shortName;
	String description;
	List<ParameterInfo> parameters = new LinkedList<>();
	List<ExecutionMode> executionModes = new LinkedList<>();
	List<ExecutionCommand> executionCommands = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ParameterInfo> getParameters() {
		return parameters;
	}

	public void setParameters(List<ParameterInfo> parameters) {
		this.parameters = parameters;
	}

	public List<ExecutionMode> getExecutionModes() {
		return executionModes;
	}

	public void setExecutionModes(List<ExecutionMode> executionModes) {
		this.executionModes = executionModes;
	}

	public List<ExecutionCommand> getExecutionCommands() {
		return executionCommands;
	}

	public void setExecutionCommands(List<ExecutionCommand> executionCommands) {
		this.executionCommands = executionCommands;
	}

	public static class Builder {
		private String name;
		private String shortName;
		private String description;
		private List<ParameterInfo> parameters;
		private List<ExecutionMode> executionModes;
		private List<ExecutionCommand> executionCommands;

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

		public Builder parameters(List<ParameterInfo> parameters) {
			this.parameters = parameters;
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

		public OperationModeInfo build() {
			return new OperationModeInfo(this);
		}
	}

	private OperationModeInfo(Builder builder) {
		this.name = builder.name;
		this.shortName = builder.shortName;
		this.description = builder.description;
		this.parameters = builder.parameters;
		this.executionModes = builder.executionModes;
		this.executionCommands = builder.executionCommands;
	}
}
