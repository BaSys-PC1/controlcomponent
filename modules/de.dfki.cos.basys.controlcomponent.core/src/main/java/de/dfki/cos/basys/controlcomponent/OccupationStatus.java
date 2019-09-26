/**
 */
package de.dfki.cos.basys.controlcomponent;

public class OccupationStatus {
	OccupationLevel level;
	String occupierId;

	public OccupationLevel getLevel() {
		return level;
	}

	public void setLevel(OccupationLevel level) {
		this.level = level;
	}

	public String getOccupierId() {
		return occupierId;
	}

	public void setOccupierId(String occupierId) {
		this.occupierId = occupierId;
	}

	public static class Builder {
		private OccupationLevel level;
		private String occupierId;

		public Builder level(OccupationLevel level) {
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

