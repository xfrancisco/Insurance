package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_COUNTRY")
public class Cod_country {

	@Id
	private String ccountry;
	private String lcountry;
	private String indvali;

	public String getCcountry() {
		return ccountry;
	}

	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}

	public String getLcountry() {
		return lcountry;
	}

	public void setLcountry(String lcountry) {
		this.lcountry = lcountry;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
