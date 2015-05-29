package org.insurance.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_MOVEMENT")
public class Cli_movement {
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMMOVEMENT_SEQ")
	@SequenceGenerator(name = "NUMMOVEMENT_SEQ", sequenceName = "NUMMOVEMENT_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long nummovement;
	private Long numcli;
	private Integer numcon;
	private String cmovement;
	private String cusermovement;
	private Date movementDate;

	public Long getNummovement() {
		return nummovement;
	}

	public void setNummovement(Long nummovement) {
		this.nummovement = nummovement;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public Integer getNumcon() {
		return numcon;
	}

	public void setNumcon(Integer numcon) {
		this.numcon = numcon;
	}

	public String getCmovement() {
		return cmovement;
	}

	public void setCmovement(String cmovement) {
		this.cmovement = cmovement;
	}

	public String getCusermovement() {
		return cusermovement;
	}

	public void setCusermovement(String cusermovement) {
		this.cusermovement = cusermovement;
	}

	public Date getMovementDate() {
		return movementDate;
	}

	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

}
