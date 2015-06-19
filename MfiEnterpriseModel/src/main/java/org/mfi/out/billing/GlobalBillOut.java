package org.mfi.out.billing;

import java.math.BigDecimal;
import java.util.List;

public class GlobalBillOut {
	private BigDecimal grossTotalAmount;
	private BigDecimal brokerTotalAmount;
	private BigDecimal netCompanyTotalAmount;
	private List<BillOut> bills;

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

	public List<BillOut> getBills() {
		return bills;
	}

	public void setBills(List<BillOut> bills) {
		this.bills = bills;
	}

}