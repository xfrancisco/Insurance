package org.insurance.service.transactional.impl;

import java.util.List;

import javax.inject.Inject;

import org.insurance.data.Cli_contract;
import org.insurance.data.Cli_guarantee;
import org.insurance.data.Cpt_guarcommi;
import org.insurance.data.Cpt_guardispatch;
import org.insurance.dto.contract.ContractDto;
import org.insurance.dto.contract.DispatchDto;
import org.insurance.dto.contract.GuaranteeDto;
import org.insurance.exception.InsuranceException;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IContractOperation;
import org.insurance.service.transactional.IMovementOperation;
import org.springframework.stereotype.Service;

@Service
public class ContractOperation extends ServiceCore implements IContractOperation {

	@Inject
	private IMovementOperation movementOperation;

	@Override
	public int insertContract(final long numcli, final int numcon, final String cuser, final ContractDto contract) throws InsuranceException {
		Cli_contract cliContract = contract.getContract();
		cliContract.setNumcli(numcli);
		cliContract.setNumcon(numcon);
		cliContract.setCusercre(cuser);
		cliContract.setCreationDate(dbHelper.getNow());
		genericDao.save(cliContract);

		List<GuaranteeDto> guarantees = contract.getGuarantees();
		for (GuaranteeDto guaranteeDto : guarantees) {
			// Enregistrement de la garantie
			Cli_guarantee cliGuarantee = new Cli_guarantee();
			cliGuarantee.setCbranch(guaranteeDto.getCbranch());
			cliGuarantee.setCcategory(guaranteeDto.getCcategory());
			cliGuarantee.setCsection(guaranteeDto.getCsection());
			cliGuarantee.setCguarantee(guaranteeDto.getCguarantee());
			cliGuarantee.setCpremium(guaranteeDto.getCpremium());
			cliGuarantee.setCreationDate(dbHelper.getNow());
			cliGuarantee.setCusercre(cuser);
			cliGuarantee.setGuaranteeamount(guaranteeDto.getGuaranteedAmount());
			cliGuarantee.setNumcli(numcli);
			cliGuarantee.setNumcon(numcon);
			cliGuarantee.setPremiumamount(guaranteeDto.getPremiumAmount());
			cliGuarantee.setStartval(cliContract.getStartval());
			cliGuarantee.setEndval(cliContract.getEndval());
			long numguarantee = (Long) genericDao.save(cliGuarantee);

			// Enregistrement de la part de l'apériteur
			Cpt_guardispatch cptGuardispatchLeader = new Cpt_guardispatch();
			cptGuardispatchLeader.setCreationDate(dbHelper.getNow());
			cptGuardispatchLeader.setCusercre(cuser);
			cptGuardispatchLeader.setNumcliins(cliContract.getNumclileader());
			cptGuardispatchLeader.setNumguarantee(numguarantee);
			cptGuardispatchLeader.setSharepart(guaranteeDto.getLeaderShare());
			genericDao.save(cptGuardispatchLeader);

			List<DispatchDto> dispatches = guaranteeDto.getDispatch();
			for (DispatchDto dispatchDto : dispatches) {
				// Enregistrement de la part des co-assureurs
				Cpt_guardispatch cptGuardispatch = new Cpt_guardispatch();
				cptGuardispatch.setCreationDate(dbHelper.getNow());
				cptGuardispatch.setCusercre(cuser);
				cptGuardispatch.setNumcliins(dispatchDto.getNumcliinsurer());
				cptGuardispatch.setNumguarantee(numguarantee);
				cptGuardispatch.setSharepart(dispatchDto.getInsurerShare());
				genericDao.save(cptGuardispatch);

				// Enregistrement de la commission des coassureurs
				Cpt_guarcommi cptGuarcommi = new Cpt_guarcommi();
				cptGuarcommi.setCreationDate(dbHelper.getNow());
				cptGuarcommi.setCusercre(cuser);
				cptGuarcommi.setNumclicommi(dispatchDto.getNumcliinsurer());
				cptGuarcommi.setNumguarantee(numguarantee);
				cptGuarcommi.setRate(dispatchDto.getInsurerRate());
				genericDao.save(cptGuarcommi);
			}

			// Enregistrement de la commission du courtier
			Cpt_guarcommi cptGuarcommiBroker = new Cpt_guarcommi();
			cptGuarcommiBroker.setCreationDate(dbHelper.getNow());
			cptGuarcommiBroker.setCusercre(cuser);
			cptGuarcommiBroker.setNumclicommi(cliContract.getNumclibroker());
			cptGuarcommiBroker.setNumguarantee(numguarantee);
			cptGuarcommiBroker.setRate(guaranteeDto.getBrokerRate());
			genericDao.save(cptGuarcommiBroker);
		}
		return 0;
	}

}
