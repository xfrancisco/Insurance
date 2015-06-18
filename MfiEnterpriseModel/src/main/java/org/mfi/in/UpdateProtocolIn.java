package org.mfi.in;

import org.mfi.validation.constraints.EnterpriseModelEnum;
import org.mfi.validation.constraints.Length;
import org.mfi.validation.constraints.Mandatory;

public class UpdateProtocolIn extends ProtocolIn {

	@Mandatory
	@Length(max = EnterpriseModelEnum.PROTOCOLID)
	private long protocolId;

	public long getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(long protocolId) {
		this.protocolId = protocolId;
	}

}