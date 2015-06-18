package org.mfi.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_FEE")
public class Cod_fee {

	@Id
	private String cfee;
	private String lfee;
	private String indpolicyfee;
	private String indvali;

	public String getCfee() {
		return cfee;
	}

	public void setCfee(String cfee) {
		this.cfee = cfee;
	}

	public String getLfee() {
		return lfee;
	}

	public void setLfee(String lfee) {
		this.lfee = lfee;
	}

	public String getIndpolicyfee() {
		return indpolicyfee;
	}

	public void setIndpolicyfee(String indpolicyfee) {
		this.indpolicyfee = indpolicyfee;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
