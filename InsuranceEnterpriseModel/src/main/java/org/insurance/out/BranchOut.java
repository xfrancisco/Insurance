package org.insurance.out;

import java.io.Serializable;

public class BranchOut extends EntityOut implements Serializable {
	private static final long serialVersionUID = -5317852693059681965L;
	private String branchId;
	private String branchLabel;
	private boolean isValid = false;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchLabel() {
		return branchLabel;
	}

	public void setBranchLabel(String branchLabel) {
		this.branchLabel = branchLabel;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
