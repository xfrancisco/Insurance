package org.mfi.dto.movements;

public class MovementDetailDetailsDto {
	private String detailCode;
	private String detailLabel;
	private String detailValue;
	private String detailValueLabel;

	public MovementDetailDetailsDto(String detailCode, String detailLabel, String detailValue, String detailValueLabel) {
		this.detailCode = detailCode;
		this.detailLabel = detailLabel;
		this.detailValue = detailValue;
		this.detailValueLabel = detailValueLabel;

	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getDetailLabel() {
		return detailLabel;
	}

	public void setDetailLabel(String detailLabel) {
		this.detailLabel = detailLabel;
	}

	public String getDetailValue() {
		return detailValue;
	}

	public void setDetailValue(String detailValue) {
		this.detailValue = detailValue;
	}

	public String getDetailValueLabel() {
		return detailValueLabel;
	}

	public void setDetailValueLabel(String detailValueLabel) {
		this.detailValueLabel = detailValueLabel;
	}

	public String getDetailCode() {
		return detailCode;
	}

}
