package org.insurance.data;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_GUARANTEE")
public class Cli_guarantee implements Serializable {

	private static final long serialVersionUID = -4695640649182214238L;

	@Id
	private Long numguarantee;
	private Long numcli;
	private Integer numcon;
	private String cguarantee;
	private BigDecimal amount;
	private java.sql.Date startVal;
	private java.sql.Date endVal;

	public Long getNumguarantee() {
		return numguarantee;
	}

	public void setNumguarantee(Long numguarantee) {
		this.numguarantee = numguarantee;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public Integer getNumcon() {
		return numcon;
	}

	public void setNumcon(Integer numcon) {
		this.numcon = numcon;
	}

	public String getCguarantee() {
		return cguarantee;
	}

	public void setCguarantee(String cguarantee) {
		this.cguarantee = cguarantee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public java.sql.Date getStartVal() {
		return startVal;
	}

	public void setStartVal(java.sql.Date startVal) {
		this.startVal = startVal;
	}

	public java.sql.Date getEndVal() {
		return endVal;
	}

	public void setEndVal(java.sql.Date endVal) {
		this.endVal = endVal;
	}

}
