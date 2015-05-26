package org.insurance.out;

import java.util.List;

public class MovementChangeValues {

	private String code;
	private String label;
	private String value;
	private String valueLabel;

	private List<MovementChangeDetail> furtherDetails;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueLabel() {
		return valueLabel;
	}

	public void setValueLabel(String valueLabel) {
		this.valueLabel = valueLabel;
	}

	public List<MovementChangeDetail> getFurtherDetails() {
		return furtherDetails;
	}

	public void setFurtherDetails(List<MovementChangeDetail> furtherDetails) {
		this.furtherDetails = furtherDetails;
	}

}
