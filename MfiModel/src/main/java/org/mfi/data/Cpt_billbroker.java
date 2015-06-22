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
@Table(name = "CPT_BILLBROKER")
public class Cpt_billbroker implements Serializable {

	private static final long serialVersionUID = -7078717992344206838L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMBILLBROKER_SEQ")
	@SequenceGenerator(name = "NUMBILLBROKER_SEQ", sequenceName = "NUMBILLBROKER_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numbillbroker;
	private Long numbill;
	private Long numcli;
	private Long numguarantee;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumbillbroker() {
		return numbillbroker;
	}

	public void setNumbillbroker(Long numbillbroker) {
		this.numbillbroker = numbillbroker;
	}

	public Long getNumbill() {
		return numbill;
	}

	public void setNumbill(Long numbill) {
		this.numbill = numbill;
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

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

}
