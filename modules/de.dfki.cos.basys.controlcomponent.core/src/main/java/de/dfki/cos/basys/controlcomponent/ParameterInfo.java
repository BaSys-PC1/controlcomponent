/**
 */
package de.dfki.cos.basys.controlcomponent;

public class ParameterInfo {
	String name;
	String type;
	Object value;
	ParameterDirection direction;

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

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ParameterDirection getDirection() {
		return direction;
	}

	public void setDirection(ParameterDirection direction) {
		this.direction = direction;
	}

	public static class Builder {
		private String name;
		private String type;
		private Object value;
		private ParameterDirection direction;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder value(Object value) {
			this.value = value;
			return this;
		}

		public Builder direction(ParameterDirection direction) {
			this.direction = direction;
			return this;
		}

		public ParameterInfo build() {
			return new ParameterInfo(this);
		}
	}

	private ParameterInfo(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.value = builder.value;
		this.direction = builder.direction;
	}
}
