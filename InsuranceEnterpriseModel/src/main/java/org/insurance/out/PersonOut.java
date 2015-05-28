package org.insurance.out;

import java.util.List;

public class PersonOut {

	private long personId;
	private String civility;
	private String lastName;
	private String firstName;
	private String companyName;
	private String companyId;
	private AddressOut address;
	private List<PersonCategoryOut> categories;

	public PersonOut() {
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
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

	public List<PersonCategoryOut> getCategories() {
		return categories;
	}

	public void setCategories(List<PersonCategoryOut> categories) {
		this.categories = categories;
	}

	public String getCivility() {
		return civility;
	}

	public void setCivility(String civility) {
		this.civility = civility;
	}

}
