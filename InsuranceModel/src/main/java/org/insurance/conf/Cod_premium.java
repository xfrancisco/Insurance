package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_PREMIUM")
public class Cod_premium {

	@Id
	private String cpremium;
	private String lpremium;
	private String ctax;
	private String indvali;

	public String getCpremium() {
		return cpremium;
	}

	public void setCpremium(String cpremium) {
		this.cpremium = cpremium;
	}

	public String getLpremium() {
		return lpremium;
	}

	public void setLpremium(String lpremium) {
		this.lpremium = lpremium;
	}

	public String getCtax() {
		return ctax;
	}

	public void setCtax(String ctax) {
		this.ctax = ctax;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
