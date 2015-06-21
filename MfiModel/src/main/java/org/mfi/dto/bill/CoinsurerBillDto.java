package org.mfi.dto.bill;

import java.math.BigDecimal;

public class CoinsurerBillDto {

	private long numcliins;
	private BigDecimal amount;

	public CoinsurerBillDto(BigDecimal amount, long numcliins) {
		this.amount = amount;
		this.numcliins = numcliins;
	}

	public long getNumcliins() {
		return numcliins;
	}

	public void setNumcliins(long numcliins) {
		this.numcliins = numcliins;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
