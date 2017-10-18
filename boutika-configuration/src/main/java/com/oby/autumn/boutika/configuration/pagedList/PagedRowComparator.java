package com.oby.autumn.boutika.configuration.pagedList;

import java.util.Comparator;

/**
 * Comparator implementation for {@link PagedRow}s used for sorting.
 */
public class PagedRowComparator implements Comparator<PagedRow> {

	private final int index;
	private final boolean asc;

	public PagedRowComparator(final int index, final String order) {
		this.index = index;
		this.asc = "desc".equals(order) ? false : true;
	}

	@Override
	public int compare(final PagedRow o1, final PagedRow o2) {
		final Object left = asc ? o1.getCell()[index] : o2.getCell()[index];
		final Object right = asc ? o2.getCell()[index] : o1.getCell()[index];
		if ( left == null && right == null ) return 0;
		else if ( left != null && right == null ) return -1;
		else if ( left == null && right != null ) return 1;
		else return String.valueOf(left).compareTo(String.valueOf(right));
	}

}
