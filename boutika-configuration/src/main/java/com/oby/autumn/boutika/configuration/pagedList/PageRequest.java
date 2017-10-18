package com.oby.autumn.boutika.configuration.pagedList;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Representation of the request for a page in a {@link PagedList}, holding the {@link Criterias},
 * page number, page size and sort information.
 */
public class PageRequest {

	private Integer page;
	private Integer requestedRowsByPage;
	private String sortColName;
	private String sortDirection;
	private final Criterias criterias = new Criterias();

	public PageRequest() {}

	/**
	 * Page Request constructed from criteria with 65000 rows per page.
	 * To be used for excel exports typically.
	 */
	public static PageRequest all(final List<Criteria> criterias) {
		final PageRequest req = new PageRequest(1, 65000);
		req.setCriterias(criterias);
		return req;
	}

	/**
	 * Page Request constructed from criteria with 20 row per page and the first page.
	 */
	public static PageRequest page(final Criteria... criterias) {
		final PageRequest req = new PageRequest(1, 20);
		if ( criterias != null ) req.setCriterias(Arrays.asList(criterias));
		return req;
	}

	/**
	 * Page Request constructed from criteria with 20 row per page and the first page.
	 */
	public static PageRequest page(final List<Criteria> criterias) {
		final PageRequest req = new PageRequest(1, 20);
		req.setCriterias(criterias);
		return req;
	}

	public PageRequest(final Integer page, final Integer requestedRowsByPage) {
		super();
		this.page = page;
		this.requestedRowsByPage = requestedRowsByPage;
	}

	/**
	 * Calculates the beginning offset for this request in the dataset (i.e the index of
	 * the first row to return).
	 * 
	 * @return the offset;
	 */
	public int getOffset() {
		return (page - 1) * requestedRowsByPage;
	}

	public int getEndset() {
		return getOffset() + requestedRowsByPage;
	}

	public String[] criteriaValuesAsString(final String property) {
		return criterias.valuesAsString(property);
	}

	public <T extends Enum<T>> T[] criteriaValuesAsEnum(final String property, final Class<T> enumType) {
		return criterias.valuesAsEnum(property,enumType);
	}

	public Long[] criteriaValuesAsLong(final String property) {
		return criterias.valuesAsLong(property);
	}

	public Date[] criteriaValuesAsDate(final String property) {
		return criterias.valuesAsDate(property);
	}

	public Boolean criteriaValueAsBoolean(final String property) {
		return criterias.valueAsBoolean(property);
	}

	public Integer[] criteriaValuesAsInteger(final String property) {
		return criterias.valuesAsInteger(property);
	}

	public Long criteriaValueAsLong(final String property) {
		return criterias.valueAsLong(property);
	}

	public void addCriteria(final Criteria c) {
		this.criterias.put(c);
	}

	public void removeCriteria(final String pname) {
		criterias.remove(pname);
	}

	public void sort(final String col, final String dir) {
		this.sortColName = col;
		this.sortDirection = dir;
	}

	public boolean hasSort() {
		return this.sortColName != null;
	}

	public int sortIndex(final int defaultsTo) {
		if (!hasSort()) return defaultsTo;
		try { return Integer.parseInt(this.sortColName); }
		catch (final NumberFormatException nfe) {return defaultsTo;}
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(final Integer page) {
		this.page = page;
	}

	public Integer getRequestedRowsByPage() {
		return requestedRowsByPage;
	}

	public void setRequestedRowsByPage(final Integer requestedRowsByPage) {
		this.requestedRowsByPage = requestedRowsByPage;
	}

	public String getSortColName() {
		return sortColName;
	}

	public void setSortColName(final String sortColName) {
		this.sortColName = sortColName;
	}

	public String getSortDirection() {
		return sortDirection != null ? sortDirection : "asc";
	}

	public void setSortDirection(final String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public Criterias getCriterias() {
		return criterias;
	}

	public void setCriterias(final List<Criteria> criterias) {
		this.criterias.addAll(criterias);
	}

	@Override
	public String toString() {
		return "PageRequest [page=" + page + ", requestedRowsByPage=" + requestedRowsByPage + ", sortColName="
				+ sortColName + ", sortDirection=" + sortDirection + ", criterias=" + criterias + "]";
	}

	public boolean hasCriteria(final String property) {
		return criterias.hasCriteria(property);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((criterias == null) ? 0 : criterias.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime
				* result
				+ ((requestedRowsByPage == null) ? 0 : requestedRowsByPage
						.hashCode());
		result = prime * result
				+ ((sortColName == null) ? 0 : sortColName.hashCode());
		result = prime * result
				+ ((sortDirection == null) ? 0 : sortDirection.hashCode());
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
		final PageRequest other = (PageRequest) obj;
		if (criterias == null) {
			if (other.criterias != null)
				return false;
		} else if (!criterias.equals(other.criterias))
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (requestedRowsByPage == null) {
			if (other.requestedRowsByPage != null)
				return false;
		} else if (!requestedRowsByPage.equals(other.requestedRowsByPage))
			return false;
		if (sortColName == null) {
			if (other.sortColName != null)
				return false;
		} else if (!sortColName.equals(other.sortColName))
			return false;
		if (sortDirection == null) {
			if (other.sortDirection != null)
				return false;
		} else if (!sortDirection.equals(other.sortDirection))
			return false;
		return true;
	}
}
