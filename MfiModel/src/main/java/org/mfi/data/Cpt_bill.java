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
@Table(name = "CPT_BILL")
public class Cpt_bill implements Serializable {

	private static final long serialVersionUID = 4245472251400548386L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMBILL_SEQ")
	@SequenceGenerator(name = "NUMBILL_SEQ", sequenceName = "NUMBILL_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numbill;
	private Long numcli;
	private Integer numcon;
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

	public Integer getNumcon() {
		return numcon;
	}

	public void setNumcon(Integer numcon) {
		this.numcon = numcon;
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

}
