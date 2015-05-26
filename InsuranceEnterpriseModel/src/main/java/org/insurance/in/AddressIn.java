package org.insurance.in;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Strings;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressIn {
	@Length(max = EnterpriseModelEnum.STREET1)
	private String street1;

	@Mandatory
	@Length(max = EnterpriseModelEnum.STREET2)
	private String street2;

	@Length(max = EnterpriseModelEnum.STREET3)
	private String street3;

	@Length(max = EnterpriseModelEnum.STREET4)
	private String street4;

	@Mandatory
	@Length(max = EnterpriseModelEnum.ZIPCODE)
	private String zipCode;

	@Mandatory
	@Length(max = EnterpriseModelEnum.CITY)
	private String city;

	@Mandatory
	@Length(max = EnterpriseModelEnum.COUNTRY)
	private String country;

	public String getStreet1() {
		return Strings.nullToEmpty(street1).toUpperCase();
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return Strings.nullToEmpty(street2).toUpperCase();
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getStreet3() {
		return Strings.nullToEmpty(street3).toUpperCase();
	}

	public void setStreet3(String street3) {
		this.street3 = street3;
	}

	public String getStreet4() {
		return Strings.nullToEmpty(street4).toUpperCase();
	}

	public void setStreet4(String street4) {
		this.street4 = street4;
	}

	public String getZipCode() {
		return Strings.nullToEmpty(zipCode).toUpperCase();
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return Strings.nullToEmpty(city).toUpperCase();
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return Strings.nullToEmpty(country).toUpperCase();
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
