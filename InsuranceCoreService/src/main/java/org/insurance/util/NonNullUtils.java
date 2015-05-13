package org.insurance.util;


import java.util.Collections;
import java.util.List;
import java.util.Map;



public final class NonNullUtils {

	private NonNullUtils() {

	}

	public static final Object[] EMPTY_ARRAY = {};

	/**
	 * Retourne une liste non null
	 * @param nullableList Liste à retourner si non null
	 * @return Liste vide si nullableList est null, nullableList sinon
	 */
	@SuppressWarnings("null")
	public static final <T> List<T> nonNullList(final/*@Nullable*/List<T> nullableList) {
		if (nullableList == null) {
			return Collections.emptyList();
		} else {
			return nullableList;
		}
	}

	/**
	 * Retourne une Map non null
	 * @param nullableMap Map à retourner si non null
	 * @return Map vide si nullableMap est null, nullableMap sinon
	 */
	@SuppressWarnings({ "null" })
	public static final <K, V> Map<K, V> nonNullMap(final/*@Nullable*/Map<K, V> nullableMap) {
		if (nullableMap == null) {
			return Collections.emptyMap();
		} else {
			return nullableMap;
		}
	}

	@SuppressWarnings("unchecked")
	public static final <T> T[] nonNullArray(final/*@Nullable*/T[] nullableArray) {
		if (nullableArray == null) {
			return (T[]) EMPTY_ARRAY;
		} else {
			return nullableArray;
		}
	}
}

