package org.mfi.service.info;

import java.util.List;

import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cpt_fee;
import org.mfi.data.Cpt_guarbroker;
import org.mfi.data.Cpt_guarcommi;
import org.mfi.data.Cpt_guardispatch;
import org.mfi.data.Cpt_guarplacement;
import org.mfi.data.Cpt_leadingfee;

public interface IContractPremiumInfo {

	List<Cli_guarantee> getGuarantees(long numcli, int numcon);

	Cpt_guarbroker getBrokerCommission(long numguarantee, long numclibroker);

	Cpt_guardispatch getLeaderShare(long numguarantee, long numclileader);

	List<Cpt_guardispatch> getDispatches(long numguarantee, long numclileader);

	Cpt_guarcommi getAgencyCommission(long numguarantee, long numcliinsurer);

	Cpt_fee getInitialFees(long numcli, int numcon);

	List<Cpt_leadingfee> getLeadingCommission(long numguarantee);

	Cpt_leadingfee getLeadingCommission(long numguarantee, long numclisrc, long numclidest);

	List<Cpt_guarcommi> getAgencyCommission(long numguarantee);

	List<Cpt_guarplacement> getAgencyPlacement(long numguarantee);

	Cpt_guarplacement getAgencyPlacement(long numguarantee, long numcliinsurer);

}
