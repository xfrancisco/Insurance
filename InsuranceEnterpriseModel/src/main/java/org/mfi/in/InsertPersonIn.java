package org.mfi.in;

import java.util.List;

import javax.validation.Valid;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;

import com.google.common.base.Strings;

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
		return Strings.nullToEmpty(companyId).toUpperCase().trim();
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLastName() {
		return Strings.nullToEmpty(lastName).toUpperCase().trim();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return Strings.nullToEmpty(firstName).toUpperCase().trim();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCompanyName() {
		return Strings.nullToEmpty(companyName).toUpperCase().trim();
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
		return Strings.nullToEmpty(civility).toUpperCase().trim();
	}

	public void setCivility(String civility) {
		this.civility = civility;
	}

	public String getEmail() {
		return Strings.nullToEmpty(email).toUpperCase().trim();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return Strings.nullToEmpty(mobile).toUpperCase().trim();
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return Strings.nullToEmpty(phone).toUpperCase().trim();
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
