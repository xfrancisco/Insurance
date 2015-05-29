package org.insurance.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Strings;

@Entity
@Table(name = "CLI_CLIENT")
public class Cli_client {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMCLI_SEQ")
	@SequenceGenerator(name = "NUMCLI_SEQ", sequenceName = "NUMCLI_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numcli;

	private String ccivil;
	private String name;
	private String firstname;
	private String companyname;
	private String companyid;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public String getCcivil() {
		return ccivil;
	}

	public void setCcivil(String ccivil) {
		this.ccivil = ccivil;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
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

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return true;
		Cli_client client = (Cli_client) o;
		if (!Strings.nullToEmpty(this.ccivil).equals(Strings.nullToEmpty(client.getCcivil()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.name).equals(Strings.nullToEmpty(client.getName()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.firstname).equals(Strings.nullToEmpty(client.getFirstname()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.companyname).equals(Strings.nullToEmpty(client.getCompanyname()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.companyid).equals(Strings.nullToEmpty(client.getCompanyid()))) {
			return true;
		}

		return false;

	}

}
