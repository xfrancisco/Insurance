package org.insurance.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.insurance.common.IMovementService;
import org.insurance.data.Cli_movement;
import org.insurance.dto.movements.MovementChangeDto;
import org.insurance.dto.movements.MovementDto;
import org.insurance.exception.InsuranceException;
import org.insurance.exception.MovementException;
import org.insurance.exception.MovementException.ErrorCode;
import org.insurance.out.movements.MovementDetailsOut;
import org.insurance.out.movements.MovementOut;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IMovementInfo;
import org.insurance.utils.mapping.MovementMapping;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class MovementService implements IMovementService {

	static final Logger logger = Logger.getLogger(MovementService.class);

	@Inject
	private IMovementInfo movementInfo;

	@Inject
	private IUserCheck userCheck;

	@Inject
	private IPersonCheck personCheck;

	@Override
	public MovementDetailsOut getMovement(String userId, long movementId) throws InsuranceException {
		MovementDetailsOut details = null;
		userCheck.checkUser(userId);
		Cli_movement cliMovement = movementInfo.getMovement(movementId);
		if (cliMovement == null)
			throw new MovementException(ErrorCode.ERR_BIZ_MOVEMENT_UNKNOWN_MOVEMENT, movementId);
		MovementChangeDto movementChange = movementInfo.getMovementDetails(movementId);
		details = MovementMapping.populateMovementChanges(cliMovement, movementChange);
		return details;
	}

	@Override
	public List<MovementOut> getMovements(String userId, long personId, Integer contractId) throws InsuranceException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		//TODO XFR Contrôle du contrat
		List<MovementDto> movements = movementInfo.getMovements(personId, contractId);
		return MovementMapping.populateMovements(movements);
	}

}
