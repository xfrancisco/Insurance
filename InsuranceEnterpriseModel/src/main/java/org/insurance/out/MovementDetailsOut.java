package org.insurance.out;

public class MovementDetailsOut {

	private String actOfManagementCode;
	private Long actOfManagementId;
	private Long creationDate;
	private String creationUser;
	private MovementChanges changes;

	public MovementDetailsOut() {
	}

	public String getActOfManagementCode() {
		return actOfManagementCode;
	}

	public void setActOfManagementCode(String actOfManagementCode) {
		this.actOfManagementCode = actOfManagementCode;
	}

	public Long getActOfManagementId() {
		return actOfManagementId;
	}

	public void setActOfManagementId(Long actOfManagementId) {
		this.actOfManagementId = actOfManagementId;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(String creationUser) {
		this.creationUser = creationUser;
	}

	public MovementChanges getChanges() {
		return changes;
	}

	public void setChanges(MovementChanges changes) {
		this.changes = changes;
	}

}
