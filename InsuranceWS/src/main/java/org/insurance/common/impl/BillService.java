package org.insurance.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.insurance.common.IBillService;
import org.insurance.dto.bill.BillDto;
import org.insurance.dto.bill.PremiumBillDto;
import org.insurance.exception.InsuranceException;
import org.insurance.out.BillOut;
import org.insurance.out.PremiumBillOut;
import org.insurance.service.check.IPersonCheck;
import org.insurance.service.check.IQuoteAndContractCheck;
import org.insurance.service.check.IUserCheck;
import org.insurance.service.info.IBillInfo;
import org.insurance.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class BillService implements IBillService {

	@Inject
	private IUserCheck userCheck;

	@Inject
	private IPersonCheck personCheck;

	@Inject
	private IQuoteAndContractCheck quoteAndContractCheck;

	@Inject
	private IBillInfo billInfo;

	@Override
	public List<BillOut> getBills(final String userId, final long personId, final int contractId) throws InsuranceException {
		userCheck.checkUser(userId);
		personCheck.checkClient(personId);
		quoteAndContractCheck.checkContract(personId, contractId);
		List<BillDto> billDtos = billInfo.getBills(personId, contractId);
		return populateBillOut(billDtos);
	}

	private List<BillOut> populateBillOut(final List<BillDto> billDtos) {
		List<BillOut> result = new ArrayList<BillOut>();
		for (BillDto billDto : billDtos) {
			BillOut billOut = new BillOut();
			billOut.setBrokerTotalAmount(billDto.getBrokerTotalAmount());
			billOut.setGrossTotalAmount(billDto.getGrossTotalAmount());
			billOut.setNetCompanyTotalAmount(billDto.getNetCompanyTotalAmount());
			billOut.setPremiumBills(populatePremiumBillOutList(billDto.getPremiumBills()));
			billOut.setStartDate(DateUtils.formatDate(billDto.getStartDate()));
			billOut.setEndDate(DateUtils.formatDate(billDto.getEndDate()));
			result.add(billOut);
		}

		return result;
	}

	private List<PremiumBillOut> populatePremiumBillOutList(List<PremiumBillDto> premiumBills) {
		List<PremiumBillOut> result = new ArrayList<PremiumBillOut>();
		for (PremiumBillDto premiumBillDto : premiumBills) {
			PremiumBillOut tmp = new PremiumBillOut();
			tmp.setBrokerAmount(premiumBillDto.getBrokerAmount());
			tmp.setBrokerRate(premiumBillDto.getBrokerRate());
			tmp.setGrossPremiumAmount(premiumBillDto.getGrossPremiumAmount());
			tmp.setNetCompanyAmount(premiumBillDto.getNetCompanyAmount());
			tmp.setNetPremiumAmount(premiumBillDto.getNetPremiumAmount());
			tmp.setTaxAmount(premiumBillDto.getTaxAmount());
			tmp.setSectionId(premiumBillDto.getCsection());
			tmp.setGuaranteeId(premiumBillDto.getCguarantee());
			tmp.setPremiumId(premiumBillDto.getCpremium());
			tmp.setTaxRate(premiumBillDto.getCodtax().getTaxvalue());
			result.add(tmp);
		}
		return result;
	}

}
