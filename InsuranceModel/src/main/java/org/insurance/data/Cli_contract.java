package org.insurance.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_CONTRACT")
public class Cli_contract implements Serializable {

	private static final long serialVersionUID = -4825962668924197946L;

	@Id
	private Long numcli;
	@Id
	private Integer numcon;

	private String ccontract;

	private java.sql.Date startDate;

	private java.sql.Date endDate;

	private String ccancel;

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

	public String getCcontract() {
		return ccontract;
	}

	public void setCcontract(String ccontract) {
		this.ccontract = ccontract;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public String getCcancel() {
		return ccancel;
	}

	public void setCcancel(String ccancel) {
		this.ccancel = ccancel;
	}

}
