package org.insurance.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_GUARANTEE")
public class Cli_guarantee implements Serializable {

	private static final long serialVersionUID = -4695640649182214238L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMGUARANTEE_SEQ")
	@SequenceGenerator(name = "NUMGUARANTEE_SEQ", sequenceName = "NUMGUARANTEE_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numguarantee;
	private Long numcli;
	private Integer numcon;
	private String cbranch;
	private String ccategory;
	private String cguarantee;
	private java.sql.Date startval;
	private java.sql.Date endval;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;
	private String csection;
	private String cpremium;
	private BigDecimal guaranteeamount;
	private BigDecimal premiumamount;

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

	public String getCsection() {
		return csection;
	}

	public void setCsection(String csection) {
		this.csection = csection;
	}

	public String getCpremium() {
		return cpremium;
	}

	public void setCpremium(String cpremium) {
		this.cpremium = cpremium;
	}

	public BigDecimal getGuaranteeamount() {
		return guaranteeamount;
	}

	public void setGuaranteeamount(BigDecimal guaranteeamount) {
		this.guaranteeamount = guaranteeamount;
	}

	public BigDecimal getPremiumamount() {
		return premiumamount;
	}

	public void setPremiumamount(BigDecimal premiumamount) {
		this.premiumamount = premiumamount;
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

}
