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
@Table(name = "CPT_GUARPLACEMENT")
public class Cpt_guarbroker implements Serializable {

	private static final long serialVersionUID = 7380226496752978707L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMGUARBROKER_SEQ")
	@SequenceGenerator(name = "NUMGUARBROKER_SEQ", sequenceName = "NUMGUARBROKER_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numguarbroker;
	private Long numguarantee;
	private Long numclibro;
	private BigDecimal rate;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumguarantee() {
		return numguarantee;
	}

	public void setNumguarantee(Long numguarantee) {
		this.numguarantee = numguarantee;
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

	public Long getNumguarbroker() {
		return numguarbroker;
	}

	public void setNumguarbroker(Long numguarbroker) {
		this.numguarbroker = numguarbroker;
	}

	public Long getNumclibro() {
		return numclibro;
	}

	public void setNumclibro(Long numclibro) {
		this.numclibro = numclibro;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}
