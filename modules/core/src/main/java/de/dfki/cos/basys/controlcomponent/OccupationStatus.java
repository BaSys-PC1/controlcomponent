/**
 */
package de.dfki.cos.basys.controlcomponent;

public class OccupationStatus {
	OccupationState level;
	String occupierId;

	public OccupationState getLevel() {
		return level;
	}

	public void setLevel(OccupationState level) {
		this.level = level;
	}

	public String getOccupierId() {
		return occupierId;
	}

	public void setOccupierId(String occupierId) {
		this.occupierId = occupierId;
	}

	public static class Builder {
		private OccupationState level;
		private String occupierId;

		public Builder level(OccupationState level) {
			this.level = level;
			return this;
		}

		public Builder occupierId(String occupierId) {
			this.occupierId = occupierId;
			return this;
		}

		public OccupationStatus build() {
			return new OccupationStatus(this);
		}
	}

	private OccupationStatus(Builder builder) {
		this.level = builder.level;
		this.occupierId = builder.occupierId;
	}
}

