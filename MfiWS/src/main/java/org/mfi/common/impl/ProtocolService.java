package org.mfi.common.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfi.common.IProtocolService;
import org.mfi.exception.MfcException;
import org.mfi.in.ProtocolIn;
import org.mfi.in.UpdateProtocolIn;
import org.mfi.out.ProtocolOut;
import org.mfi.service.check.IUserCheck;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class ProtocolService implements IProtocolService {

	@Inject
	private IUserCheck userCheck;

	@Override
	public ProtocolOut insertProtocol(String userId, ProtocolIn newProtocolIn) throws MfcException {
		userCheck.checkUser(userId);
		return null;
	}

	@Override
	public ProtocolOut updateProtocol(String userId, UpdateProtocolIn updateProtocolIn) throws MfcException {
		userCheck.checkUser(userId);
		return null;
	}

	@Override
	public ProtocolOut getProtocol(String userId, Long protocolId, String protocolName) throws MfcException {
		userCheck.checkUser(userId);
		return null;
	}

	@Override
	public List<ProtocolOut> getProtocols(String userId) throws MfcException {
		userCheck.checkUser(userId);
		return null;
	}

}
