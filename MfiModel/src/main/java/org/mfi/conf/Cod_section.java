package org.mfi.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_SECTION")
public class Cod_section {

	@Id
	private String csection;
	private String lsection;
	private String indvali;

	public String getCsection() {
		return csection;
	}

	public void setCsection(String csection) {
		this.csection = csection;
	}

	public String getLsection() {
		return lsection;
	}

	public void setLsection(String lsection) {
		this.lsection = lsection;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
