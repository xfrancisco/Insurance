package org.mfi.dao;

import java.util.Date;

/**
 * Classe utilitaire donnant accès des informations liées à la base de données.
 *
 */
public interface IDBHelper {
	/**
	 * Retourne la date du jour en base de données avec les heures, minutes, secondes.
	 * @return Date du jour avec heure, minutes, secondes.
	 * @see #getToday
	 */
	Date getNow();

	/**
	 * Retourne la date du jour en base de données mais sans les heures, minutes, secondes.
	 * @return Date du jour tronquée (sans heure, minutes, secondes)
	 * @see #getNow
	 */
	Date getToday();

	/**
	 * Retourne la prochaine valeur d'une séquence
	 * @param sequenceName séquence dont on veut récupérer la prochaine valeur
	 * @return Prochaine valeur de la séquence
	 */
	long getSequenceNextVal(String sequenceName);

}
