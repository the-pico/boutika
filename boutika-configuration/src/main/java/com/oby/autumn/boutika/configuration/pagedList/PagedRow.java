package com.oby.autumn.boutika.configuration.pagedList;

/**
 * Representation of a row of data in a {@link PagedList}, composed of an array of data and a row
 * identifier. <br />
 * Attention: the fields are named after jqgrid expected data format. Do not update !
 */
public final class PagedRow {

	/**
	 * id: row id
	 */
	private final Object id;
	private final Object[] cell;

	public PagedRow(final Object rowId, final Object[] cell) {
		this.id = rowId;
		this.cell = cell;
	}

	public Object getId() {
		return id;
	}

	public Object[] getCell() {
		return cell;
	}
}
