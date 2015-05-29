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

	private String cbranch;
	private String ccategory;

	private java.sql.Date startVal;

	private java.sql.Date endVal;

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

	public String getCbranch() {
		return cbranch;
	}

	public void setCbranch(String cbranch) {
		this.cbranch = cbranch;
	}

	public String getCcategory() {
		return ccategory;
	}

	public void setCcategory(String ccategory) {
		this.ccategory = ccategory;
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
