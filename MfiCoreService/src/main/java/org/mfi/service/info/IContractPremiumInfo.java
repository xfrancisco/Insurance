package org.mfi.service.info;

import java.util.List;

import org.mfi.data.Cli_guarantee;
import org.mfi.data.Cpt_guarcommi;
import org.mfi.data.Cpt_guardispatch;

public interface IContractPremiumInfo {

	List<Cli_guarantee> getGuarantees(long numcli, int numcon);

	Cpt_guarcommi getBrokerCommission(long numguarantee, long numclibroker);

	Cpt_guardispatch getLeaderShare(long numguarantee, long numclileader);

	List<Cpt_guardispatch> getDispatches(long numguarantee, long numclileader);

	Cpt_guarcommi getInsurerCommission(long numguarantee, Long numcliinsurer);

}
