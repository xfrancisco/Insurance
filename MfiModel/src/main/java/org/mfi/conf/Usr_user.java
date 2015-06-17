package org.mfi.conf;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Strings;

@Entity
@Table(name = "USR_USER")
public class Usr_user {

	@Id
	private String cuser;
	private String luser;
	private String crole;
	private String indvali;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	public String getLuser() {
		return luser;
	}

	public void setLuser(String luser) {
		this.luser = luser;
	}

	public String getCrole() {
		return crole;
	}

	public void setCrole(String crole) {
		this.crole = crole;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

	public String getCusercre() {
		return cusercre;
	}

	public void setCusercre(String cusercre) {
		this.cusercre = cusercre;
	}

	public String getCusermod() {
		return cusermod;
	}

	public void setCusermod(String cusermod) {
		this.cusermod = cusermod;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModifDate() {
		return modifDate;
	}

	public void setModifDate(Date modifDate) {
		this.modifDate = modifDate;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		Usr_user oldUser = (Usr_user) o;
		if (!Strings.nullToEmpty(this.crole).equals(Strings.nullToEmpty(oldUser.getCrole()))) {
			return false;
		}

		if (!Strings.nullToEmpty(this.cuser).equals(Strings.nullToEmpty(oldUser.getCuser()))) {
			return false;
		}

		if (!Strings.nullToEmpty(this.luser).equals(Strings.nullToEmpty(oldUser.getLuser()))) {
			return false;
		}

		if (!Strings.nullToEmpty(this.indvali).equals(Strings.nullToEmpty(oldUser.getIndvali()))) {
			return false;
		}

		return true;
	}

}
