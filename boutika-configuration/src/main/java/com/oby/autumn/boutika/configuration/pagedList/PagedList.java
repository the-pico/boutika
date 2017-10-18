package com.oby.autumn.boutika.configuration.pagedList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Representation of a paged list: holds the list of results ({@link PagedRow}s) for the page and
 * supplemental informations : current page, total number of pages, total number of rows.<br/>
 * Attention: The fields are following jqgrid naming convention ! Do not change.
 */
@JsonPropertyOrder(alphabetic = true)
public final class PagedList {

	/**
	 * Current page.
	 */
	private final Integer page;

	/**
	 * Total number of pages found.
	 */
	private final Long total;

	/**
	 * Total number of rows found (independently of pagination).
	 */
	private final Long records;

	/**
	 * List of objects returned.
	 */
	private final List<PagedRow> rows;

	private final Map<String, Object> userdata = new HashMap<String, Object>();

	public PagedList( //
			final Integer pageIndex, //
			final Integer pageSize, //
			final Long totalNumberOfRows, //
			final List<PagedRow> rows) {
		this.page = pageIndex;
		this.rows = rows;
		this.records = totalNumberOfRows;

		if (this.records == 0L) {
			this.total = 1L;
		} else {
			if (pageSize != null) {
				final long guess = this.records / pageSize;
				if (this.records % pageSize == 0) {
					this.total = guess;
				} else {
					this.total = guess + 1;
				}
			} else {
				this.total = null;
			}
		}
	}

	public void addUserdata(final String key, final Object value) {
		userdata.put(key, value);
	}

	public Map<String, Object> getUserdata() {
		return userdata;
	}

	public Integer getPage() {
		return page;
	}

	public Long getTotal() {
		return total;
	}

	public List<PagedRow> getRows() {
		return rows;
	}

	public Long getRecords() {
		return records;
	}
}
