package org.insurance.InsuranceEnterpriseModel.in;

public class PersonIn {
	private String ccivil;
	private String name;
	private String firstName;
	private String companyName;
	private String companyId;
	private AddressIn address;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCompanyName() {
		return companyName;
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

	public String getCcivil() {
		return ccivil;
	}

	public void setCcivil(String ccivil) {
		this.ccivil = ccivil;
	}

}
