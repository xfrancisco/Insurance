package org.insurance.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_CLIENT")
public class Cli_client {

	@Id
	private Long numcli;

	private String ccivil;
	private String name;
	private String firstname;
	private String companyname;

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

}
