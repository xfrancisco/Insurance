package org.insurance.out;

import java.io.Serializable;

public class GuaranteeOut extends EntityOut implements Serializable {
	private static final long serialVersionUID = -4226198837034076887L;
	private String guaranteeId;
	private String guaranteeLabel;
	private boolean isValid = false;

	public String getGuaranteeId() {
		return guaranteeId;
	}

	public void setGuaranteeId(String guaranteeId) {
		this.guaranteeId = guaranteeId;
	}

	public String getGuaranteeLabel() {
		return guaranteeLabel;
	}

	public void setGuaranteeLabel(String guaranteeLabel) {
		this.guaranteeLabel = guaranteeLabel;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
