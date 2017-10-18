package com.oby.autumn.boutika.configuration.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder used to generate the order by clause.
 */
public class OrderByBuilder {

	public static final OrderByBuilder NULL_BUILDER = new OrderByBuilder();
	private final List<String> sortColNames = new ArrayList<String>();
	private final List<String> sortDirections = new ArrayList<String>();

	public OrderByBuilder() {
	}

	public OrderByBuilder(final String sortColName, final String sortDirection) {
		addColumn(sortColName, sortDirection);
	}

	public String build(final String alias) {
		final StringBuilder result = new StringBuilder();
		for (int index = 0; index < sortColNames.size(); index++) {
			result.append(index == 0 ? " order by " : ", ");
			result.append(String.format("%s.%s %s", alias, sortColNames.get(index), sortDirections.get(index)));
		}
		return result.toString();
	}

	public String build() {
		final StringBuilder result = new StringBuilder();
		for (int index = 0; index < sortColNames.size(); index++) {
			result.append(index == 0 ? " order by " : ", ");
			result.append(String.format("%s %s", sortColNames.get(index), sortDirections.get(index)));
		}
		return result.toString();
	}

	public final void addColumn(final String sortColName, final String sortDirection) {
		if (sortColName != null && sortColName.trim().length() > 0 && !this.sortColNames.contains(sortColName)) {
			this.sortColNames.add(sortColName);
			this.sortDirections.add(sortDirection);
		}
	}

}
