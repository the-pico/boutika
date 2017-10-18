package com.oby.autumn.boutika.configuration.filters;

import java.util.Map;

import javax.persistence.Query;

/**
 * Base class for a Filter that defines basic interface methods.
 */
public abstract class Filter {

	/**
	 * Should return <code>true</code> if the filter was set with restricting values and should be
	 * added to the query, <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if the filter should be added to the query.
	 */
	public abstract boolean isEmptyFilter();

	/**
	 * Generates the clause (the JPA-QL) for the filter.
	 * @return the JPA-QL string.
	 */
	public abstract String clause();

	/**
	 * add parameter values for this filter in the query.
	 * @param query the JPA query object.
	 */
	public abstract void parameters(Query query);

	public abstract Map<String, Object> parameters();

	public static String escape(final String in) {
		return in.replace('.', '_').replaceAll("[^\\w]", "");
	}

}