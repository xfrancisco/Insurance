package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_TAX")
public class Cod_tax {

	@Id
	private String ctax;
	private String ltax;
	private String indvali;

	public String getCtax() {
		return ctax;
	}

	public void setCtax(String ctax) {
		this.ctax = ctax;
	}

	public String getLtax() {
		return ltax;
	}

	public void setLtax(String ltax) {
		this.ltax = ltax;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
