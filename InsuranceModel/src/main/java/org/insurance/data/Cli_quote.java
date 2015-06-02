package org.insurance.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Strings;

@Entity
@Table(name = "CLI_QUOTE")
public class Cli_quote implements Serializable {

	private static final long serialVersionUID = -4250563351971771860L;
	@Id
	private Long numcli;
	@Id
	private Integer numquote;
	private Integer numcon;
	private String cbranch;
	private String ccategory;
	private Long numclibroker;
	private Long numclileader;
	private java.sql.Date startval;
	private java.sql.Date endval;
	private java.sql.Date receptiondate;
	private java.sql.Date acceptancedate;
	private String cquotestatus;
	private String cduration;
	private String cuseruw;
	private BigDecimal guaranteedamount;
	private BigDecimal premiumamount;
	private BigDecimal sharepart;

	private String cusercre;
	private String cusermod;
	private String cusercancel;
	private Date creationDate;
	private Date modifDate;
	private Date cancelDate;

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return true;
		Cli_quote quote = (Cli_quote) o;
		if (this.getAcceptancedate() != null && !this.getAcceptancedate().equals(quote.getAcceptancedate()))
			return false;
		if (!Strings.nullToEmpty(this.getCbranch()).equals(quote.getCbranch()))
			return false;
		if (!Strings.nullToEmpty(this.getCcategory()).equals(quote.getCcategory()))
			return false;
		if (!Strings.nullToEmpty(this.getCduration()).equals(quote.getCduration()))
			return false;
		if (!Strings.nullToEmpty(this.getCquotestatus()).equals(quote.getCquotestatus()))
			return false;
		if (!Strings.nullToEmpty(this.getCuseruw()).equals(quote.getCuseruw()))
			return false;
		if (this.getEndval() != null && !this.getEndval().equals(quote.getEndval()))
			return false;
		if (this.getGuaranteedamount() != null && !this.getGuaranteedamount().equals(quote.getGuaranteedamount()))
			return false;
		if (this.getNumclibroker() != null && !this.getNumclibroker().equals(quote.getNumclibroker()))
			return false;
		if (this.getNumclileader() != null && !this.getNumclileader().equals(quote.getNumclileader()))
			return false;
		if (this.getPremiumamount() != null && !this.getPremiumamount().equals(quote.getPremiumamount()))
			return false;
		if (this.getReceptiondate() != null && !this.getReceptiondate().equals(quote.getReceptiondate()))
			return false;
		if (this.getSharepart() != null && !this.getSharepart().equals(quote.getSharepart()))
			return false;
		if (this.getStartval() != null && !this.getStartval().equals(quote.getStartval()))
			return false;
		return true;

	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public Integer getNumquote() {
		return numquote;
	}

	public void setNumquote(Integer numquote) {
		this.numquote = numquote;
	}

	public Integer getNumcon() {
		return numcon;
	}

	public void setNumcon(Integer numcon) {
		this.numcon = numcon;
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

	public java.sql.Date getReceptiondate() {
		return receptiondate;
	}

	public void setReceptiondate(java.sql.Date receptiondate) {
		this.receptiondate = receptiondate;
	}

	public java.sql.Date getAcceptancedate() {
		return acceptancedate;
	}

	public void setAcceptancedate(java.sql.Date acceptancedate) {
		this.acceptancedate = acceptancedate;
	}

	public String getCquotestatus() {
		return cquotestatus;
	}

	public void setCquotestatus(String cquotestatus) {
		this.cquotestatus = cquotestatus;
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

	public BigDecimal getGuaranteedamount() {
		return guaranteedamount;
	}

	public void setGuaranteedamount(BigDecimal guaranteedamount) {
		this.guaranteedamount = guaranteedamount;
	}

	public BigDecimal getPremiumamount() {
		return premiumamount;
	}

	public void setPremiumamount(BigDecimal premiumamount) {
		this.premiumamount = premiumamount;
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

	public BigDecimal getSharepart() {
		return sharepart;
	}

	public void setSharepart(BigDecimal sharepart) {
		this.sharepart = sharepart;
	}

}
