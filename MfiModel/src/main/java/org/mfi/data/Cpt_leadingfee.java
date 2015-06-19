package org.mfi.data;

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
@Table(name = "CPT_LEADINGFEE")
public class Cpt_leadingfee implements Serializable {

	private static final long serialVersionUID = 738622809422791175L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMLEADINGFEE_SEQ")
	@SequenceGenerator(name = "NUMLEADINGFEE_SEQ", sequenceName = "NUMLEADINGFEE_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numleadingfee;
	private Long numguarantee;
	private Long numclisrc;
	private Long numclidest;
	private BigDecimal rate;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumleadingfee() {
		return numleadingfee;
	}

	public void setNumleadingfee(Long numleadingfee) {
		this.numleadingfee = numleadingfee;
	}

	public Long getNumclisrc() {
		return numclisrc;
	}

	public void setNumclisrc(Long numclisrc) {
		this.numclisrc = numclisrc;
	}

	public Long getNumclidest() {
		return numclidest;
	}

	public void setNumclidest(Long numclidest) {
		this.numclidest = numclidest;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
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

	public Long getNumguarantee() {
		return numguarantee;
	}

	public void setNumguarantee(Long numguarantee) {
		this.numguarantee = numguarantee;
	}

}
