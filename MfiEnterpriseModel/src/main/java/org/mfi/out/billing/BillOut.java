package org.mfi.out.billing;

import java.math.BigDecimal;
import java.util.List;

public class BillOut {
	private List<PremiumBillOut> premiumBills;
	private BigDecimal grossTotalAmount;
	private BigDecimal brokerTotalAmount;
	private BigDecimal netCompanyTotalAmount;
	private List<BillFeeOut> fee;
	private String startDate;
	private String endDate;

	public List<PremiumBillOut> getPremiumBills() {
		return premiumBills;
	}

	public void setPremiumBills(List<PremiumBillOut> premiumBills) {
		this.premiumBills = premiumBills;
	}

	public BigDecimal getGrossTotalAmount() {
		return grossTotalAmount;
	}

	public void setGrossTotalAmount(BigDecimal grossTotalAmount) {
		this.grossTotalAmount = grossTotalAmount;
	}

	public BigDecimal getBrokerTotalAmount() {
		return brokerTotalAmount;
	}

	public void setBrokerTotalAmount(BigDecimal brokerTotalAmount) {
		this.brokerTotalAmount = brokerTotalAmount;
	}

	public BigDecimal getNetCompanyTotalAmount() {
		return netCompanyTotalAmount;
	}

	public void setNetCompanyTotalAmount(BigDecimal netCompanyTotalAmount) {
		this.netCompanyTotalAmount = netCompanyTotalAmount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<BillFeeOut> getFee() {
		return fee;
	}

	public void setFee(List<BillFeeOut> fee) {
		this.fee = fee;
	}

}