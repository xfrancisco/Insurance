package org.mfi.utils.mapping;

import java.util.ArrayList;
import java.util.List;

import org.mfi.data.Cli_movement;
import org.mfi.dto.movements.MovementChangeDto;
import org.mfi.dto.movements.MovementDetailDetailsDto;
import org.mfi.dto.movements.MovementDetailDto;
import org.mfi.dto.movements.MovementDto;
import org.mfi.out.movements.MovementChangeDetail;
import org.mfi.out.movements.MovementChangeValues;
import org.mfi.out.movements.MovementChanges;
import org.mfi.out.movements.MovementDetailsOut;
import org.mfi.out.movements.MovementOut;
import org.mfi.util.DateUtils;

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

	public static List<MovementOut> populateMovements(List<MovementDto> movements) {
		List<MovementOut> result = new ArrayList<MovementOut>();
		for (MovementDto movementDto : movements) {
			MovementOut tmp = new MovementOut();
			tmp.setCode(movementDto.getCmovement());
			tmp.setId(movementDto.getNummovement());
			tmp.setLabel(movementDto.getLmovement());
			tmp.setUser(movementDto.getCusermovement());
			tmp.setDate(DateUtils.formatDate(movementDto.getMovementDate()));
			result.add(tmp);
		}
		return result;
	}
}
