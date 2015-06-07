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

	private Integer numquote;

	private String cbranch;
	private String ccategory;

	private java.sql.Date startval;

	private java.sql.Date endval;

	private String ccancel;

	private String cusercre;
	private String cusermod;
	private String cusercancel;
	private Date creationDate;
	private Date modifDate;
	private Date cancelDate;

	private Long numclibroker;
	private Long numclileader;
	private String cduration;
	private String cuseruw;

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

	public java.sql.Date getStartval() {
		return startval;
	}

	public void setStartval(java.sql.Date startval) {
		this.startval = startval;
	}

	public java.sql.Date getEndval() {
		return endval;
	}

	public void setEndval(java.sql.Date endval) {
		this.endval = endval;
	}

	public Long getNumclibroker() {
		return numclibroker;
	}

	public void setNumclibroker(Long numclibroker) {
		this.numclibroker = numclibroker;
	}

	public Long getNumclileader() {
		return numclileader;
	}

	public void setNumclileader(Long numclileader) {
		this.numclileader = numclileader;
	}

	public String getCduration() {
		return cduration;
	}

	public void setCduration(String cduration) {
		this.cduration = cduration;
	}

	public String getCuseruw() {
		return cuseruw;
	}

	public void setCuseruw(String cuseruw) {
		this.cuseruw = cuseruw;
	}

	public Integer getNumquote() {
		return numquote;
	}

	public void setNumquote(Integer numquote) {
		this.numquote = numquote;
	}

}
