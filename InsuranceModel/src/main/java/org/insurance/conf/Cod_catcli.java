package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CATCLI")
public class Cod_catcli {

	@Id
	private String ccatcli;
	private String lcatcli;
	private String indvali;

	public String getCcatcli() {
		return ccatcli;
	}

	public void setCcatcli(String ccatcli) {
		this.ccatcli = ccatcli;
	}

	public String getLcatcli() {
		return lcatcli;
	}

	public void setLcatcli(String lcatcli) {
		this.lcatcli = lcatcli;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
