package org.mfi.service.info;

import java.util.List;

import org.mfi.data.Cli_movement;
import org.mfi.dto.movements.MovementChangeDto;
import org.mfi.dto.movements.MovementDto;

public interface IMovementInfo {

	Cli_movement getMovement(long nummovement);

	MovementChangeDto getMovementDetails(long nummovement);

	List<MovementDto> getMovements(long numcli, Integer numcon, Integer numquote);
}
