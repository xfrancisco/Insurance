package org.mfi.service.info.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_tax;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cpt_guarbroker;
import org.mfi.data.Cpt_guarcommi;
import org.mfi.data.Cpt_guardispatch;
import org.mfi.data.Cpt_guarplacement;
import org.mfi.data.Cpt_leadingfee;
import org.mfi.dto.bill.BillDto;
import org.mfi.dto.bill.CoinsurerBillDto;
import org.mfi.dto.bill.LeadingFeeDto;
import org.mfi.dto.bill.PlacementBillDto;
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

		boolean isFirstDayOfYearTreated = false;
		Date contractStartDate = cliContract.getStartval();
		Date contractEndDate = cliContract.getEndval();
		Date today = dbHelper.getToday();
		Date firstDayOfYear = DateUtils.getFirstDayOfNextYear(today);
		Date nextDate = today;
		BigDecimal nbDaysOfContract = new BigDecimal(Days.daysBetween(new LocalDate(contractStartDate), new LocalDate(contractEndDate)).getDays());
		Date lastDate = contractStartDate;
		boolean isFeeTreated = false;
		while (!contractEndDate.equals(nextDate) && contractEndDate.after(nextDate)) {
			nextDate = DateUtils.addToDate(lastDate, codFrequency.getNbmonths(), TimePeriod.MONTH);
			nextDate = DateUtils.getFirstDayOfTheMonth(nextDate);
			nextDate = DateUtils.addToDate(nextDate, -1, TimePeriod.DAY);

			if (!isFirstDayOfYearTreated && nextDate.after(firstDayOfYear)) {
				nextDate = firstDayOfYear;
				isFirstDayOfYearTreated = true;
			}

			if (nextDate.after(contractEndDate)) {
				nextDate = contractEndDate;
			}
			BigDecimal nbDaysInCurrentBill = new BigDecimal(Days.daysBetween(new LocalDate(lastDate), new LocalDate(nextDate)).getDays())
			.add(new BigDecimal(1));

			BillDto billDto = new BillDto();
			if (!isFeeTreated) {
				//TODO XFR : à traiter correctement (en l'étalant sur toutes les factures)
				billDto.setFees(contractPremiumInfo.getFees(numcli, numcon));
				isFeeTreated = true;
			}
			List<PremiumBillDto> premiumBills = new ArrayList<PremiumBillDto>();
			BigDecimal grossTotalAmount = ZERO;
			BigDecimal brokerTotalAmount = ZERO;
			BigDecimal netCompanyTotalAmount = ZERO;

			for (Cli_guarantee cliGuarantee : guarantees) {
				Map<Long, CoinsurerBillDto> coinsurersMap = new HashMap<Long, CoinsurerBillDto>();
				Map<Long, PlacementBillDto> placementsMap = new HashMap<Long, PlacementBillDto>();
				Map<Long, LeadingFeeDto> leadingFeesMap = new HashMap<Long, LeadingFeeDto>();

				PremiumBillDto premiumBillDto = new PremiumBillDto();
				long numguarantee = cliGuarantee.getNumguarantee();
				premiumBillDto.setCsection(cliGuarantee.getCsection());
				premiumBillDto.setCguarantee(cliGuarantee.getCguarantee());
				premiumBillDto.setCpremium(cliGuarantee.getCpremium());

				BigDecimal premiumAmount = cliGuarantee.getPremiumamount();
				BigDecimal dailyPremiumAmount = premiumAmount.divide(nbDaysOfContract, 2, RoundingMode.HALF_UP);
				BigDecimal currentBillPremiumAmount = dailyPremiumAmount.multiply(nbDaysInCurrentBill);

				if (codFrequency.getNbmonths() == 12)
					currentBillPremiumAmount = premiumAmount;

				Cpt_guardispatch cptGuardispatchLeader = contractPremiumInfo.getLeaderShare(numguarantee, numclileader);
				BigDecimal leaderShare = cptGuardispatchLeader.getSharepart();

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

				/* Apériteur */
				List<Cpt_leadingfee> leadingfees = contractPremiumInfo.getLeadingCommission(numguarantee);

				for (Cpt_leadingfee cptLeadingfee : leadingfees) {
					BigDecimal amount = MathUtils.applyPercentage(currentBillPremiumAmount, cptLeadingfee.getRate());
					LeadingFeeDto leadingFeeDto = new LeadingFeeDto(cptLeadingfee.getNumclisrc(), cptLeadingfee.getRate(), amount);
					leadingFeesMap.put(cptLeadingfee.getNumclisrc(), leadingFeeDto);
				}

				/* Coassureurs */
				List<Cpt_guardispatch> dispatches = contractPremiumInfo.getDispatches(numguarantee);
				Cpt_guardispatch agencyDispatch = null;
				for (Cpt_guardispatch cptGuardispatch : dispatches) {
					long numcliins = cptGuardispatch.getNumcliins();
					if (personInfo.isAgency(numcliins)) {
						// On se garde l'agence pour plus tard!
						numcliagency = numcliins;
						agencyDispatch = cptGuardispatch;
					} else {
						/* Cas des coassureurs classiques */
						Cpt_leadingfee cptLeadingFee = contractPremiumInfo.getLeadingCommission(numguarantee, numcliins, numclileader);
						BigDecimal leadingFeeAmount = cptLeadingFee == null ? ZERO : MathUtils.applyPercentage(
								MathUtils.applyPercentage(currentBillPremiumAmount, cptGuardispatch.getSharepart()), cptLeadingFee.getRate());
						BigDecimal amount = MathUtils
								.applyPercentage(currentBillPremiumAmount.subtract(brokerAmount), cptGuardispatch.getSharepart()).subtract(
										leadingFeeAmount);
						CoinsurerBillDto coinsurerBillDto = new CoinsurerBillDto(amount, numcliins);
						coinsurersMap.put(numcliins, coinsurerBillDto);
						LeadingFeeDto leadingFeeDto = leadingFeesMap.get(numcliins);
						leadingFeeDto.setShare(cptGuardispatch.getSharepart());
					}
				}

				BigDecimal netCompanyAmount = ZERO;

				/* Cas des protocoles ou placements */
				if (agencyDispatch != null) {
					// Courtage sur la part de l'agence
					BigDecimal agencyBrokerShare = MathUtils.applyPercentage(brokerAmount, agencyDispatch.getSharepart());

					// Récupération des placements de l'agence
					List<Cpt_guarplacement> placements = contractPremiumInfo.getAgencyPlacement(numguarantee);
					for (Cpt_guarplacement cptGuarplacement : placements) {
						// Part de commission de l'agence :
						Cpt_guarcommi cptGuarcommi = contractPremiumInfo.getAgencyCommission(numguarantee, cptGuarplacement.getNumcliins());

						// Montant HT à reverser à l'assureur du placement : Montant avec taxes * part agence
						BigDecimal placementAmount = MathUtils.applyPercentage(MathUtils.applyPercentage(grossPremiumAmount, leaderShare),
								cptGuarplacement.getSharepart());

						// Retenue de l'agence : Net * part agence * commission
						BigDecimal agencyAmount = MathUtils.applyPercentage(
								MathUtils.applyPercentage(currentBillPremiumAmount, agencyDispatch.getSharepart()), cptGuarcommi.getRate());

						// Montant TTC à reverser à l'assureur du placement : Montant HT - Retenue agence  + Taxes
						placementAmount = placementAmount.subtract(agencyAmount).add(taxamount);

						// Retenue nette de l'agence : Retenue - courtage sur la part de l'agence  + les frais d'apérition sur l'ensemble des coassureurs
						BigDecimal agencyLeadingFee = ZERO;
						Set<Long> keys = leadingFeesMap.keySet();
						for (long key : keys) {
							LeadingFeeDto leadingFeeDto = leadingFeesMap.get(key);
							agencyLeadingFee = MathUtils.applyPercentage(agencyLeadingFee.add(leadingFeeDto.getAmount()), leadingFeeDto.getShare());
						}
						agencyAmount = agencyAmount.subtract(agencyBrokerShare).add(agencyLeadingFee);

						PlacementBillDto placementBillDto = new PlacementBillDto(placementAmount, agencyAmount, cptGuarplacement.getNumcliins());
						placementsMap.put(cptGuarplacement.getNumcliins(), placementBillDto);
						netCompanyAmount = netCompanyAmount.add(agencyAmount);
					}
				}

				premiumBillDto.setNetCompanyAmount(netCompanyAmount);
				premiumBillDto.setCoinsurers(coinsurersMap);
				premiumBillDto.setLeadingFees(leadingFeesMap);
				premiumBillDto.setPlacements(placementsMap);
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
