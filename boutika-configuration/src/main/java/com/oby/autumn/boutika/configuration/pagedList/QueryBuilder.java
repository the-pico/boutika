package com.oby.autumn.boutika.configuration.pagedList;

import com.oby.autumn.boutika.configuration.filters.Filter;
import com.oby.autumn.boutika.configuration.filters.FilterBuilder;
import com.oby.autumn.boutika.configuration.filters.OrderByBuilder;

/**
 * Base class for paged list and export query builders implementations.
 */
public abstract class QueryBuilder {

	protected final FilterBuilder fb;
	protected final OrderByBuilder ob;

	public QueryBuilder() {
		this.fb = new FilterBuilder();
		this.ob = new OrderByBuilder();
	}

	public void filterOn(final Filter filter) {
		this.fb.add(filter);
	}

	public void sortOn(final String sortColName, final String sortDirection) {
		this.ob.addColumn(sortColName, sortDirection);
	}

}