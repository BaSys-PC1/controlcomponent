/**
 */
package de.dfki.cos.basys.controlcomponent;

import java.util.LinkedList;
import java.util.List;

public class SimulationConfiguration {
	private int onAbortingDuration = 1000;
	private int onClearingDuration = 1000;
	private int onStoppingDuration = 1000;
	private int onExecuteDuration = 1000;
	private int onResettingDuration = 1000;
	private int onStartingDuration = 1000;
	private int onCompletingDuration = 1000;
	private int onSuspendingDuration = 1000;
	private int onUnsuspendingDuration = 1000;
	private int onHoldingDuration = 1000;
	private int onUnholdingDuration = 1000;

	ErrorStatus onCompletingErrorStatus = null;
	List<Parameter> onCompletingVariables = null;

	ErrorStatus onStoppingErrorStatus = null;
	List<Parameter> onStoppingVariables = null;

	public int getOnAbortingDuration() {
		return onAbortingDuration;
	}

	public void setOnAbortingDuration(int onAbortingDuration) {
		this.onAbortingDuration = onAbortingDuration;
	}

	public int getOnClearingDuration() {
		return onClearingDuration;
	}

	public void setOnClearingDuration(int onClearingDuration) {
		this.onClearingDuration = onClearingDuration;
	}

	public int getOnStoppingDuration() {
		return onStoppingDuration;
	}

	public void setOnStoppingDuration(int onStoppingDuration) {
		this.onStoppingDuration = onStoppingDuration;
	}

	public int getOnExecuteDuration() {
		return onExecuteDuration;
	}

	public void setOnExecuteDuration(int onExecuteDuration) {
		this.onExecuteDuration = onExecuteDuration;
	}

	public int getOnResettingDuration() {
		return onResettingDuration;
	}

	public void setOnResettingDuration(int onResettingDuration) {
		this.onResettingDuration = onResettingDuration;
	}

	public int getOnStartingDuration() {
		return onStartingDuration;
	}

	public void setOnStartingDuration(int onStartingDuration) {
		this.onStartingDuration = onStartingDuration;
	}

	public int getOnCompletingDuration() {
		return onCompletingDuration;
	}

	public void setOnCompletingDuration(int onCompletingDuration) {
		this.onCompletingDuration = onCompletingDuration;
	}

	public int getOnSuspendingDuration() {
		return onSuspendingDuration;
	}

	public void setOnSuspendingDuration(int onSuspendingDuration) {
		this.onSuspendingDuration = onSuspendingDuration;
	}

	public int getOnUnsuspendingDuration() {
		return onUnsuspendingDuration;
	}

	public void setOnUnsuspendingDuration(int onUnsuspendingDuration) {
		this.onUnsuspendingDuration = onUnsuspendingDuration;
	}

	public int getOnHoldingDuration() {
		return onHoldingDuration;
	}

	public void setOnHoldingDuration(int onHoldingDuration) {
		this.onHoldingDuration = onHoldingDuration;
	}

	public int getOnUnholdingDuration() {
		return onUnholdingDuration;
	}

	public void setOnUnholdingDuration(int onUnholdingDuration) {
		this.onUnholdingDuration = onUnholdingDuration;
	}

	public ErrorStatus getOnCompletingErrorStatus() {
		return onCompletingErrorStatus;
	}

	public void setOnCompletingErrorStatus(ErrorStatus onCompletingErrorStatus) {
		this.onCompletingErrorStatus = onCompletingErrorStatus;
	}

	public List<Parameter> getOnCompletingVariables() {
		return onCompletingVariables;
	}

	public void setOnCompletingVariables(List<Parameter> onCompletingVariables) {
		this.onCompletingVariables = onCompletingVariables;
	}

	public ErrorStatus getOnStoppingErrorStatus() {
		return onStoppingErrorStatus;
	}

	public void setOnStoppingErrorStatus(ErrorStatus onStoppingErrorStatus) {
		this.onStoppingErrorStatus = onStoppingErrorStatus;
	}

	public List<Parameter> getOnStoppingVariables() {
		return onStoppingVariables;
	}

	public void setOnStoppingVariables(List<Parameter> onStoppingVariables) {
		this.onStoppingVariables = onStoppingVariables;
	}

	public static class Builder {
		private int onAbortingDuration = 1000;
		private int onClearingDuration = 1000;
		private int onStoppingDuration = 1000;
		private int onExecuteDuration = 1000;
		private int onResettingDuration = 1000;
		private int onStartingDuration = 1000;
		private int onCompletingDuration = 1000;
		private int onSuspendingDuration = 1000;
		private int onUnsuspendingDuration = 1000;
		private int onHoldingDuration = 1000;
		private int onUnholdingDuration = 1000;
		private ErrorStatus onCompletingErrorStatus = new ErrorStatus.Builder().errorCode(0).errorMessage("OK").build();
		private List<Parameter> onCompletingVariables = new LinkedList<>();
		private ErrorStatus onStoppingErrorStatus = new ErrorStatus.Builder().errorCode(1).errorMessage("NOK").build();
		private List<Parameter> onStoppingVariables = new LinkedList<>();;

		public Builder onAbortingDuration(int onAbortingDuration) {
			this.onAbortingDuration = onAbortingDuration;
			return this;
		}

		public Builder onClearingDuration(int onClearingDuration) {
			this.onClearingDuration = onClearingDuration;
			return this;
		}

		public Builder onStoppingDuration(int onStoppingDuration) {
			this.onStoppingDuration = onStoppingDuration;
			return this;
		}

		public Builder onExecuteDuration(int onExecuteDuration) {
			this.onExecuteDuration = onExecuteDuration;
			return this;
		}

		public Builder onResettingDuration(int onResettingDuration) {
			this.onResettingDuration = onResettingDuration;
			return this;
		}

		public Builder onStartingDuration(int onStartingDuration) {
			this.onStartingDuration = onStartingDuration;
			return this;
		}

		public Builder onCompletingDuration(int onCompletingDuration) {
			this.onCompletingDuration = onCompletingDuration;
			return this;
		}

		public Builder onSuspendingDuration(int onSuspendingDuration) {
			this.onSuspendingDuration = onSuspendingDuration;
			return this;
		}

		public Builder onUnsuspendingDuration(int onUnsuspendingDuration) {
			this.onUnsuspendingDuration = onUnsuspendingDuration;
			return this;
		}

		public Builder onHoldingDuration(int onHoldingDuration) {
			this.onHoldingDuration = onHoldingDuration;
			return this;
		}

		public Builder onUnholdingDuration(int onUnholdingDuration) {
			this.onUnholdingDuration = onUnholdingDuration;
			return this;
		}

		public Builder onCompletingErrorStatus(ErrorStatus onCompletingErrorStatus) {
			this.onCompletingErrorStatus = onCompletingErrorStatus;
			return this;
		}

		public Builder onCompletingVariables(List<Parameter> onCompletingVariables) {
			this.onCompletingVariables = onCompletingVariables;
			return this;
		}

		public Builder onStoppingErrorStatus(ErrorStatus onStoppingErrorStatus) {
			this.onStoppingErrorStatus = onStoppingErrorStatus;
			return this;
		}

		public Builder onStoppingVariables(List<Parameter> onStoppingVariables) {
			this.onStoppingVariables = onStoppingVariables;
			return this;
		}

		public SimulationConfiguration build() {
			return new SimulationConfiguration(this);
		}
	}

	private SimulationConfiguration(Builder builder) {
		this.onAbortingDuration = builder.onAbortingDuration;
		this.onClearingDuration = builder.onClearingDuration;
		this.onStoppingDuration = builder.onStoppingDuration;
		this.onExecuteDuration = builder.onExecuteDuration;
		this.onResettingDuration = builder.onResettingDuration;
		this.onStartingDuration = builder.onStartingDuration;
		this.onCompletingDuration = builder.onCompletingDuration;
		this.onSuspendingDuration = builder.onSuspendingDuration;
		this.onUnsuspendingDuration = builder.onUnsuspendingDuration;
		this.onHoldingDuration = builder.onHoldingDuration;
		this.onUnholdingDuration = builder.onUnholdingDuration;
		this.onCompletingErrorStatus = builder.onCompletingErrorStatus;
		this.onCompletingVariables = builder.onCompletingVariables;
		this.onStoppingErrorStatus = builder.onStoppingErrorStatus;
		this.onStoppingVariables = builder.onStoppingVariables;
	}
}
