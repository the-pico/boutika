package com.oby.autumn.boutika.configuration.pagedList;


/**
 * Dummy implementation of a {@link Transformer} and {@link DetailedTransformer}, mostly used for
 * testing purposes.
 */
public class NoopTransformer implements Transformer<Object[], Object> {

	@Override
	public String id(final Object[] row) {
		return String.valueOf(row[0]);
	}

	@Override
	public Object[] cells(final Object[] row) {
		final String[] cells = new String[row.length - 1];
		for (int i = 1; i < row.length; i++) {
			cells[i - 1] = asString(row[i]);
		}
		return cells;
	}

	private String asString(final Object o) {
		if (o != null) {
			return String.valueOf(o);
		} else {
			return null;
		}
	}
}
