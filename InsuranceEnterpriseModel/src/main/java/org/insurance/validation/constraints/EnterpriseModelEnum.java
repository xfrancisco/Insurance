package org.insurance.validation.constraints;

public enum EnterpriseModelEnum {

	MIN(0, null, null),
	MAX(Integer.MAX_VALUE, null, null),
	CIVILITY(2, "COD_CIVILITY", "CCIVIL"),
	LASTNAME(128, "CLI_CLIENT", "NAME"),
	FIRSTNAME(128, "CLI_CLIENT", "FIRSTNAME"),
	COMPANYNAME(128, "CLI_CLIENT", "COMPANYNAME"),
	COMPANYID(8, "CLI_CLIENT", "COMPANYID"),
	PERSONCATEGORY(5, "COD_CATCLI", "CCATCLI"),
	PERSONID(8, "CLI_CLIENT", "NUMCLI"),
	CONTRACTID(3, "CLI_CONTRACT", "NUMCON"),
	STREET1(128, "CLI_ADDRESS", "STREET1"),
	STREET2(128, "CLI_ADDRESS", "STREET2"),
	STREET3(128, "CLI_ADDRESS", "STREET3"),
	STREET4(128, "CLI_ADDRESS", "STREET4"),
	ZIPCODE(5, "COD_POSTAL", "CPOSTAL"),
	CITY(128, "COD_POSTAL", "CITY"),
	COUNTRY(3, "COD_COUNTRY", "CCOUNTRY"),
	USERID(32, "USR_USER", "CUSER"),
	USERNAME(128, "USR_USER", "LUSER"),
	USERPROFILE(16, "USR_ROLE", "CROLE"),
	EMAIL(128, "CLI_EMAIL", "EMAIL"),
	MOBILE(16, "CLI_PHONE", "PHONENUMBER"),
	PHONE(16, "CLI_PHONE", "PHONENUMBER");

	public final int length;
	public final String table;
	public final String column;

	EnterpriseModelEnum(final int length, final String table, final String column) {
		this.length = length;
		this.table = table;
		this.column = column;
	}

	public String table() {
		return table;
	}

	public String column() {
		return column;

	}

}