package com.oby.autumn.boutika.configuration.filters;

import static com.google.common.collect.Maps.newHashMap;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

/**
 * Convenience {@link Filter} implementation that add support for a variable list of named
 * parameters and the respective value.
 */
public abstract class ParamableFilter extends Filter {
	private final Map<String, Object> params = newHashMap();

	/**
	 * Adds a parameter and it's value to the list of parameters. The parameter name has to be
	 * escaped (via {@link Filter#escape(String)}).
	 * 
	 * @param name the parameter name.
	 * @param value the parameter value.
	 * @throws IllegalArgumentException if parameter name was not escaped.
	 */
	public void setParameter(final String name, final Object value) {
		if (!name.equals(escape(name))) {
			throw new IllegalArgumentException("the parameter should not be escapable " + name + " != " + escape(name));
		}
		params.put(name, value);
	}

	/**
	 * Sets the values for all parameters declared via
	 * {@link ParamableFilter#setParameter(String, Object)} on the Query object.
	 */
	@Override
	public final void parameters(final Query query) {
		if (!isEmptyFilter()) {
			for (final Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
	}

	@Override
	public Map<String, Object> parameters() {
		return params;
	}


}
