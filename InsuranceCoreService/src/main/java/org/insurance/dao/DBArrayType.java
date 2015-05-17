package org.insurance.dao;



public enum DBArrayType {
	TYPE_TABJAVA("TABJAVA"),
	TYPE_TABCARTICLE("TABCARTICLE"),
	TYPE_TABCARTICLECPT("TABCARTICLECPT"),
	TYPE_TABMONTANT("TABMONTANT");

	private final String name;

	DBArrayType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

