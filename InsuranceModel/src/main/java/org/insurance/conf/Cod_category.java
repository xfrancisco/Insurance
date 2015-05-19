package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CATEGORY")
public class Cod_category {

	@Id
	private String ccateg;
	private String lcateg;
	private String indvali;

	public String getCcateg() {
		return ccateg;
	}

	public void setCcateg(String ccateg) {
		this.ccateg = ccateg;
	}

	public String getLcateg() {
		return lcateg;
	}

	public void setLcateg(String lcateg) {
		this.lcateg = lcateg;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
