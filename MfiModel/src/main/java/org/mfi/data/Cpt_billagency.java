package org.mfi.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CPT_BILLAGENCY")
public class Cpt_billagency implements Serializable {

	private static final long serialVersionUID = 3998497188437889025L;

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NUMBILLAGENCY_SEQ")
	@SequenceGenerator(name = "NUMBILLAGENCY_SEQ", sequenceName = "NUMBILLAGENCY_SEQ", allocationSize = 1, initialValue = 1)
	@Id
	private Long numbillagency;
	private Long numbill;
	private Long numcli;
	private Long numbillleader;
	private Long numbillcoinsurance;

	public Long getNumbillagency() {
		return numbillagency;
	}

	public void setNumbillagency(Long numbillagency) {
		this.numbillagency = numbillagency;
	}

	public Long getNumbill() {
		return numbill;
	}

	public void setNumbill(Long numbill) {
		this.numbill = numbill;
	}

	public Long getNumcli() {
		return numcli;
	}

	public void setNumcli(Long numcli) {
		this.numcli = numcli;
	}

	public Long getNumbillleader() {
		return numbillleader;
	}

	public void setNumbillleader(Long numbillleader) {
		this.numbillleader = numbillleader;
	}

	public Long getNumbillcoinsurance() {
		return numbillcoinsurance;
	}

	public void setNumbillcoinsurance(Long numbillcoinsurance) {
		this.numbillcoinsurance = numbillcoinsurance;
	}

}
