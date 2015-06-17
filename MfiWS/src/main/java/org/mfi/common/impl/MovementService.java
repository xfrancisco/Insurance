package org.mfi.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.mfi.common.IMovementService;
import org.mfi.data.Cli_movement;
import org.mfi.dto.movements.MovementChangeDto;
import org.mfi.dto.movements.MovementDto;
import org.mfi.exception.MfcException;
import org.mfi.exception.MovementException;
import org.mfi.exception.MovementException.ErrorCode;
import org.mfi.out.movements.MovementDetailsOut;
import org.mfi.out.movements.MovementOut;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IQuoteAndContractCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IMovementInfo;
import org.mfi.utils.mapping.MovementMapping;
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

	@Inject
	private IQuoteAndContractCheck quoteAndContractCheck;

	@Override
	public MovementDetailsOut getMovement(final String userId, final long movementId) throws MfcException {
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
	public List<MovementOut> getMovements(final String userId, final long personId, final Integer contractId, final Integer quoteId)
			throws MfcException {
		userCheck.checkUser(userId);
		personCheck.checkPerson(personId);
		if (contractId != null && quoteId != null)
			throw new MovementException(ErrorCode.ERR_BIZ_MOVEMENT_SEARCH_CONTRAT_OR_QUOTE, contractId, quoteId);
		if (contractId != null)
			quoteAndContractCheck.checkContract(personId, contractId);
		if (quoteId != null)
			quoteAndContractCheck.checkQuote(personId, contractId);

		List<MovementDto> movements = movementInfo.getMovements(personId, contractId, quoteId);
		return MovementMapping.populateMovements(movements);
	}

}
