package org.insurance.conf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COD_VERSION")
public class Cod_version {

	@Id
	private String cversion;
	private String lversion;
	private Date dateversion;
	
	public String getCversion() {
		return cversion;
	}
	public void setCversion(String cversion) {
		this.cversion = cversion;
	}
	public String getLversion() {
		return lversion;
	}
	public void setLversion(String lversion) {
		this.lversion = lversion;
	}
	public Date getDateversion() {
		return dateversion;
	}
	public void setDateversion(Date dateversion) {
		this.dateversion = dateversion;
	}

	
	
}
