package com.oby.autumn.boutika.configuration.filters;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

/**
 * Builder that generates a clause from a list of {@link Filter}, adding parenthesis, OR and AND
 * keywords.
 */
public class FilterBuilder {

	private final List<Filter> filters = new LinkedList<Filter>();


	public static FilterBuilder copyOf(final FilterBuilder filterBuilder) {
		final FilterBuilder copy = new FilterBuilder();
		copy.filters.addAll(filterBuilder.filters);
		return copy;
	}

	public String createQueryFilter() {
		final StringBuilder queryFilter = new StringBuilder();
		if (isNotEmpty(filters)) {
			queryFilter.append("where (");
			for (int i = 0; i < filters.size() - 1; i++) {
				queryFilter.append(filters.get(i).clause());
				queryFilter.append(") and (");
			}
			queryFilter.append(filters.get(filters.size() - 1).clause());
			queryFilter.append(")");
		}
		return queryFilter.toString();
	}

	public void addQueryParameter(final Query query) {
		for (final Filter filter : filters) {
			filter.parameters(query);
		}
	}

	public final void addAtFirst(final Filter filter) {
		if (filter != null && filter.isEmptyFilter()) {
			return;
		}
		filters.add(0, filter);
	}

	public final void add(final Filter filter) {
		if ( filter == null || filter.isEmptyFilter() ) return;
		filters.add(filter);
	}

	public final void add(final List<Filter> filters) {
		for ( final Filter f : filters ) this.add(f);
	}

	public List<Filter> getFilters() {
		return filters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filters == null) ? 0 : filters.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FilterBuilder other = (FilterBuilder) obj;
		if (filters == null) {
			if (other.filters != null)
				return false;
		} else if (!filters.equals(other.filters))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("FilterBuilder [");
		if (filters != null) {
			builder.append("filters=");
			builder.append(filters);
		}
		builder.append("]");
		return builder.toString();
	}
}
