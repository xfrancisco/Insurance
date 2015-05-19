package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CONTRACT")
public class Cod_contract {

	@Id
	private String ccontract;
	private String lcontract;
	private String indvali;

	public String getCcontract() {
		return ccontract;
	}

	public void setCcontract(String ccontract) {
		this.ccontract = ccontract;
	}

	public String getLcontract() {
		return lcontract;
	}

	public void setLcontract(String lcontract) {
		this.lcontract = lcontract;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
