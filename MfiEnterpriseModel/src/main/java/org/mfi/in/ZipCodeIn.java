package org.mfi.in;

import org.mfi.validation.constraints.Mandatory;

import com.google.common.base.Strings;

public class ZipCodeIn {
	@Mandatory
	private String zipCode;
	private String country;
	@Mandatory
	private String city;

	public String getZipCode() {
		return Strings.nullToEmpty(zipCode).toUpperCase().trim();
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return Strings.nullToEmpty(country).toUpperCase().trim();
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return Strings.nullToEmpty(city).toUpperCase().trim();
	}

	public void setCity(String city) {
		this.city = city;
	}

}
