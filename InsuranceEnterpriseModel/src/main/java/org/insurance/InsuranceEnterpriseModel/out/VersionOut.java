package org.insurance.InsuranceEnterpriseModel.out;

public class VersionOut {
	private String databaseVersion;
	private String applicativeVersion;

	public String getDatabaseVersion() {
		return databaseVersion;
	}

	public void setDatabaseVersion(String databaseVersion) {
		this.databaseVersion = databaseVersion;
	}

	public String getApplicativeVersion() {
		return applicativeVersion;
	}

	public void setApplicativeVersion(String applicativeVersion) {
		this.applicativeVersion = applicativeVersion;
	}
}
