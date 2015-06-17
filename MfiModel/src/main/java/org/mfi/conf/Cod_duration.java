package org.mfi.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_DURATION")
public class Cod_duration {

	@Id
	private String cduration;
	private String lduration;
	private String indvali;

	public String getCduration() {
		return cduration;
	}

	public void setCduration(String cduration) {
		this.cduration = cduration;
	}

	public String getLduration() {
		return lduration;
	}

	public void setLduration(String lduration) {
		this.lduration = lduration;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
