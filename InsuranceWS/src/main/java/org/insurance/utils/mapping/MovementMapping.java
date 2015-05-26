package org.insurance.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.insurance.data.Cli_movement;
import org.insurance.dto.MovementChangeDto;
import org.insurance.dto.MovementDetailDetailsDto;
import org.insurance.dto.MovementDetailDto;
import org.insurance.out.MovementChangeDetail;
import org.insurance.out.MovementChangeValues;
import org.insurance.out.MovementChanges;
import org.insurance.out.MovementDetailsOut;

public class MovementMapping {
	public static MovementDetailsOut populateMovementChanges(final Cli_movement m, MovementChangeDto movementChange) {

		MovementDetailsOut act = new MovementDetailsOut();

		act.setActOfManagementId(m.getNummovement());
		act.setActOfManagementCode(m.getCmovement());
		act.setCreationDate((m.getMovementDate() != null) ? m.getMovementDate().getTime() : null);
		act.setCreationUser(m.getCusermovement());
		act.setChanges(populateActOfManagementChanges(movementChange));

		return act;
	}

	public static MovementChanges populateActOfManagementChanges(MovementChangeDto mvtDetails) {

		List<MovementDetailDto> oldDetails = mvtDetails.getOldValues();
		List<MovementDetailDto> newDetails = mvtDetails.getNewValues();

		List<MovementChangeValues> oldResult = new ArrayList<MovementChangeValues>();
		List<MovementChangeValues> newResult = new ArrayList<MovementChangeValues>();
		if (oldDetails != null)
			oldResult = populateValues(oldDetails);
		if (newDetails != null)
			newResult = populateValues(newDetails);
		return new MovementChanges(newResult, oldResult);
	}

	private static List<MovementChangeValues> populateValues(List<MovementDetailDto> details) {
		List<MovementChangeValues> result = new ArrayList<MovementChangeValues>();
		for (MovementDetailDto movementDetail : details) {
			MovementChangeValues value = new MovementChangeValues();
			value.setCode(movementDetail.getCode());
			value.setLabel(movementDetail.getLabel());
			value.setValue(movementDetail.getValue());
			value.setValueLabel(movementDetail.getValueLabel());
			value.setFurtherDetails(populateDetails(movementDetail.getFurtherDetails()));
			result.add(value);
		}
		return result;
	}

	private static List<MovementChangeDetail> populateDetails(List<MovementDetailDetailsDto> furtherDetails) {
		List<MovementChangeDetail> result = new ArrayList<MovementChangeDetail>();
		if (furtherDetails != null) {
			for (MovementDetailDetailsDto movementDetailDetails : furtherDetails) {
				MovementChangeDetail detail = new MovementChangeDetail();
				detail.setDetailCode(movementDetailDetails.getDetailCode());
				detail.setDetailLabel(movementDetailDetails.getDetailLabel());
				detail.setDetailValue(movementDetailDetails.getDetailValue());
				detail.setDetailValueLabel(movementDetailDetails.getDetailValueLabel());
				result.add(detail);
			}
		}
		return result;
	}
}
