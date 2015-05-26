package org.insurance.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CLI_MOVEMENTDET")
public class Cli_movementdet {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMMOVEMENTDET_SEQ")
	@SequenceGenerator(name = "NUMMOVEMENTDET_SEQ", sequenceName = "NUMMOVEMENTDET_SEQ")
	@Id
	private Long nummovementdet;
	private Long nummovement;
	private String ccolumn;
	private String oldvalue;
	private String newvalue;

	public Long getNummovementdet() {
		return nummovementdet;
	}

	public void setNummovementdet(Long nummovementdet) {
		this.nummovementdet = nummovementdet;
	}

	public Long getNummovement() {
		return nummovement;
	}

	public void setNummovement(Long nummovement) {
		this.nummovement = nummovement;
	}

	public String getCcolumn() {
		return ccolumn;
	}

	public void setCcolumn(String ccolumn) {
		this.ccolumn = ccolumn;
	}

	public String getOldvalue() {
		return oldvalue;
	}

	public void setOldvalue(String oldvalue) {
		this.oldvalue = oldvalue;
	}

	public String getNewvalue() {
		return newvalue;
	}

	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}

}
