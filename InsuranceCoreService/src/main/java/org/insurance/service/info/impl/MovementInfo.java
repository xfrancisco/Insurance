package org.insurance.service.info.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.insurance.conf.Cod_movement;
import org.insurance.conf.Cod_movementdet;
import org.insurance.dao.IDBServiceHelper;
import org.insurance.data.Cli_movement;
import org.insurance.data.Cli_movementdet;
import org.insurance.dto.movements.MovementChangeDto;
import org.insurance.dto.movements.MovementDetailDetailsDto;
import org.insurance.dto.movements.MovementDetailDto;
import org.insurance.dto.movements.MovementDto;
import org.insurance.service.ServiceCore;
import org.insurance.service.info.IMovementInfo;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

@Service
public class MovementInfo extends ServiceCore implements IMovementInfo {

	private static final String DETAIL_SEPARATOR = ";";

	@Inject
	private IDBServiceHelper dbServiceHelper;

	@Override
	public Cli_movement getMovement(final long nummovement) {
		return genericDao.get(Cli_movement.class, nummovement);
	}

	@Override
	public MovementChangeDto getMovementDetails(final long nummovement) {
		DetachedCriteria crit = DetachedCriteria.forClass(Cli_movementdet.class, "mvtd");
		crit.add(Restrictions.eq("mvtd.nummovement", nummovement));
		List<Cli_movementdet> mouvmtDets = genericDao.getByCriteria(crit);

		Map<String, List<String>> oldValues = new HashMap<String, List<String>>();
		Map<String, List<String>> newValues = new HashMap<String, List<String>>();
		for (Cli_movementdet det : mouvmtDets) {
			String ccolumn = det.getCcolumn();
			List<String> tmp = null;
			// OLDVALUES
			if (!Strings.isNullOrEmpty(det.getOldvalue())) {
				tmp = oldValues.get(ccolumn);
				if (tmp == null)
					tmp = new ArrayList<String>();
				tmp.add(det.getOldvalue());
				oldValues.put(ccolumn, tmp);
			}

			// NEWVALUES
			if (!Strings.isNullOrEmpty(det.getNewvalue())) {
				tmp = newValues.get(ccolumn);
				if (tmp == null)
					tmp = new ArrayList<String>();
				tmp.add(det.getNewvalue());
				newValues.put(ccolumn, tmp);
			}
		}
		return getMovementDetails(oldValues, newValues);
	}

	private MovementChangeDto getMovementDetails(final Map<String, List<String>> oldValues, final Map<String, List<String>> newValues) {
		MovementChangeDto result = new MovementChangeDto();
		Set<Entry<String, List<String>>> entrySet = oldValues.entrySet();
		Iterator<Entry<String, List<String>>> iter = entrySet.iterator();
		while (iter.hasNext()) {
			Entry<String, List<String>> entry = iter.next();
			String ccolumn = entry.getKey();
			List<String> values = entry.getValue();
			for (String value : values) {
				MovementDetailDto detail = populateMvtDetail(ccolumn, value);
				result.setOldValues(detail);
			}
		}

		entrySet = newValues.entrySet();
		iter = entrySet.iterator();
		while (iter.hasNext()) {
			Entry<String, List<String>> entry = iter.next();
			String code = entry.getKey();
			List<String> values = entry.getValue();
			for (String value : values) {
				MovementDetailDto detail = populateMvtDetail(code, value);
				result.setNewValues(detail);
			}
		}
		return result;
	}

	private MovementDetailDto populateMvtDetail(final String ccolumn, final String value) {
		// Ce traitement est un traitement récursif basé sur du paramétrage.
		// L'erreur étant humaine, on va utiliser une map permettant d'éviter dans des boucles infinies.
		// D'où ce nom savamment choisi : infiniteLoopPreventer
		Map<String, Boolean> infiniteLoopPreventer = new HashMap<String, Boolean>();

		MovementDetailDto result = new MovementDetailDto();
		result.setCode(ccolumn);
		result.setValue(value);

		String label = null;
		String valueLabel = null;
		Cod_movementdet cmouvmtdet = getMovementDetailConfiguration(ccolumn);

		if (cmouvmtdet != null) {
			label = cmouvmtdet.getLabel();
			result.setLabel(label);

			String tables = cmouvmtdet.getValuetable();
			if (tables != null) {
				StringTokenizer tablesTokens = new StringTokenizer(tables, DETAIL_SEPARATOR);

				String columns = cmouvmtdet.getValuecolumn();
				StringTokenizer columnsTokens = new StringTokenizer(columns, DETAIL_SEPARATOR);

				String table = tablesTokens.nextToken();
				String column = columnsTokens.nextToken();

				valueLabel = getValueFromTable(table, column, cmouvmtdet.getCcolumn(), value);

				result.setValueLabel(valueLabel);

				while (columnsTokens.hasMoreTokens()) {
					column = columnsTokens.nextToken();
					table = tablesTokens.nextToken();
					result.addFurtherDetails(getDetails(table, ccolumn, value, column, infiniteLoopPreventer));
				}
			} else {
				result.setValueLabel(value);
			}
		} else {
			result.setLabel(ccolumn);
			result.setValueLabel(value);
		}
		return result;
	}

	private Cod_movementdet getMovementDetailConfiguration(final String ccolumn) {
		DetachedCriteria crit = DetachedCriteria.forClass(Cod_movementdet.class, "mdet");
		crit.add(Restrictions.eq("mdet.ccolumn", ccolumn));
		return genericDao.getFirstByCriteria(crit);
	}

	private List<MovementDetailDetailsDto> getDetails(final String mainTable, final String mainCode, final String mainValue, final String column,
			Map<String, Boolean> infiniteLoopPreventer) {
		Cod_movementdet cmouvmtdet = getMovementDetailConfiguration(column);
		List<MovementDetailDetailsDto> result = new ArrayList<MovementDetailDetailsDto>();
		MovementDetailDetailsDto detail = null;

		if (isEligibleForDetails(cmouvmtdet, infiniteLoopPreventer)) {
			String tables = cmouvmtdet.getValuetable();
			StringTokenizer tablesTokens = new StringTokenizer(tables, DETAIL_SEPARATOR);
			String detailValue = getValueFromTable(mainTable, column, mainCode, mainValue);

			String columns = cmouvmtdet.getValuecolumn();
			StringTokenizer columnsTokens = new StringTokenizer(columns, DETAIL_SEPARATOR);

			String detailValueLabel = getValueFromTable(tablesTokens.nextToken(), columnsTokens.nextToken(), column, detailValue);

			detail = new MovementDetailDetailsDto(column, cmouvmtdet.getLabel(), detailValue, detailValueLabel);
			while (columnsTokens.hasMoreTokens()) {
				result.addAll(getDetails(tablesTokens.nextToken(), column, detailValue, columnsTokens.nextToken(), infiniteLoopPreventer));
			}
		} else {
			String detailValueLabel = getValueFromTable(mainTable, column, mainCode, mainValue);
			detail = new MovementDetailDetailsDto(column, cmouvmtdet.getLabel(), detailValueLabel, detailValueLabel);
		}
		if (detail != null)
			result.add(detail);
		return result;
	}

	private boolean isEligibleForDetails(final Cod_movementdet cmouvmtdet, Map<String, Boolean> infiniteLoopPreventer) {
		if (cmouvmtdet != null && !Strings.isNullOrEmpty(cmouvmtdet.getValuetable())) {
			Boolean isAlreadyUsed = infiniteLoopPreventer.get(cmouvmtdet.getCcolumn());
			if (isAlreadyUsed != null) {
				return false;
			} else {
				infiniteLoopPreventer.put(cmouvmtdet.getCcolumn(), true);
				return true;
			}
		}
		return false;
	}

	private String getValueFromTable(final String table, final String columnForValue, final String columnForCode, final String value) {
		if (!Strings.isNullOrEmpty(columnForValue) && !Strings.isNullOrEmpty(table)) {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer queryBuffer = new StringBuffer();
			queryBuffer.append("SELECT ");
			queryBuffer.append(columnForValue);
			queryBuffer.append(" FROM ");
			queryBuffer.append(table);
			queryBuffer.append(" WHERE ");
			queryBuffer.append(columnForCode);
			if (Strings.isNullOrEmpty(value)) {
				queryBuffer.append(" IS NULL ");
			} else {
				queryBuffer.append(" = :value ");
				params.put("value", value);
			}
			try {
				Object object = dbServiceHelper.simpleSelectNullable(queryBuffer.toString(), params);
				if (object != null) {
					return object.toString();
				}
				return value;
			} catch (Exception e) {
				return value;
			}
		}
		return null;
	}

	@Override
	public List<MovementDto> getMovements(final long numcli, final Integer numcon, final Integer numquote) {
		DetachedCriteria crit = DetachedCriteria.forClass(Cli_movement.class);
		if (numcon != null) {
			crit.add(Restrictions.eq("numcli", numcli));
			crit.add(Restrictions.eq("numcon", numcon));
		} else if (numquote != null) {
			crit.add(Restrictions.eq("numcli", numcli));
			crit.add(Restrictions.eq("numquote", numquote));
		} else {
			crit.add(Restrictions.eq("numcli", numcli));
			crit.add(Restrictions.isNull("numcon"));
			crit.add(Restrictions.isNull("numquote"));
		}
		List<Cli_movement> movements = genericDao.getByCriteria(crit);
		List<MovementDto> result = new ArrayList<MovementDto>();
		for (Cli_movement cliMovement : movements) {
			MovementDto tmp = new MovementDto();
			tmp.setCmovement(cliMovement.getCmovement());
			tmp.setMovementDate(cliMovement.getMovementDate());
			tmp.setCusermovement(cliMovement.getCusermovement());
			tmp.setNummovement(cliMovement.getNummovement());
			Cod_movement codMovement = genericDao.get(Cod_movement.class, cliMovement.getCmovement());
			tmp.setLmovement(codMovement.getLmovement());
			result.add(tmp);
		}
		return result;
	}

}
