package org.mfi.common;

import java.util.List;

import org.mfi.exception.MfcException;
import org.mfi.in.ProtocolIn;
import org.mfi.in.UpdateProtocolIn;
import org.mfi.out.ProtocolOut;

public interface IProtocolService {

	ProtocolOut insertProtocol(String userId, ProtocolIn newProtocolIn) throws MfcException;

	ProtocolOut updateProtocol(String userId, UpdateProtocolIn updateProtocolIn) throws MfcException;

	ProtocolOut getProtocol(String userId, Long protocolId, String protocolName) throws MfcException;

	List<ProtocolOut> getProtocols(String userId) throws MfcException;

}
