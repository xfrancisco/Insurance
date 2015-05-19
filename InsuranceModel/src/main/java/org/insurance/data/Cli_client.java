package org.insurance.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_CLIENT")
public class Cli_client {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMCLI_SEQ")
	@SequenceGenerator(name = "NUMCLI_SEQ", sequenceName = "NUMCLI_SEQ")
	@Id
	private Long numcli;

	private String ccivil;
	private String name;
	private String firstname;
	private String companyname;
	private String companyid;

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

}
