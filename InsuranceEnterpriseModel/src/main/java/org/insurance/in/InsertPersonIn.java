package org.insurance.in;

import javax.validation.Valid;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Strings;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsertPersonIn {
	@Mandatory
	@Length(min = EnterpriseModelEnum.CIVILITY, max = EnterpriseModelEnum.CIVILITY)
	private String civility;

	@Length(max = EnterpriseModelEnum.LASTNAME)
	private String lastName;

	@Length(max = EnterpriseModelEnum.FIRSTNAME)
	private String firstName;

	@Length(max = EnterpriseModelEnum.COMPANYNAME)
	private String companyName;

	@Length(max = EnterpriseModelEnum.COMPANYID)
	private String companyId;

	@Valid
	private AddressIn address;

	public String getCompanyId() {
		return Strings.nullToEmpty(companyId).toUpperCase();
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLastName() {
		return Strings.nullToEmpty(lastName).toUpperCase();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return Strings.nullToEmpty(firstName).toUpperCase();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCompanyName() {
		return Strings.nullToEmpty(companyName).toUpperCase();
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public AddressIn getAddress() {
		return address;
	}

	public void setAddress(AddressIn address) {
		this.address = address;
	}

	public String getCivility() {
		return Strings.nullToEmpty(civility).toUpperCase();
	}

	public void setCivility(String civility) {
		this.civility = civility;
	}

}
