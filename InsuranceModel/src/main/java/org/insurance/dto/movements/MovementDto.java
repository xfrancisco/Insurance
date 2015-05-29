package org.insurance.dto.movements;

import java.util.Date;

public class MovementDto {

	private String cmovement;
	private Long nummovement;
	private String lmovement;
	private Date movementDate;
	private String cusermovement;

	public String getCmovement() {
		return cmovement;
	}

	public void setCmovement(String cmovement) {
		this.cmovement = cmovement;
	}

	public Long getNummovement() {
		return nummovement;
	}

	public void setNummovement(Long nummovement) {
		this.nummovement = nummovement;
	}

	public String getLmovement() {
		return lmovement;
	}

	public void setLmovement(String lmovement) {
		this.lmovement = lmovement;
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	public String getCusermovement() {
		return cusermovement;
	}

	public void setCusermovement(String cusermovement) {
		this.cusermovement = cusermovement;
	}

}
