package org.insurance.data;

import java.io.Serializable;
import java.util.Date;

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

	private String cusercre;
	private String cusermod;
	private String cusercancel;
	private Date creationDate;
	private Date modifDate;
	private Date cancelDate;

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

	public String getCusercre() {
		return cusercre;
	}

	public void setCusercre(String cusercre) {
		this.cusercre = cusercre;
	}

	public String getCusermod() {
		return cusermod;
	}

	public void setCusermod(String cusermod) {
		this.cusermod = cusermod;
	}

	public String getCusercancel() {
		return cusercancel;
	}

	public void setCusercancel(String cusercancel) {
		this.cusercancel = cusercancel;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifDate() {
		return modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

}
