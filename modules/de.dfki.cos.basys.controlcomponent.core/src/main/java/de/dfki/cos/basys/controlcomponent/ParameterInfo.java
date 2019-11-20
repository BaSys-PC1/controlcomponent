/**
 */
package de.dfki.cos.basys.controlcomponent;

public class ParameterInfo {
	String name;
	String type;
	ParameterDirection access;
	Object value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ParameterDirection getAccess() {
		return access;
	}

	public void setAccess(ParameterDirection access) {
		this.access = access;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public static class Builder {
		private String name;
		private String type;
		private ParameterDirection access;
		private Object value;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder access(ParameterDirection access) {
			this.access = access;
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
		this.type = builder.type;
		this.access = builder.access;
		this.value = builder.value;
	}
}
