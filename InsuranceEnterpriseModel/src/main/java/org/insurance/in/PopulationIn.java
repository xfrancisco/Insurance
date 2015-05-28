package org.insurance.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PopulationIn {
	private boolean isClient;
	private boolean isInsurer;
	private boolean isBroker;
	private boolean isReinsurer;
	private boolean isLawyer;
	private boolean isExpert;
	private boolean isBeneficiary;
	private boolean isThirdParty;

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}

	public boolean isInsurer() {
		return isInsurer;
	}

	public void setInsurer(boolean isInsurer) {
		this.isInsurer = isInsurer;
	}

	public boolean isBroker() {
		return isBroker;
	}

	public void setBroker(boolean isBroker) {
		this.isBroker = isBroker;
	}

	public boolean isReinsurer() {
		return isReinsurer;
	}

	public void setReinsurer(boolean isReinsurer) {
		this.isReinsurer = isReinsurer;
	}

	public boolean isLawyer() {
		return isLawyer;
	}

	public void setLawyer(boolean isLawyer) {
		this.isLawyer = isLawyer;
	}

	public boolean isExpert() {
		return isExpert;
	}

	public void setExpert(boolean isExpert) {
		this.isExpert = isExpert;
	}

	public boolean isBeneficiary() {
		return isBeneficiary;
	}

	public void setBeneficiary(boolean isBeneficiary) {
		this.isBeneficiary = isBeneficiary;
	}

	public boolean isThirdParty() {
		return isThirdParty;
	}

	public void setThirdParty(boolean isThirdParty) {
		this.isThirdParty = isThirdParty;
	}

}
