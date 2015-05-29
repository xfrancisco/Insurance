package org.insurance.service.info;

import java.util.List;

import org.insurance.data.Cli_movement;
import org.insurance.dto.movements.MovementChangeDto;
import org.insurance.dto.movements.MovementDto;

public interface IMovementInfo {

	Cli_movement getMovement(long nummovement);

	MovementChangeDto getMovementDetails(long nummovement);

	List<MovementDto> getMovements(long numcli, Integer numcon);
}
