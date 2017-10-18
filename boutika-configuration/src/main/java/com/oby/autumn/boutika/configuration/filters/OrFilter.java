package com.oby.autumn.boutika.configuration.filters;

/**
 * Special implementation of a {@link Filter} used to combine two other filters within an OR clause.
 */
public class OrFilter extends FilterCombination {

	public OrFilter(final Filter filter1, final Filter filter2) {
		super(filter1, filter2, "%s or %s");
	}
}
