package org.mfi.dto.bill;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.mfi.data.Cpt_fee;

public class BillDto {

	private List<PremiumBillDto> premiumBills;
	private BigDecimal grossTotalAmount;
	private BigDecimal brokerTotalAmount;
	private BigDecimal netCompanyTotalAmount;
	private List<Cpt_fee> fees;
	private Date startDate;
	private Date endDate;

	public List<PremiumBillDto> getPremiumBills() {
		return premiumBills;
	}

	public void setPremiumBills(List<PremiumBillDto> premiumBills) {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Cpt_fee> getFees() {
		return fees;
	}

	public void setFees(List<Cpt_fee> fees) {
		this.fees = fees;
	}

}
