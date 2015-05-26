package org.insurance.out;

import java.util.List;

public class MovementChanges {

	private List<MovementChangeValues> newValues;
	private List<MovementChangeValues> oldValues;

	public MovementChanges() {
		super();
	}

	public MovementChanges(List<MovementChangeValues> newValues, List<MovementChangeValues> oldValues) {
		this.newValues = newValues;
		this.oldValues = oldValues;
	}

	public List<MovementChangeValues> getNewValues() {
		return newValues;
	}

	public void setNewValues(List<MovementChangeValues> newValues) {
		this.newValues = newValues;
	}

	public List<MovementChangeValues> getOldValues() {
		return oldValues;
	}

	public void setOldValues(List<MovementChangeValues> oldValues) {
		this.oldValues = oldValues;
	}
}
