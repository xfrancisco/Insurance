package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_FREQUENCY")
public class Cod_frequency {

	@Id
	private String cfrequency;
	private String lfrequency;
	private String indvali;
	private int nbmonths;

	public String getCfrequency() {
		return cfrequency;
	}

	public void setCfrequency(String cfrequency) {
		this.cfrequency = cfrequency;
	}

	public String getLfrequency() {
		return lfrequency;
	}

	public void setLfrequency(String lfrequency) {
		this.lfrequency = lfrequency;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

	public int getNbmonths() {
		return nbmonths;
	}

	public void setNbmonths(int nbmonths) {
		this.nbmonths = nbmonths;
	}

}
