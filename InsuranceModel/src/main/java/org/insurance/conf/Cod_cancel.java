package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_CANCEL")
public class Cod_cancel {

	@Id
	private String ccancel;
	private String lcancel;
	private String indvali;

	public String getCcancel() {
		return ccancel;
	}

	public void setCcancel(String ccancel) {
		this.ccancel = ccancel;
	}

	public String getLcancel() {
		return lcancel;
	}

	public void setLcancel(String lcancel) {
		this.lcancel = lcancel;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
