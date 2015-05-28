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
	private String indclient;
	private String indbroker;
	private String indinsurance;
	private String indreinsurance;
	private String indexpert;
	private String indbenef;
	private String indtiers;
	private String indlawyer;

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

	public String getIndclient() {
		return indclient;
	}

	public void setIndclient(String indclient) {
		this.indclient = indclient;
	}

	public String getIndbroker() {
		return indbroker;
	}

	public void setIndbroker(String indbroker) {
		this.indbroker = indbroker;
	}

	public String getIndinsurance() {
		return indinsurance;
	}

	public void setIndinsurance(String indinsurance) {
		this.indinsurance = indinsurance;
	}

	public String getIndreinsurance() {
		return indreinsurance;
	}

	public void setIndreinsurance(String indreinsurance) {
		this.indreinsurance = indreinsurance;
	}

	public String getIndexpert() {
		return indexpert;
	}

	public void setIndexpert(String indexpert) {
		this.indexpert = indexpert;
	}

	public String getIndbenef() {
		return indbenef;
	}

	public void setIndbenef(String indbenef) {
		this.indbenef = indbenef;
	}

	public String getIndtiers() {
		return indtiers;
	}

	public void setIndtiers(String indtiers) {
		this.indtiers = indtiers;
	}

	public String getIndlawyer() {
		return indlawyer;
	}

	public void setIndlawyer(String indlawyer) {
		this.indlawyer = indlawyer;
	}

}
