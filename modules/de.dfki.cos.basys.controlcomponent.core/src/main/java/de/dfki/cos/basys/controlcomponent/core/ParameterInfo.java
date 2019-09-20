package de.dfki.cos.basys.controlcomponent.core;

public class ParameterInfo {

	public ParameterInfo() {
		// TODO Auto-generated constructor stub
	}

	String name;
	ParameterDirection direction;
	ParameterType type;
	Object value;

	public String getName() {
		return name;
	}

	public ParameterDirection getDirection() {
		return direction;
	}

	public ParameterType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public static class Builder {
		private String name;
		private ParameterDirection direction;
		private ParameterType type;
		private Object value;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder direction(ParameterDirection direction) {
			this.direction = direction;
			return this;
		}

		public Builder type(ParameterType type) {
			this.type = type;
			return this;
		}

		public Builder value(Object value) {
			this.value = value;
			return this;
		}

		public ParameterInfo build() {
			return new ParameterInfo(this);
		}
	}

	private ParameterInfo(Builder builder) {
		this.name = builder.name;
		this.direction = builder.direction;
		this.type = builder.type;
		this.value = builder.value;
	}
}
