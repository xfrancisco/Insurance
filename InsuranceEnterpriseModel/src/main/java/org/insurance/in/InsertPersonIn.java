package org.insurance.in;

import java.util.List;

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

	@Length(max = EnterpriseModelEnum.EMAIL)
	private String email;

	@Length(max = EnterpriseModelEnum.MOBILE)
	private String mobile;

	@Length(max = EnterpriseModelEnum.PHONE)
	private String phone;

	@Valid
	@Mandatory
	private List<ClientCategoryIn> categories;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<ClientCategoryIn> getCategories() {
		return categories;
	}

	public void setCategories(List<ClientCategoryIn> categories) {
		this.categories = categories;
	}

}
