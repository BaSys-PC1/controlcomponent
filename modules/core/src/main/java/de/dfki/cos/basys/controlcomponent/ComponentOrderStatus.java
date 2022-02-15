/**
 */
package de.dfki.cos.basys.controlcomponent;

public class ComponentOrderStatus {
	OrderStatus status;
	String message;
	int statusCode;

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public static class Builder {
		private OrderStatus status;
		private String message;
		private int statusCode;

		public Builder status(OrderStatus status) {
			this.status = status;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}
		
		public Builder statusCode(int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public ComponentOrderStatus build() {
			return new ComponentOrderStatus(this);
		}
	}

	private ComponentOrderStatus(Builder builder) {
		this.status = builder.status;
		this.message = builder.message;
		this.statusCode = builder.statusCode;
	}
}
