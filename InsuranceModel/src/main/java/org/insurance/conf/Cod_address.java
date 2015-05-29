package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_ADDRESS")
public class Cod_address {

	@Id
	private String caddress;
	private String laddress;
	private String inddefault;
	private String indvali;

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getLaddress() {
		return laddress;
	}

	public void setLaddress(String laddress) {
		this.laddress = laddress;
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
