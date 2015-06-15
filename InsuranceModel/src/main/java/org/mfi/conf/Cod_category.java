package org.mfi.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CATEGORY")
public class Cod_category {

	@Id
	private String ccategory;
	private String lcategory;
	private String indvali;

	public String getCcategory() {
		return ccategory;
	}

	public void setCcategory(String ccategory) {
		this.ccategory = ccategory;
	}

	public String getLcategory() {
		return lcategory;
	}

	public void setLcategory(String lcategory) {
		this.lcategory = lcategory;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
