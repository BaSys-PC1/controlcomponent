package de.dfki.cos.basys.controlcomponent.core;

import java.util.List;

import de.dfki.cos.basys.controlcomponent.ExecutionCommand;
import de.dfki.cos.basys.controlcomponent.ExecutionMode;
import de.dfki.cos.basys.controlcomponent.ParameterInfo;

public class OperationModeInfo {

	String name;
	String shortName;
	String description;
	List<ExecutionMode> modes;
	List<ExecutionCommand> commands;
	List<ParameterInfo> parameters;

	public OperationModeInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getShortName() {
		return shortName;
	}

	public String getDescription() {
		return description;
	}

	public List<ParameterInfo> getParameters() {
		return parameters;
	}

	public List<ExecutionMode> getModes() {
		return modes;
	}

	public List<ExecutionCommand> getCommands() {
		return commands;
	}

	public static class Builder {
		private String name;
		private String shortName;
		private String description;
		private List<ExecutionMode> modes;
		private List<ExecutionCommand> commands;
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

		public Builder modes(List<ExecutionMode> modes) {
			this.modes = modes;
			return this;
		}

		public Builder commands(List<ExecutionCommand> commands) {
			this.commands = commands;
			return this;
		}

		public Builder parameters(List<ParameterInfo> parameters) {
			this.parameters = parameters;
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
		this.modes = builder.modes;
		this.commands = builder.commands;
		this.parameters = builder.parameters;
	}
}
