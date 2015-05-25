package org.insurance.in;

import javax.validation.Valid;

import org.insurance.validation.constraints.EnterpriseModelEnum;
import org.insurance.validation.constraints.Length;
import org.insurance.validation.constraints.Mandatory;

import com.google.common.base.Strings;

public class InsertPersonIn {
	@Mandatory
	@Length(min = EnterpriseModelEnum.CIVILITY, max = EnterpriseModelEnum.CIVILITY)
	private String civility;
	@Length(max = EnterpriseModelEnum.NAME)
	private String name;
	@Length(max = EnterpriseModelEnum.FIRSTNAME)
	private String firstName;
	@Length(max = EnterpriseModelEnum.COMPANYNAME)
	private String companyName;
	@Length(max = EnterpriseModelEnum.COMPANYID)
	private String companyId;
	@Length(max = EnterpriseModelEnum.PERSONCATEGORY)
	private String personCategory;
	@Valid
	private AddressIn address;

	public String getCompanyId() {
		return Strings.nullToEmpty(companyId).toUpperCase();
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return Strings.nullToEmpty(name).toUpperCase();
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPersonCategory() {
		return Strings.nullToEmpty(personCategory).toUpperCase();
	}

	public void setPersonCategory(String personCategory) {
		this.personCategory = personCategory;
	}

}
