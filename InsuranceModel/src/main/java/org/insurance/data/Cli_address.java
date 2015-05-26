package org.insurance.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.common.base.Strings;

@Entity
@Table(name = "CLI_ADDRESS")
public class Cli_address implements Serializable {

	private static final long serialVersionUID = 2065521207003803792L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMADDRESS_SEQ")
	@SequenceGenerator(name = "NUMADDRESS_SEQ", sequenceName = "NUMADDRESS_SEQ")
	@Id
	private Long numaddress;
	private Long numcli;
	private String street1;
	private String street2;
	private String street3;
	private String street4;
	private String city;
	private String cpostal;
	private String ccountry;
	private String cusercre;
	private String cusermod;
	private Date creationDate;
	private Date modifDate;

	public Long getNumaddress() {
		return numaddress;
	}

	public void setNumaddress(Long numaddress) {
		this.numaddress = numaddress;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getStreet3() {
		return street3;
	}

	public void setStreet3(String street3) {
		this.street3 = street3;
	}

	public String getStreet4() {
		return street4;
	}

	public void setStreet4(String street4) {
		this.street4 = street4;
	}

	public String getCcountry() {
		return ccountry;
	}

	public void setCcountry(String ccountry) {
		this.ccountry = ccountry;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCpostal() {
		return cpostal;
	}

	public void setCpostal(String cpostal) {
		this.cpostal = cpostal;
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

	public boolean hasChanged(Cli_address oldAddress) {
		if (!Strings.nullToEmpty(this.street1).equals(Strings.nullToEmpty(oldAddress.getStreet1()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.street2).equals(Strings.nullToEmpty(oldAddress.getStreet2()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.street3).equals(Strings.nullToEmpty(oldAddress.getStreet3()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.street4).equals(Strings.nullToEmpty(oldAddress.getStreet4()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.city).equals(Strings.nullToEmpty(oldAddress.getCity()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.cpostal).equals(Strings.nullToEmpty(oldAddress.getCpostal()))) {
			return true;
		}

		if (!Strings.nullToEmpty(this.ccountry).equals(Strings.nullToEmpty(oldAddress.getCcountry()))) {
			return true;
		}
		return false;
	}

}
