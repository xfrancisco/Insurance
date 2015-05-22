package org.insurance.out;

import java.io.Serializable;

public class PremiumOut extends EntityOut implements Serializable {
	private static final long serialVersionUID = 6504868948555986484L;
	private String premiumId;
	private String premiumLabel;
	private boolean isValid = false;

	public String getPremiumId() {
		return premiumId;
	}

	public void setPremiumId(String premiumId) {
		this.premiumId = premiumId;
	}

	public String getPremiumLabel() {
		return premiumLabel;
	}

	public void setPremiumLabel(String premiumLabel) {
		this.premiumLabel = premiumLabel;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}
