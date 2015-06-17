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
@Table(name = "CLI_EMAIL")
public class Cli_email implements Serializable {

	private static final long serialVersionUID = 8295999420888213011L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMEMAIL_SEQ")
	@SequenceGenerator(name = "NUMEMAIL_SEQ", sequenceName = "NUMEMAIL_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numemail;
	private Long numcli;
	private String cemail;
	private String email;
	private String cusercre;
	private String cusermod;
	private java.sql.Date startval;
	private java.sql.Date endval;
	private Date creationDate;
	private Date modifDate;

	public Cli_email() {
	}

	public Cli_email(String cemail, String email) {
		this.cemail = cemail;
		this.email = email;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Cli_email other = (Cli_email) o;
		return other.getCemail().equals(cemail) && other.getEmail().equals(email);
	}

	public Long getNumemail() {
		return numemail;
	}

	public void setNumemail(Long numemail) {
		this.numemail = numemail;
	}

}
