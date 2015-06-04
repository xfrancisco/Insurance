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
@Table(name = "CPT_GUARCOMMI")
public class Cpt_guarcommi implements Serializable {

	private static final long serialVersionUID = -795149994051799338L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMCOMMI_SEQ")
	@SequenceGenerator(name = "NUMCOMMI_SEQ", sequenceName = "NUMCOMMI_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numcommi;
	private Long numguarantee;
	private Long numclicommi;
	private BigDecimal rate;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumcommi() {
		return numcommi;
	}

	public void setNumcommi(Long numcommi) {
		this.numcommi = numcommi;
	}

	public Long getNumguarantee() {
		return numguarantee;
	}

	public void setNumguarantee(Long numguarantee) {
		this.numguarantee = numguarantee;
	}

	public Long getNumclicommi() {
		return numclicommi;
	}

	public void setNumclicommi(Long numclicommi) {
		this.numclicommi = numclicommi;
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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
