package org.mfi.out.billing;

import java.util.List;

public class GlobalBillOut {
	private String grossTotalAmount;
	private String brokerTotalAmount;
	private String netCompanyTotalAmount;
	private String feesTotalAmount;
	private List<BillOut> bills;

	public String getGrossTotalAmount() {
		return grossTotalAmount;
	}

	public void setGrossTotalAmount(String grossTotalAmount) {
		this.grossTotalAmount = grossTotalAmount;
	}

	public String getBrokerTotalAmount() {
		return brokerTotalAmount;
	}

	public void setBrokerTotalAmount(String brokerTotalAmount) {
		this.brokerTotalAmount = brokerTotalAmount;
	}

	public String getNetCompanyTotalAmount() {
		return netCompanyTotalAmount;
	}

	public void setNetCompanyTotalAmount(String netCompanyTotalAmount) {
		this.netCompanyTotalAmount = netCompanyTotalAmount;
	}

	public List<BillOut> getBills() {
		return bills;
	}

	public void setBills(List<BillOut> bills) {
		this.bills = bills;
	}

	public String getFeesTotalAmount() {
		return feesTotalAmount;
	}

	public void setFeesTotalAmount(String feesTotalAmount) {
		this.feesTotalAmount = feesTotalAmount;
	}

}