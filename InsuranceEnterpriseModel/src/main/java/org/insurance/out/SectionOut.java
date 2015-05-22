package org.insurance.out;

import java.io.Serializable;

public class SectionOut extends EntityOut implements Serializable {
	private static final long serialVersionUID = -4778904368046038275L;
	private String sectionId;
	private String sectionLabel;
	private boolean isValid = false;

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionLabel() {
		return sectionLabel;
	}

	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
