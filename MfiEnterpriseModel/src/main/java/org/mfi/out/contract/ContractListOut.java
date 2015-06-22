package org.mfi.out.contract;

import org.mfi.annotations.NameSetter;

public class ContractListOut {

	@NameSetter
	private long personId;
	private String personIdLabel;
	private int contractId;
	private String branchId;
	private String categoryId;
	private String underwriterId;
	private Integer quoteId;

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUnderwriterId() {
		return underwriterId;
	}

	public void setUnderwriterId(String underwriterId) {
		this.underwriterId = underwriterId;
	}

	public Integer getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(Integer quoteId) {
		this.quoteId = quoteId;
	}

	public String getPersonIdLabel() {
		return personIdLabel;
	}

	public void setPersonIdLabel(String personIdLabel) {
		this.personIdLabel = personIdLabel;
	}
}