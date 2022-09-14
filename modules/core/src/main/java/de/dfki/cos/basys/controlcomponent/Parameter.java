/**
 */
package de.dfki.cos.basys.controlcomponent;

public class Parameter {
	String name;
	ParameterType type;
	ParameterDirection direction;
	Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParameterType getType() {
		return type;
	}

	public void setType(ParameterType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ParameterDirection getDirection() {
		return direction;
	}

	public void setDirection(ParameterDirection access) {
		this.direction = access;
	}

	public static class Builder {
		private String name;
		private ParameterType type;
		private Object value;
		private ParameterDirection access;

		public Builder name(String name) {
			this.name = name;
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

		public Builder access(ParameterDirection access) {
			this.access = access;
			return this;
		}

		public Parameter build() {
			return new Parameter(this);
		}
	}

	private Parameter(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.value = builder.value;
		this.direction = builder.access;
	}
}
