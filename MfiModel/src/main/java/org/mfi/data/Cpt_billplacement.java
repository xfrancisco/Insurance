package org.mfi.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CPT_BILLPLACEMENT")
public class Cpt_billplacement implements Serializable {

	private static final long serialVersionUID = -6543629048791679311L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMBILLPLACEMENT_SEQ")
	@SequenceGenerator(name = "NUMBILLPLACEMENT_SEQ", sequenceName = "NUMBILLPLACEMENT_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numbillplacement;
	private Long numbill;
	private Long numcli;
	private Long numguarantee;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumbill() {
		return numbill;
	}

	public void setNumbill(Long numbill) {
		this.numbill = numbill;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

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

	public Long getNumbillplacement() {
		return numbillplacement;
	}

	public void setNumbillplacement(Long numbillplacement) {
		this.numbillplacement = numbillplacement;
	}

}
