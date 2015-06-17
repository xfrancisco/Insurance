package org.mfi.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.mfi.dao.IDBHelper;
import org.mfi.dao.IGenericDao;

/**
 * Classe mère à tous les services:
 * Check, Info, Transaction
 *
 */
public class ServiceCore {

	@Inject
	protected IGenericDao genericDao;

	@Inject
	protected IDBHelper dbHelper;

	/**
	 * Vérifie que la pk existe dans la table (tableClass) et qu'elle est indvalid à 1
	 * Certaines tables n'ont pas d'indicateur de validité, on suppose donc que ces valeurs sont tout le temps valides.
	 * @param tableClass Bean mappant une table
	 * @param pk Clé primaire à tester
	 * @return Le bean instancié s'il est valide, null sinon
	 */
	protected <T> T getExistsAndValidInTable(final Class<T> tableClass, final Serializable pk) {
		T table = genericDao.get(tableClass, pk);
		if (table != null) {
			boolean isValid = true;
			try {
				String indvali = BeanUtils.getProperty(table, "indvali");
				isValid = "1".equals(indvali);
			} catch (IllegalAccessException | InvocationTargetException e) {
				isValid = false;
			} catch (NoSuchMethodException e) {
				// Si la colonne "indvali" n'existe pas, c'est que toutes les valeurs sont authorisées
				// du coup, c'est valide.
				isValid = true;
			}
			if (!isValid) {
				table = null;
			}
		}
		return table;
	}
}
