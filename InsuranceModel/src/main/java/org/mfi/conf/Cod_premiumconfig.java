package org.mfi.conf;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_PREMIUMCONFIG")
public class Cod_premiumconfig implements Serializable {

	private static final long serialVersionUID = 2331801292694426647L;

	@Id
	private String cbranch;
	@Id
	private String ccategory;
	@Id
	private String csection;
	@Id
	private String cguarantee;
	@Id
	private String cpremium;

	private String indvali;

	public String getCbranch() {
		return cbranch;
	}

	public void setCbranch(String cbranch) {
		this.cbranch = cbranch;
	}

	public String getCcategory() {
		return ccategory;
	}

	public void setCcategory(String ccategory) {
		this.ccategory = ccategory;
	}

	public String getCsection() {
		return csection;
	}

	public void setCsection(String csection) {
		this.csection = csection;
	}

	public String getCguarantee() {
		return cguarantee;
	}

	public void setCguarantee(String cguarantee) {
		this.cguarantee = cguarantee;
	}

	public String getCpremium() {
		return cpremium;
	}

	public void setCpremium(String cpremium) {
		this.cpremium = cpremium;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
