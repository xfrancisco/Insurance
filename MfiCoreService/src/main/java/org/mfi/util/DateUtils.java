package org.mfi.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.mfi.exception.TechnicalException;

public final class DateUtils {

	private static final int MONTH_YEAR = 12;

	public static enum DatePattern {
		DATE_YYYYMMDD("yyyyMMdd"),
		DATE_YYYYMM("yyyyMM"),
		DATE_MM_YY("MM/YY"),
		DATE_DD_MM("DD/MM"),
		DATE_DD_MM_YYYY("dd/MM/yyyy"),
		DATE_DD_MM_YYYY_HH_MM_SS("dd/MM/yyyy hh:mm:ss");

		private final String pattern;

		DatePattern(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return pattern;
		}
	}

	public static enum TimePeriod {
		DAY(Calendar.DATE),
		MONTH(Calendar.MONTH),
		YEAR(Calendar.YEAR);

		private final int calendarField;

		TimePeriod(int calendarField) {
			this.calendarField = calendarField;
		}

		public int getCalendarField() {
			return calendarField;
		}

	}

	private DateUtils() {
	}

	public static String formatDate(Date date) {
		return formatDate(date, DatePattern.DATE_YYYYMMDD);
	}

	public static String formatDate(Date date, DatePattern pattern) {
		String formattedDate = null;
		if (date != null) {
			try {
				DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern.getPattern());
				formattedDate = dateTimeFormatter.print(date.getTime());
			} catch (Exception e) {
				throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_INVALID_DATE, e, date);
			}
		}
		return formattedDate;
	}

	/**
	 * Convertit une chaine en date. Si la chaine est null ou vide la fonction retourne null
	 * @param date Chaine au format YYYYMMDD
	 * @return une Date
	 * @throws TechnicalException
	 */
	public static Date parseStringToUtilDate(final String date) {
		return parseStringToUtilDate(date, DatePattern.DATE_YYYYMMDD);
	}

	/**
	 * Convertit une chaine en date. Si la chaine est null ou vide la fonction retourne null
	 * @param date Chaine au format spécifié par le pattern
	 * @param pattern format de la date
	 * @return une Date
	 * @throws TechnicalException
	 */
	public static Date parseStringToUtilDate(final String date, DatePattern pattern) {
		Date parsedDate = null;
		if (StringUtils.isNotBlank(date)) {
			try {
				DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern.getPattern());
				LocalDate parsedLocalDate = dateTimeFormatter.parseLocalDate(date);

				parsedDate = parsedLocalDate.toDate();
			} catch (Exception e) {
				throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_INVALID_DATE, e, date);
			}
		}
		return parsedDate;
	}

	/**
	 * Convertit une chaine en date. Si la chaine est null ou vide la fonction retourne null
	 * @param date Chaine au format spécifié par le pattern
	 * @param pattern format de la date
	 * @return une java.sql.Date
	 * @throws ParsingDateException
	 */
	public static java.sql.Date parseStringToSqlDate(final String date, DatePattern pattern) {
		java.sql.Date parsedDate = null;
		if (StringUtils.isNotBlank(date)) {
			try {
				DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern.getPattern());
				LocalDate parsedLocalDate = dateTimeFormatter.parseLocalDate(date);

				parsedDate = new java.sql.Date(parsedLocalDate.toDate().getTime());
			} catch (Exception e) {
				throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_INVALID_DATE, e, date);
			}
		}
		return parsedDate;
	}

	/**
	 * Convertit une chaine en date. Si la chaine est null ou vide la fonction retourne null
	 * @param date Chaine au format YYYYMMDD
	 * @return une java.sql.Date
	 * @throws TechnicalException
	 */
	public static java.sql.Date parseStringToSqlDate(final String date) {
		return parseStringToSqlDate(date, DatePattern.DATE_YYYYMMDD);
	}

	/**
	 * Ajoute la période de temps spécifiée à la date en paramètre.
	 * @param date date initiale
	 * @param nbToAdd nombre d'unité de temps à ajouter
	 * @param timePeriod unité de temps (jour, mois, année)
	 * @return
	 */
	public static Date addToDate(final Date date, int nbToAdd, final TimePeriod timePeriod) {
		switch (timePeriod) {
			case MONTH:
				return org.apache.commons.lang.time.DateUtils.addMonths(date, nbToAdd);
			case DAY:
				return org.apache.commons.lang.time.DateUtils.addDays(date, nbToAdd);
			case YEAR:
				return org.apache.commons.lang.time.DateUtils.addYears(date, nbToAdd);
			default:
				throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_INVALIDTIMEPERIOD, timePeriod.name());
		}

	}

	/**
	 * Retire la période de temps spécifiée à la date en paramètre.
	 * @param date date initiale
	 * @param nbToAdd nombre d'unité de temps à ajouter
	 * @param timePeriod unité de temps (jour, mois, année)
	 * @return
	 */
	public static Date withdrawToDate(final Date date, int nbToWithdraw, final TimePeriod timePeriod) {
		return addToDate(date, -nbToWithdraw, timePeriod);
	}

	public static Date convertSqlDateToUtilDate(final java.sql.Date date) {
		Date convertedDate = null;
		if (date != null) {
			convertedDate = new Date(date.getTime());
		}
		return convertedDate;
	}

	public static java.sql.Date convertUtilDateToSqlDate(final Date date) {
		java.sql.Date convertedDate = null;
		if (date != null) {
			convertedDate = new java.sql.Date(date.getTime());
		}
		return convertedDate;
	}

	/**
	 * Compare deux objets java.util.Date en n'utilisant que la partie jour mois annee.
	 * @param date1
	 * @param date2
	 * @return 0 si dates egales, positif si date1 est apres date2, negatif si date1 est avant date2.
	 */
	public static int compareWithDay(Date date1, Date date2) {
		return new LocalDate(date1).compareTo(new LocalDate(date2));
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar parseDateToXMLGregorianCalendar(Date date) {
		XMLGregorianCalendar xmlGregorianCalendar = null;
		if (date != null) {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTimeInMillis(date.getTime());
			try {
				xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			} catch (DatatypeConfigurationException e) {
				throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_GEN_DEFAULT, e);
			}
		}
		return xmlGregorianCalendar;
	}

	public static int truncatedCompareTo(final Date date1, final Date date2) {
		return truncatedCompareTo(date1, date2, TimePeriod.DAY);
	}

	/**
	 * Compare les versions tronquées de 2 dates (potentiellement null).
	 * Une date null est considérée comme étant à +INFINI
	 * @param date1
	 * @param date2
	 * @param timePeriod
	 * @return
	 */
	public static int truncatedCompareTo(final Date date1, final Date date2, final TimePeriod timePeriod) {
		if ((date1 != null) && (date2 != null)) {
			return org.apache.commons.lang.time.DateUtils.truncatedCompareTo(date1, date2, timePeriod.getCalendarField());
		}
		if ((date1 == null) && (date2 == null)) {
			return 0;
		}
		//une des deux date seulement est null
		if (date1 == null) {
			return 1;
		} else {
			return -1;
		}
	}

	public static Date truncate(final Date date) {
		return truncate(date, TimePeriod.DAY);
	}

	public static Date truncate(final Date date, final TimePeriod timePeriod) {
		if (date == null) {
			return null;
		}
		return org.apache.commons.lang.time.DateUtils.truncate(date, timePeriod.getCalendarField());
	}

	public static int getValueOfDateFromField(final Date date, TimePeriod timePeriod) {
		return Calendar.getInstance().get(timePeriod.getCalendarField());
	}

	public static Date maxDate(Date date1, Date date2) {
		if (date1 == null) {
			return date2;
		}

		if (date2 == null) {
			return date1;
		}

		if (date1.before(date2)) {
			return date2;
		} else {
			return date1;
		}
	}

	/**
	 * return min(date1,date2), min(null, date) = date
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static Date minDate(Date date1, Date date2) {
		if (date1 == null) {
			return date2;
		}

		if (date2 == null) {
			return date1;
		}

		if (date1.before(date2)) {
			return date1;
		} else {
			return date2;
		}
	}

	public static int numberOfMonthsBetween(final Date startDate, final Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.setTime(endDate);

		int startMonth = calendar.get(Calendar.MONTH);
		int startYear = calendar.get(Calendar.YEAR);

		int endMonth = calendar.get(Calendar.MONTH);
		int endYear = calendar.get(Calendar.YEAR);

		return ((endYear - startYear) * MONTH_YEAR) + (endMonth - startMonth);
	}

	/**
	 * Méthode utilitaire permattant le test de cohérence chronologique entre une date de début et une date de fin.
	 * Le pattern par défaut "yyyyMMdd"
	 *
	 * @param startDate : la date de début
	 * @param endDate : la date de fin
	 *
	 * @throws TechnicalException : Exception renvoyée si l'une des date est vide ou null ou si une incohérence chronologique est trouvée
	 */
	public static void checkStartAndEndDate(String startDate, String endDate) {
		checkStartAndEndDate(startDate, endDate, DatePattern.DATE_YYYYMMDD);
	}

	/**
	 * Méthode utilitaire permattant le test de cohérence chronologique entre une date de début et une date de fin.
	 *
	 * @param startDate : la date de début
	 * @param endDate : la date de fin
	 * @param pattern : le format de la date
	 *
	 * @throws TechnicalException : Exception renvoyée si l'une des date est vide ou null ou si une incohérence chronologique est trouvée
	 */
	public static void checkStartAndEndDate(String startDate, String endDate, DatePattern pattern) {
		if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)) {

			if (parseStringToUtilDate(startDate, pattern).after(parseStringToUtilDate(endDate, pattern))) {
				throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_INVALID_DATE, new Object[] { startDate, endDate });
			}
		} else {
			throw new TechnicalException(TechnicalException.ErrorCode.ERR_TECH_INVALID_DATE, new Object[] { startDate, endDate });
		}
	}

	public static Date getLastDayOfTheMonth(final Date date) {
		if (date == null) {
			return null;
		}
		LocalDate locdate = new LocalDate(date);
		LocalDate endOfMonth = locdate.dayOfMonth().withMaximumValue();
		return endOfMonth.toDate();
	}

	public static Date getFirstDayOfTheMonth(final Date date) {
		if (date == null) {
			return null;
		}
		LocalDate locdate = new LocalDate(date);
		LocalDate endOfMonth = locdate.dayOfMonth().withMinimumValue();
		return endOfMonth.toDate();
	}

	public static Date getLastDayOfTheYear(final Date date) {
		if (date == null) {
			return null;
		}
		LocalDate locdate = new LocalDate(date);
		LocalDate endOfYear = locdate.monthOfYear().withMaximumValue();
		return endOfYear.dayOfMonth().withMaximumValue().toDate();
	}
}