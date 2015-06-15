package org.mfi.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_GUARANTEE")
public class Cod_guarantee {

	@Id
	private String cguarantee;
	private String lguarantee;
	private String indvali;

	public String getCguarantee() {
		return cguarantee;
	}

	public void setCguarantee(String cguarantee) {
		this.cguarantee = cguarantee;
	}

	public String getLguarantee() {
		return lguarantee;
	}

	public void setLguarantee(String lguarantee) {
		this.lguarantee = lguarantee;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
