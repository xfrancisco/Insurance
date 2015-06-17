package org.mfi.movements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Movement {

	private final MovementCode movement;
	protected final Map<Enum<?>, List<String>> parameters;
	protected final Map<Enum<?>, List<String>> oldparameters;

	protected Movement(final MovementCode movement) {
		this.movement = movement;
		this.parameters = new HashMap<>();
		this.oldparameters = new HashMap<>();
	}

	public final String getCmovement() {
		return movement.name();
	}

	public final Map<Enum<?>, List<String>> getDetail() {
		return parameters;
	}

	public final Map<Enum<?>, List<String>> getOldDetail() {
		return oldparameters;
	}

	protected void addParameters(final Enum<?> name, final String value) {
		// ne pas faire de uppercase sur les valeurs sinon les champs annotés en @hashvalue sont pris en compte
		List<String> values = parameters.get(name);
		if (values == null || values.isEmpty())
			values = new ArrayList<String>();
		values.add(value);
		parameters.put(name, values);
	}

	protected void addOldParameters(final Enum<?> name, final String value) {
		// ne pas faire de uppercase sur les valeurs sinon les champs annotés en @hashvalue sont pris en compte
		List<String> values = oldparameters.get(name);
		if (values == null || values.isEmpty())
			values = new ArrayList<String>();
		values.add(value);
		oldparameters.put(name, values);
	}

	protected void addOldParameters(final Enum<?> name, final List<String> value) {
		// ne pas faire de uppercase sur les valeurs sinon les champs annotés en @hashvalue sont pris en compte
		oldparameters.put(name, value);
	}

	protected void addParameters(final Enum<?> name, final List<String> value) {
		// ne pas faire de uppercase sur les valeurs sinon les champs annotés en @hashvalue sont pris en compte
		parameters.put(name, value);
	}

	protected void addParameters(final Enum<?> name, final String oldvalue, final String value) {
		// ne pas faire de uppercase sur les valeurs sinon les champs annotés en @hashvalue sont pris en compte
		addOldParameters(name, value);
		addParameters(name, value);
	}
}