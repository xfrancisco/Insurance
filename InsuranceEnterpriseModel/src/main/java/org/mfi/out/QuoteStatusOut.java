package org.mfi.out;

public class QuoteStatusOut {

	private String label;
	private String id;
	private boolean isPending;
	private boolean isAccepted;
	private boolean isRefused;
	private boolean isValidated;
	private boolean isAborted;
	private boolean isValid = false;

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

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean getIsPending() {
		return isPending;
	}

	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public boolean getIsRefused() {
		return isRefused;
	}

	public void setRefused(boolean isRefused) {
		this.isRefused = isRefused;
	}

	public boolean getIsValidated() {
		return isValidated;
	}

	public void setValidated(boolean isValidated) {
		this.isValidated = isValidated;
	}

	public boolean getIsAborted() {
		return isAborted;
	}

	public void setAborted(boolean isAborted) {
		this.isAborted = isAborted;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}