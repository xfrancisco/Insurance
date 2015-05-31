package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_TABLE")
public class Cod_table {

	@Id
	private String ctable;
	private String ltable;
	private String indvali;
	private String tablename;
	private String tablecode;
	private String tablelabel;

	public String getCtable() {
		return ctable;
	}

	public void setCtable(String ctable) {
		this.ctable = ctable;
	}

	public String getLtable() {
		return ltable;
	}

	public void setLtable(String ltable) {
		this.ltable = ltable;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getTablecode() {
		return tablecode;
	}

	public void setTablecode(String tablecode) {
		this.tablecode = tablecode;
	}

	public String getTablelabel() {
		return tablelabel;
	}

	public void setTablelabel(String tablelabel) {
		this.tablelabel = tablelabel;
	}

}
