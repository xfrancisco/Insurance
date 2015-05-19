package org.insurance.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private String zipcode;
	private String ccountry;

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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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

}
