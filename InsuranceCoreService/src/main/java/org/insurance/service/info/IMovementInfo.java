package org.insurance.service.info;

import org.insurance.data.Cli_movement;
import org.insurance.dto.MovementChangeDto;

public interface IMovementInfo {

	Cli_movement getMovement(long nummovement);

	MovementChangeDto getMovementDetails(long nummovement);
}
