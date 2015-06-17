package org.mfi.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_EMAIL")
public class Cod_email {

	@Id
	private String cemail;
	private String lemail;
	private String pattern;
	private String inddefault;
	private String indvali;

	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getLemail() {
		return lemail;
	}

	public void setLemail(String lemail) {
		this.lemail = lemail;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
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

}
