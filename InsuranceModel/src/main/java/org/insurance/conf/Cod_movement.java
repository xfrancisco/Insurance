package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_MOVEMENT")
public class Cod_movement {

	@Id
	private String cmovement;
	private String lmovement;
	private String indvali;

	public String getCmovement() {
		return cmovement;
	}

	public void setCmovement(String cmovement) {
		this.cmovement = cmovement;
	}

	public String getLmovement() {
		return lmovement;
	}

	public void setLmovement(String lmovement) {
		this.lmovement = lmovement;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
