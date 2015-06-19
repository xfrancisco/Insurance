package org.mfi.service.info.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.mfi.conf.Cod_frequency;
import org.mfi.conf.Cod_tax;
import org.mfi.data.Cli_contract;
import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cpt_guarcommi;
import org.mfi.dto.bill.BillDto;
import org.mfi.dto.bill.PremiumBillDto;
import org.mfi.service.ServiceCore;
import org.mfi.service.info.IBillInfo;
import org.mfi.service.info.IContractPremiumInfo;
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

	@Override
	public List<BillDto> getBills(final long numcli, final int numcon) {

		List<BillDto> result = new ArrayList<BillDto>();
		Cli_contract cliContract = quoteAndContractInfo.getContract(numcli, numcon);
		long numclibroker = cliContract.getNumclibroker();
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
			BigDecimal grossTotalAmount = new BigDecimal(0);
			BigDecimal brokerTotalAmount = new BigDecimal(0);
			BigDecimal netCompanyTotalAmount = new BigDecimal(0);

			for (Cli_guarantee cliGuarantee : guarantees) {
				BigDecimal premiumAmount = cliGuarantee.getPremiumamount();
				BigDecimal dailyPremiumAmount = premiumAmount.divide(nbDaysOfContract, 2, RoundingMode.HALF_UP);
				BigDecimal currentBillPremiumAmount = dailyPremiumAmount.multiply(nbDaysInCurrentBill);

				PremiumBillDto premiumBillDto = new PremiumBillDto();
				Cod_tax codTax = premiumInfo.getTaxByPremium(cliGuarantee.getCpremium());
				Cpt_guarcommi brokerCommission = contractPremiumInfo.getBrokerCommission(cliGuarantee.getNumguarantee(), numclibroker);

				premiumBillDto.setCsection(cliGuarantee.getCsection());
				premiumBillDto.setCguarantee(cliGuarantee.getCguarantee());
				premiumBillDto.setCpremium(cliGuarantee.getCpremium());

				premiumBillDto.setCodtax(codTax);
				premiumBillDto.setTaxAmount(MathUtils.applyPercentage(currentBillPremiumAmount, codTax.getTaxvalue()));
				premiumBillDto.setNetPremiumAmount(currentBillPremiumAmount);
				premiumBillDto.setGrossPremiumAmount(premiumBillDto.getNetPremiumAmount().add(premiumBillDto.getTaxAmount()));

				premiumBillDto.setBrokerRate(brokerCommission.getRate());
				premiumBillDto.setBrokerAmount(MathUtils.applyPercentage(currentBillPremiumAmount, brokerCommission.getRate()));

				BigDecimal netCompanyAmount = premiumBillDto.getGrossPremiumAmount().subtract(premiumBillDto.getBrokerAmount());

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
