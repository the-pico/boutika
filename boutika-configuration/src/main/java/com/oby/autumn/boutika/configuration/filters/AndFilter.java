package com.oby.autumn.boutika.configuration.filters;

/**
 * Special implementation of a {@link Filter} used to combine two other filters within an AND clause.
 * <br />
 * WARNING : in most case the use of this class is useless, as a {@link FilterBuilder} already generates "and"s between
 * the given filters. But in some case it can still be usefull, for example if an "and" clause has to be embeded within
 * and "pr" clause ( e.g. : " A or ( B and C ) ". Or if for another reason you need to only have a single filter
 * containing two others.
 */
public class AndFilter extends FilterCombination {

	public AndFilter(final Filter filter1, final Filter filter2) {
		super(filter1, filter2, "(%s and %s)");
	}
}