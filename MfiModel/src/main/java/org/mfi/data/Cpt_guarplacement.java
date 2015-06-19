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
public class Cpt_guarplacement implements Serializable {

	private static final long serialVersionUID = 7380226496752978707L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMPLACEMENT_SEQ")
	@SequenceGenerator(name = "NUMPLACEMENT_SEQ", sequenceName = "NUMPLACEMENT_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numplacement;
	private Long numguarantee;
	private Long numcliins;
	private BigDecimal sharepart;
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

	public Long getNumcliins() {
		return numcliins;
	}

	public void setNumcliins(Long numcliins) {
		this.numcliins = numcliins;
	}

	public BigDecimal getSharepart() {
		return sharepart;
	}

	public void setSharepart(BigDecimal sharepart) {
		this.sharepart = sharepart;
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

	public Long getNumplacement() {
		return numplacement;
	}

	public void setNumplacement(Long numplacement) {
		this.numplacement = numplacement;
	}

}
