package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USR_ROLE")
public class Usr_role {

	@Id
	private String crole;
	private String lrole;
	private String indvali;

	public String getCrole() {
		return crole;
	}

	public void setCrole(String crole) {
		this.crole = crole;
	}

	public String getLrole() {
		return lrole;
	}

	public void setLrole(String lrole) {
		this.lrole = lrole;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
