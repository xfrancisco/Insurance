package org.insurance.service.transactional.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.insurance.data.Cli_movement;
import org.insurance.data.Cli_movementdet;
import org.insurance.movements.Movement;
import org.insurance.service.ServiceCore;
import org.insurance.service.transactional.IMovementOperation;
import org.springframework.stereotype.Service;

@Service
public class MovementOperation extends ServiceCore implements IMovementOperation {

	private void addMovementDetails(long nummovement, Enum<?> key, String value, String oldvalue) {
		Cli_movementdet cliMovementdet = new Cli_movementdet();
		cliMovementdet.setNummovement(nummovement);
		cliMovementdet.setCcolumn(key.name());
		cliMovementdet.setOldvalue(oldvalue);
		cliMovementdet.setNewvalue(value);
		genericDao.save(cliMovementdet);
	}

	@Override
	public long insertMovement(long numcli, Integer numcon, String cuser, Movement movement) {

		Cli_movement cliMouvmt = new Cli_movement();
		cliMouvmt.setCmovement(movement.getCmovement());
		cliMouvmt.setCusermovement(cuser);
		cliMouvmt.setMovementDate(dbHelper.getNow());
		cliMouvmt.setNumcli(numcli);
		cliMouvmt.setNumcon(numcon);
		Long nummovement = (Long) genericDao.save(cliMouvmt);

		//Insert dans abo_mouvmtdet
		final Map<Enum<?>, List<String>> detail = movement.getDetail();
		for (Map.Entry<Enum<?>, List<String>> entry : detail.entrySet()) {
			if (movement.getOldDetail().isEmpty()) {
				for (String value : entry.getValue()) {
					addMovementDetails(nummovement, entry.getKey(), value, null);
				}
			} else {
				List<String> oldvalues = movement.getOldDetail().get(entry.getKey());
				if (oldvalues == null) {
					oldvalues = new ArrayList<String>();
				}
				List<String> newvalues = entry.getValue();
				if (newvalues == null) {
					newvalues = new ArrayList<String>();
				}
				if (oldvalues.size() == newvalues.size() && newvalues.size() == 1) {
					String oldvalue = oldvalues.get(0);
					String newvalue = newvalues.get(0);
					addMovementDetails(nummovement, entry.getKey(), newvalue, oldvalue);
				} else {
					int oldsize = oldvalues.size();
					int newsize = newvalues.size();
					if (newsize > oldsize) {
						for (int i = 0; i < oldsize; i++) {
							addMovementDetails(nummovement, entry.getKey(), newvalues.get(i), oldvalues.get(i));
						}
						for (int i = oldsize; i < newsize; i++) {
							addMovementDetails(nummovement, entry.getKey(), newvalues.get(i), null);
						}
					} else {
						for (int i = 0; i < newsize; i++) {
							addMovementDetails(nummovement, entry.getKey(), newvalues.get(i), oldvalues.get(i));
						}
						for (int i = newsize; i < oldsize; i++) {
							addMovementDetails(nummovement, entry.getKey(), "", oldvalues.get(i));
						}
					}
				}

			}
		}
		return nummovement;
	}
}
