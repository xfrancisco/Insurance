package org.mfi.service.info.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_tax;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cpt_guarbroker;
import org.mfi.data.Cpt_guardispatch;
import org.mfi.data.Cpt_guarplacement;
import org.mfi.data.Cpt_leadingfee;
import org.mfi.dto.bill.BillDto;
import org.mfi.dto.bill.CoinsurerBillDto;
import org.mfi.dto.bill.PremiumBillDto;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IBillInfo;
import org.mfi.service.info.IContractPremiumInfo;
import org.mfi.service.info.IPersonInfo;
import org.mfi.service.info.IPremiumInfo;
import org.mfi.service.info.IQuoteAndContractInfo;
import org.mfi.util.DateUtils;
import org.mfi.util.DateUtils.TimePeriod;
import org.mfi.util.MathUtils;
import org.springframework.stereotype.Service;

@Service
public class BillInfo extends ServiceCore implements IBillInfo {
	@Inject
	private IContractPremiumInfo contractPremiumInfo;

	@Inject
	private IQuoteAndContractInfo quoteAndContractInfo;

	@Inject
	private IPremiumInfo premiumInfo;

	@Inject
	private IPersonInfo personInfo;

	private static BigDecimal ZERO = new BigDecimal(0);

	@Override
	public List<BillDto> getBills(final long numcli, final int numcon) {

		List<BillDto> result = new ArrayList<BillDto>();
		Cli_contract cliContract = quoteAndContractInfo.getContract(numcli, numcon);
		long numclibroker = cliContract.getNumclibroker();
		long numclileader = cliContract.getNumclileader();
		Long numcliagency = null;
		boolean isLeaderAnAgency = personInfo.isAgency(numclileader);
		if (isLeaderAnAgency)
			numcliagency = numclileader;

		List<Cli_guarantee> guarantees = contractPremiumInfo.getGuarantees(numcli, numcon);

		Cod_frequency codFrequency = quoteAndContractInfo.getFrequency(cliContract.getCfrequency());

		boolean isLastDayOfYearTreated = false;
		Date contractStartDate = cliContract.getStartval();
		Date contractEndDate = cliContract.getEndval();
		Date today = dbHelper.getToday();
		Date lastDayOfYear = DateUtils.getLastDayOfTheYear(today);
		Date nextDate = null;
		BigDecimal nbDaysOfContract = new BigDecimal(Days.daysBetween(new LocalDate(contractStartDate), new LocalDate(contractEndDate)).getDays());
		Date lastDate = contractStartDate;
		boolean caca = false;
		while (!contractEndDate.equals(nextDate)) {

			nextDate = DateUtils.addToDate(lastDate, codFrequency.getNbmonths(), TimePeriod.MONTH);
			nextDate = DateUtils.getFirstDayOfTheMonth(nextDate);
			nextDate = DateUtils.addToDate(nextDate, -1, TimePeriod.DAY);

			if (!isLastDayOfYearTreated && nextDate.after(lastDayOfYear)) {
				nextDate = lastDayOfYear;
				isLastDayOfYearTreated = true;
			}

			if (nextDate.after(contractEndDate)) {
				nextDate = contractEndDate;
			}
			BigDecimal nbDaysInCurrentBill = null;
			if (caca)
				nbDaysInCurrentBill = new BigDecimal(Days.daysBetween(new LocalDate(lastDate), new LocalDate(nextDate)).getDays())
						.add(new BigDecimal(1));
			else {
				nbDaysInCurrentBill = new BigDecimal(Days.daysBetween(new LocalDate(lastDate), new LocalDate(nextDate)).getDays());
				caca = true;
			}

			BillDto billDto = new BillDto();
			List<PremiumBillDto> premiumBills = new ArrayList<PremiumBillDto>();
			BigDecimal grossTotalAmount = ZERO;
			BigDecimal brokerTotalAmount = ZERO;
			BigDecimal netCompanyTotalAmount = ZERO;

			BigDecimal netLeaderAmount = ZERO;
			BigDecimal grossLeaderAmount = ZERO;

			for (Cli_guarantee cliGuarantee : guarantees) {
				PremiumBillDto premiumBillDto = new PremiumBillDto();
				long numguarantee = cliGuarantee.getNumguarantee();
				premiumBillDto.setCsection(cliGuarantee.getCsection());
				premiumBillDto.setCguarantee(cliGuarantee.getCguarantee());
				premiumBillDto.setCpremium(cliGuarantee.getCpremium());

				BigDecimal premiumAmount = cliGuarantee.getPremiumamount();
				BigDecimal dailyPremiumAmount = premiumAmount.divide(nbDaysOfContract, 2, RoundingMode.HALF_UP);
				BigDecimal currentBillPremiumAmount = dailyPremiumAmount.multiply(nbDaysInCurrentBill);

				Cod_tax codTax = premiumInfo.getTaxByPremium(cliGuarantee.getCpremium());
				premiumBillDto.setCodtax(codTax);
				BigDecimal taxamount = MathUtils.applyPercentage(currentBillPremiumAmount, codTax.getTaxvalue());

				BigDecimal grossPremiumAmount = currentBillPremiumAmount.add(taxamount);

				Cpt_guarbroker brokerCommission = contractPremiumInfo.getBrokerCommission(numguarantee, numclibroker);
				BigDecimal brokerAmount = MathUtils.applyPercentage(currentBillPremiumAmount, brokerCommission.getRate());

				premiumBillDto.setTaxAmount(taxamount);
				premiumBillDto.setNetPremiumAmount(currentBillPremiumAmount);
				premiumBillDto.setGrossPremiumAmount(grossPremiumAmount);

				premiumBillDto.setBrokerRate(brokerCommission.getRate());
				premiumBillDto.setBrokerAmount(brokerAmount);

				BigDecimal netCompanyAmount = grossPremiumAmount.subtract(brokerAmount);

				/* Apériteur */
				List<Cpt_leadingfee> leadingfees = contractPremiumInfo.getLeadingCommission(numguarantee);
				for (Cpt_leadingfee cptLeadingfee : leadingfees) {
					netLeaderAmount = netLeaderAmount.add(MathUtils.applyPercentage(currentBillPremiumAmount, cptLeadingfee.getRate()));
				}
				if (isLeaderAnAgency)
					grossLeaderAmount = netLeaderAmount.add(premiumBillDto.getTaxAmount());
				else
					grossLeaderAmount = netLeaderAmount;

				/* Coassureurs */
				List<Cpt_guardispatch> dispatches = contractPremiumInfo.getDispatches(numguarantee, numclileader);
				Map<Long, CoinsurerBillDto> coinsurersMap = new HashMap<Long, CoinsurerBillDto>();
				Map<Long, CoinsurerBillDto> placementsMap = new HashMap<Long, CoinsurerBillDto>();
				for (Cpt_guardispatch cptGuardispatch : dispatches) {
					long numcliins = cptGuardispatch.getNumcliins();
					if (personInfo.isAgency(numcliins)) {
						numcliagency = numcliins;
						/* Cas des protocoles ou placements */
						List<Cpt_guarplacement> placements = contractPremiumInfo.getAgencyPlacement(numguarantee);
						for (Cpt_guarplacement cptGuarplacement : placements) {
							BigDecimal amount = cptGuarplacement.getSharepart().multiply(currentBillPremiumAmount);
							CoinsurerBillDto tmp = placementsMap.get(cptGuarplacement.getNumcliins());
							if (tmp == null) {
								tmp = new CoinsurerBillDto();
								tmp.setAmount(amount);
								tmp.setNumcliins(cptGuarplacement.getNumcliins());
							} else {
								tmp.setAmount(tmp.getAmount().add(amount));
							}
							placementsMap.put(cptGuarplacement.getNumcliins(), tmp);
						}
					} else {
						/* Cas des coassureurs classiques */
						Cpt_leadingfee cptLeadingFee = contractPremiumInfo.getLeadingCommission(numguarantee, numcliins, numclileader);
						BigDecimal leadingFeeAmount = cptLeadingFee == null ? ZERO : MathUtils.applyPercentage(
								MathUtils.applyPercentage(currentBillPremiumAmount, cptGuardispatch.getSharepart()), cptLeadingFee.getRate());
						BigDecimal amount = currentBillPremiumAmount.subtract(brokerAmount).multiply(cptGuardispatch.getSharepart())
								.subtract(leadingFeeAmount);
						CoinsurerBillDto tmp = coinsurersMap.get(numcliins);
						if (tmp == null) {
							tmp = new CoinsurerBillDto();
							tmp.setAmount(amount);
							tmp.setNumcliins(numcliins);
						} else {
							tmp.setAmount(tmp.getAmount().add(amount));
						}
						coinsurersMap.put(numcliins, tmp);
					}
				}

				premiumBillDto.setNetCompanyAmount(netCompanyAmount);
				premiumBills.add(premiumBillDto);

				brokerTotalAmount = brokerTotalAmount.add(premiumBillDto.getBrokerAmount());
				grossTotalAmount = grossTotalAmount.add(premiumBillDto.getGrossPremiumAmount());
				netCompanyTotalAmount = netCompanyTotalAmount.add(premiumBillDto.getNetCompanyAmount());
			}
			billDto.setPremiumBills(premiumBills);
			billDto.setBrokerTotalAmount(brokerTotalAmount);
			billDto.setGrossTotalAmount(grossTotalAmount);
			billDto.setNetCompanyTotalAmount(netCompanyTotalAmount);
			billDto.setStartDate(lastDate);
			billDto.setEndDate(nextDate);
			result.add(billDto);

			lastDate = DateUtils.addToDate(nextDate, 1, TimePeriod.DAY);

		}
		return result;
	}
}
