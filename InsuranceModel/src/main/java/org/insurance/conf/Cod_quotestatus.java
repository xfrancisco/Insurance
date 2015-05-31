package org.insurance.conf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COD_QUOTESTATUS")
public class Cod_quotestatus {

	@Id
	private String cquotestatus;
	private String lquotestatus;
	private String indpending;
	private String indaccepted;
	private String indrefused;
	private String indvalidated;
	private String indaborted;
	private String indvali;

	public String getCquotestatus() {
		return cquotestatus;
	}

	public void setCquotestatus(String cquotestatus) {
		this.cquotestatus = cquotestatus;
	}

	public String getLquotestatus() {
		return lquotestatus;
	}

	public void setLquotestatus(String lquotestatus) {
		this.lquotestatus = lquotestatus;
	}

	public String getIndpending() {
		return indpending;
	}

	public void setIndpending(String indpending) {
		this.indpending = indpending;
	}

	public String getIndaccepted() {
		return indaccepted;
	}

	public void setIndaccepted(String indaccepted) {
		this.indaccepted = indaccepted;
	}

	public String getIndrefused() {
		return indrefused;
	}

	public void setIndrefused(String indrefused) {
		this.indrefused = indrefused;
	}

	public String getIndvalidated() {
		return indvalidated;
	}

	public void setIndvalidated(String indvalidated) {
		this.indvalidated = indvalidated;
	}

	public String getIndaborted() {
		return indaborted;
	}

	public void setIndaborted(String indaborted) {
		this.indaborted = indaborted;
	}

	public String getIndvali() {
		return indvali;
	}

	public void setIndvali(String indvali) {
		this.indvali = indvali;
	}

}
