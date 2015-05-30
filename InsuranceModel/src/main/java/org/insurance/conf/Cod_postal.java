package org.insurance.conf;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_POSTAL")
public class Cod_postal implements Serializable {

	private static final long serialVersionUID = 2470684085717690736L;

	@Id
	private String cpostal;
	@Id
	private String city;
	@Id
	private String ccountry;

	public Cod_postal() {

	}

	public Cod_postal(String cpostal, String city, String ccountry) {
		this.cpostal = cpostal;
		this.city = city;
		this.ccountry = ccountry;
	}

	public String getCcountry() {
		return ccountry;
	}

	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}

	private String indvali;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

	public String getCpostal() {
		return cpostal;
	}

	public void setCpostal(String cpostal) {
		this.cpostal = cpostal;
	}

}
