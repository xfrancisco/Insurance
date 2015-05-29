package org.insurance.dto.movements;

import java.util.ArrayList;
import java.util.List;

public class MovementChangeDto {
	private List<MovementDetailDto> newValues;
	private List<MovementDetailDto> oldValues;

	public List<MovementDetailDto> getNewValues() {
		return newValues;
	}

	public void setNewValues(List<MovementDetailDto> newValues) {
		this.newValues = newValues;
	}

	public void setNewValues(MovementDetailDto detail) {
		if (newValues == null)
			newValues = new ArrayList<MovementDetailDto>();
		this.newValues.add(detail);
	}

	public List<MovementDetailDto> getOldValues() {
		return oldValues;
	}

	public void setOldValues(List<MovementDetailDto> oldValues) {
		this.oldValues = oldValues;
	}

	public void setOldValues(MovementDetailDto detail) {
		if (oldValues == null)
			oldValues = new ArrayList<MovementDetailDto>();
		this.oldValues.add(detail);
	}
}
