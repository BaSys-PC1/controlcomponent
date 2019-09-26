/**
 */
package de.dfki.cos.basys.controlcomponent;

public class ErrorStatus {
	int errorCode;
	String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static class Builder {
		private int errorCode;
		private String errorMessage;

		public Builder errorCode(int errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public Builder errorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public ErrorStatus build() {
			return new ErrorStatus(this);
		}
	}

	private ErrorStatus(Builder builder) {
		this.errorCode = builder.errorCode;
		this.errorMessage = builder.errorMessage;
	}
}
