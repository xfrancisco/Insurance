package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_MOVEMENTDET")
public class Cod_movementdet {

	@Id
	private String ccolumn;
	private String label;
	private String valuetable;
	private String valuecolumn;

	public String getCcolumn() {
		return ccolumn;
	}

	public void setCcolumn(String ccolumn) {
		this.ccolumn = ccolumn;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValuetable() {
		return valuetable;
	}

	public void setValuetable(String valuetable) {
		this.valuetable = valuetable;
	}

	public String getValuecolumn() {
		return valuecolumn;
	}

	public void setValuecolumn(String valuecolumn) {
		this.valuecolumn = valuecolumn;
	}

}
