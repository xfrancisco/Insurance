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
@Table(name = "CPT_FEE")
public class Cpt_fee implements Serializable {

	private static final long serialVersionUID = 8426063286524702658L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMFEE_SEQ")
	@SequenceGenerator(name = "NUMFEE_SEQ", sequenceName = "NUMFEE_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private long numfee;
	private long numcli;
	private int numcon;
	private String cfee;
	private BigDecimal amount;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public long getNumfee() {
		return numfee;
	}

	public void setNumfee(long numfee) {
		this.numfee = numfee;
	}

	public long getNumcli() {
		return numcli;
	}

	public void setNumcli(long numcli) {
		this.numcli = numcli;
	}

	public int getNumcon() {
		return numcon;
	}

	public void setNumcon(int numcon) {
		this.numcon = numcon;
	}

	public String getCfee() {
		return cfee;
	}

	public void setCfee(String cfee) {
		this.cfee = cfee;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
