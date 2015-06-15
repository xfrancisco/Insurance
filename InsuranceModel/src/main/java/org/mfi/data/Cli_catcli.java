package org.mfi.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_CATCLI")
public class Cli_catcli {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMCLICATCLI_SEQ")
	@SequenceGenerator(name = "NUMCLICATCLI_SEQ", sequenceName = "NUMCLICATCLI_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numclicatcli;

	private String ccatcli;
	private Long numcli;

	public Long getNumclicatcli() {
		return numclicatcli;
	}

	public void setNumclicatcli(Long numclicatcli) {
		this.numclicatcli = numclicatcli;
	}

	public String getCcatcli() {
		return ccatcli;
	}

	public void setCcatcli(String ccatcli) {
		this.ccatcli = ccatcli;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	@Override
	public boolean equals(Object other) {
		Cli_catcli tmp = (Cli_catcli) other;
		if (tmp.getCcatcli().equals(ccatcli))
			return true;
		return false;
	}

}
