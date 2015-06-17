package org.mfi.dto.movements;

import java.util.ArrayList;
import java.util.List;

public class MovementDetailDto {

	private String code;
	private String label;
	private String value;
	private String valueLabel;

	private List<MovementDetailDetailsDto> furtherDetails;

	public MovementDetailDto() {
		super();
	}

	public MovementDetailDto(String code, String label, String oldValue, String value, String valueLabel) {
		super();
		this.code = code;
		this.label = label;
		this.value = value;
		this.valueLabel = valueLabel;
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
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

	public List<MovementDetailDetailsDto> getFurtherDetails() {
		if (this.furtherDetails == null) {
			this.furtherDetails = new ArrayList<>();
		}
		return this.furtherDetails;
	}

	public void setFurtherDetails(List<MovementDetailDetailsDto> furtherDetails) {
		this.furtherDetails = furtherDetails;
	}

	public void setFurtherDetails(MovementDetailDetailsDto furtherDetails) {
		if (this.furtherDetails == null) {
			this.furtherDetails = new ArrayList<>();
		}
		this.furtherDetails.add(furtherDetails);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void addFurtherDetails(List<MovementDetailDetailsDto> details) {
		if (this.furtherDetails == null) {
			this.furtherDetails = new ArrayList<>();
		}
		this.furtherDetails.addAll(details);

	}
}
