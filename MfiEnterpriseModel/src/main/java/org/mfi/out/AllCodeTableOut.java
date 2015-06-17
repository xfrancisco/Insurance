package org.mfi.out;

public class AllCodeTableOut {
	private String label;
	private String id;
	private boolean isValid = false;

	private Object detail;

	public AllCodeTableOut() {

	}

	public AllCodeTableOut(String label, String id, boolean isValid, Object detail) {
		this.label = label;
		this.id = id;
		this.isValid = isValid;
		this.detail = detail;

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Object getDetail() {
		return detail;
	}

	public void setDetail(Object detail) {
		this.detail = detail;
	}

}