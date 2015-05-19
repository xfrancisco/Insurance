package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_BRANCH")
public class Cod_branch {

	@Id
	private String cbranch;
	private String lbranch;
	private String indvali;

	public String getCbranch() {
		return cbranch;
	}

	public void setCbranch(String cbranch) {
		this.cbranch = cbranch;
	}

	public String getLbranch() {
		return lbranch;
	}

	public void setLbranch(String lbranch) {
		this.lbranch = lbranch;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
