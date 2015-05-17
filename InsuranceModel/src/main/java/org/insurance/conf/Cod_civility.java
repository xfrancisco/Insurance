package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COD_CIVILITE")
public class Cod_civility {

	@Id
	private String ccivil;
	private String lcivil;
	private String indvali;
	
	public String getCcivil() {
		return ccivil;
	}
	public void setCcivil(String ccivil) {
		this.ccivil = ccivil;
	}
	public String getLcivil() {
		return lcivil;
	}
	public void setLcivil(String lcivil) {
		this.lcivil = lcivil;
	}
	public String getIndvali() {
		return indvali;
	}
	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}
	
}

