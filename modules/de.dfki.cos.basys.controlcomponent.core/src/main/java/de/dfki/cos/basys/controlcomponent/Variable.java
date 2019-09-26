/**
 */
package de.dfki.cos.basys.controlcomponent;

public class Variable {
	String name;
	VariableType type;
	Object value;
	VariableAccess access;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VariableType getType() {
		return type;
	}

	public void setType(VariableType type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public VariableAccess getAccess() {
		return access;
	}

	public void setAccess(VariableAccess access) {
		this.access = access;
	}

	public static class Builder {
		private String name;
		private VariableType type;
		private Object value;
		private VariableAccess access;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(VariableType type) {
			this.type = type;
			return this;
		}

		public Builder value(Object value) {
			this.value = value;
			return this;
		}

		public Builder access(VariableAccess access) {
			this.access = access;
			return this;
		}

		public Variable build() {
			return new Variable(this);
		}
	}

	private Variable(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.value = builder.value;
		this.access = builder.access;
	}
}
