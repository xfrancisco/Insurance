package org.insurance.in;

import org.insurance.validation.constraints.Mandatory;

import com.google.common.base.Strings;

public class CodeTableIn {

	@Mandatory
	private String id;

	@Mandatory
	private String label;

	@Mandatory
	private boolean isValid;

	@Mandatory
	private String codeTableId;

	public String getId() {
		return Strings.nullToEmpty(id).toUpperCase().trim();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return Strings.nullToEmpty(label).toUpperCase().trim();
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getCodeTableId() {
		return Strings.nullToEmpty(codeTableId).toUpperCase().trim();
	}

	public void setCodeTableId(String codeTableId) {
		this.codeTableId = codeTableId;
	}

}
