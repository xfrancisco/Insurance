package org.insurance.common;

import org.insurance.InsuranceEnterpriseModel.in.ClientIn;
import org.insurance.InsuranceEnterpriseModel.out.ClientOut;

public interface IPersonService {

	ClientOut insertClient(ClientIn clientIn);

}
