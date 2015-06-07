package org.insurance.service.info;

import java.util.List;

import org.insurance.data.Cli_guarantee;
import org.insurance.data.Cpt_guarcommi;
import org.insurance.data.Cpt_guardispatch;

public interface IContractPremiumInfo {

	List<Cli_guarantee> getGuarantees(long numcli, int numcon);

	Cpt_guarcommi getBrokerCommission(long numguarantee, long numclibroker);

	Cpt_guardispatch getLeaderShare(long numguarantee, long numclileader);

	List<Cpt_guardispatch> getDispatches(long numguarantee, long numclileader);

	Cpt_guarcommi getInsurerCommission(long numguarantee, Long numcliinsurer);

}
