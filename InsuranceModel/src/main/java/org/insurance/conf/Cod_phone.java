package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_PHONE")
public class Cod_phone {

	@Id
	private String cphone;
	private String lphone;
	private String pattern;
	private String indmobile;
	private String inddefault;
	private String indvali;

	public String getCphone() {
		return cphone;
	}

	public void setCphone(String cphone) {
		this.cphone = cphone;
	}

	public String getLphone() {
		return lphone;
	}

	public void setLphone(String lphone) {
		this.lphone = lphone;
	}

	public String getIndmobile() {
		return indmobile;
	}

	public void setIndmobile(String indmobile) {
		this.indmobile = indmobile;
	}

	public String getInddefault() {
		return inddefault;
	}

	public void setInddefault(String inddefault) {
		this.inddefault = inddefault;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
