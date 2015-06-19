package org.mfi.common.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mfi.common.IBillService;
import org.mfi.data.Cpt_fee;
import org.mfi.dto.bill.BillDto;
import org.mfi.dto.bill.PremiumBillDto;
import org.mfi.exception.MfcException;
import org.mfi.out.billing.BillFeeOut;
import org.mfi.out.billing.BillOut;
import org.mfi.out.billing.GlobalBillOut;
import org.mfi.out.billing.PremiumBillOut;
import org.mfi.service.check.IPersonCheck;
import org.mfi.service.check.IQuoteAndContractCheck;
import org.mfi.service.check.IUserCheck;
import org.mfi.service.info.IBillInfo;
import org.mfi.util.DateUtils;
import org.mfi.util.MappingUtils;
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
	public GlobalBillOut getBills(final String userId, final long personId, final int contractId) throws MfcException {
		userCheck.checkUser(userId);
		personCheck.checkClient(personId);
		quoteAndContractCheck.checkContract(personId, contractId);
		List<BillDto> billDtos = billInfo.getBills(personId, contractId);
		return populateBillOut(billDtos);
	}

	private GlobalBillOut populateBillOut(final List<BillDto> billDtos) {
		GlobalBillOut out = new GlobalBillOut();
		List<BillOut> result = new ArrayList<BillOut>();

		BigDecimal grossTotalAmount = new BigDecimal(0);
		BigDecimal brokerTotalAmount = new BigDecimal(0);
		BigDecimal netCompanyTotalAmount = new BigDecimal(0);

		for (BillDto billDto : billDtos) {
			BillOut billOut = new BillOut();
			billOut.setBrokerTotalAmount(billDto.getBrokerTotalAmount());
			billOut.setGrossTotalAmount(billDto.getGrossTotalAmount());
			billOut.setNetCompanyTotalAmount(billDto.getNetCompanyTotalAmount());
			billOut.setPremiumBills(populatePremiumBillOutList(billDto.getPremiumBills()));
			billOut.setStartDate(DateUtils.formatDate(billDto.getStartDate()));
			billOut.setEndDate(DateUtils.formatDate(billDto.getEndDate()));
			billOut.setFee(populateBillFeeOut(billDto.getFees()));
			result.add(billOut);

			grossTotalAmount = grossTotalAmount.add(billOut.getGrossTotalAmount());
			brokerTotalAmount = brokerTotalAmount.add(billOut.getBrokerTotalAmount());
			netCompanyTotalAmount = netCompanyTotalAmount.add(billOut.getNetCompanyTotalAmount());
		}
		out.setGrossTotalAmount(grossTotalAmount);
		out.setBrokerTotalAmount(brokerTotalAmount);
		out.setNetCompanyTotalAmount(netCompanyTotalAmount);
		out.setBills(result);
		return out;
	}

	private List<BillFeeOut> populateBillFeeOut(List<Cpt_fee> fees) {
		List<BillFeeOut> result = new ArrayList<BillFeeOut>();
		if (fees != null) {
			for (Cpt_fee cptFee : fees) {
				BillFeeOut tmp = new BillFeeOut();
				tmp.setAmount(MappingUtils.toString(cptFee.getAmount()));
				tmp.setFeeId(cptFee.getCfee());
			}
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
