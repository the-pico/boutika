package com.oby.autumn.boutika.configuration.filters;

import java.util.Map;

import javax.persistence.Query;

import com.google.common.collect.Maps;

/**
 * A base class used to combine two filters, for example using an 'OR' or an 'AND' clause.
 */
public class FilterCombination extends Filter{
	private final Filter filter1;
	private final Filter filter2;
	private final String clauseFormat;


	/**
	 *
	 * @param filter1 the first filter to combinate
	 * @param filter2 the second filter to combinate
	 * @param clauseFormat the format of the close to use, it must contains two "%s" where the two clauses
	 * of the filters to combinate will be inserted
	 */
	public FilterCombination(Filter filter1, Filter filter2, String clauseFormat) {
		this.filter1 = filter1;
		this.filter2 = filter2;
		this.clauseFormat = clauseFormat;
	}

	@Override
	public boolean isEmptyFilter() {
		return filter1.isEmptyFilter() && filter2.isEmptyFilter();
	}

	@Override
	public String clause() {
		if (filter1.isEmptyFilter() && filter2.isEmptyFilter()) {
			return "";
		} else if (filter1.isEmptyFilter() && !filter2.isEmptyFilter()) {
			return filter2.clause();
		} else if (!filter1.isEmptyFilter() && filter2.isEmptyFilter()) {
			return filter1.clause();
		}
		return String.format(clauseFormat, filter1.clause(), filter2.clause());
	}

	@Override
	public void parameters(final Query query) {
		if (!filter1.isEmptyFilter()) {
			filter1.parameters(query);
		}
		if (!filter2.isEmptyFilter()) {
			filter2.parameters(query);
		}
	}

	@Override
	public Map<String, Object> parameters() {
		final Map<String, Object> ret = Maps.newHashMap();
		if (!filter1.isEmptyFilter()) {
			ret.putAll(filter1.parameters());
		}
		if (!filter2.isEmptyFilter()) {
			ret.putAll(filter2.parameters());
		}
		return ret;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		FilterCombination that = (FilterCombination) o;

		if (!clauseFormat.equals(that.clauseFormat)) {
			return false;
		}
		if (!filter1.equals(that.filter1)) {
			return false;
		}
		if (!filter2.equals(that.filter2)) {
			return false;
		}

		return true;
	}

	public Filter getFilter1() {
		return filter1;
	}

	public Filter getFilter2() {
		return filter2;
	}

	@Override
	public int hashCode() {
		int result = filter1.hashCode();
		result = 31 * result + filter2.hashCode();
		result = 31 * result + clauseFormat.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "FilterCombinaison[" +
				"filter1=" + filter1 +
				", filter2=" + filter2 +
				", clauseFormat='" + clauseFormat + '\'' +
				']';
	}
}