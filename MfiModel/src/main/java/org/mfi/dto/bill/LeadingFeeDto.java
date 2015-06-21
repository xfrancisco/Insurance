package org.mfi.dto.bill;

import java.math.BigDecimal;

public class LeadingFeeDto {

	private long numcliins;
	private BigDecimal rate;
	private BigDecimal amount;
	private BigDecimal share;

	public LeadingFeeDto(long numcliins, BigDecimal rate, BigDecimal amount) {
		this.numcliins = numcliins;
		this.rate = rate;
		this.amount = amount;
	}

	public long getNumcliins() {
		return numcliins;
	}

	public void setNumcliins(long numcliins) {
		this.numcliins = numcliins;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getShare() {
		return share;
	}

	public void setShare(BigDecimal share) {
		this.share = share;
	}

}
