package org.mfi.conf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_VERSION")
public class Cod_version {

	@Id
	private String cversion;
	private String lversion;
	private Date versionDate;

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

	public Date getVersionDate() {
		return versionDate;
	}

	public void setVersionDate(Date versionDate) {
		this.versionDate = versionDate;
	}

}
