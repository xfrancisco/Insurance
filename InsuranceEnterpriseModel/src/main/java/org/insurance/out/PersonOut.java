package org.insurance.out;

public class PersonOut {

	private long personId;
	private String ccivil;
	private String lastName;
	private String firstName;
	private String companyName;
	private String companyId;
	private AddressOut address;

	public PersonOut() {
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public String getCcivil() {
		return ccivil;
	}

	public void setCcivil(String ccivil) {
		this.ccivil = ccivil;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public AddressOut getAddress() {
		return address;
	}

	public void setAddress(AddressOut address) {
		this.address = address;
	}

}
