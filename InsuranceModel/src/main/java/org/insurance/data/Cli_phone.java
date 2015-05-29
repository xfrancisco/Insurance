package org.insurance.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_PHONE")
public class Cli_phone implements Serializable {

	private static final long serialVersionUID = -4522074571212364248L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMPHONE_SEQ")
	@SequenceGenerator(name = "NUMPHONE_SEQ", sequenceName = "NUMPHONE_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numphone;
	private Long numcli;
	private String cphone;
	private String phonenumber;
	private String cusercre;
	private String cusermod;
	private java.sql.Date startVal;
	private java.sql.Date endVal;
	private Date creationDate;
	private Date modifDate;

	public Cli_phone() {

	}

	public Cli_phone(String cphone, String phoneNumber) {
		this.cphone = cphone;
		this.phonenumber = phoneNumber;

	}

	public Long getNumphone() {
		return numphone;
	}

	public void setNumphone(Long numphone) {
		this.numphone = numphone;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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

	public java.sql.Date getStartVal() {
		return startVal;
	}

	public void setStartVal(java.sql.Date startVal) {
		this.startVal = startVal;
	}

	public java.sql.Date getEndVal() {
		return endVal;
	}

	public void setEndVal(java.sql.Date endVal) {
		this.endVal = endVal;
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

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Cli_phone other = (Cli_phone) o;
		return other.getCphone().equals(cphone) && other.getPhonenumber().equals(phonenumber);
	}

}
