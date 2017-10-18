package com.oby.autumn.boutika.configuration.pagedList;


import java.util.Date;
import java.util.List;

/**
 * Utility class providing basic transformation functions that can be used by implementations of a
 * {@link Transformer}.<br/>
 */
public abstract class BaseTransformer {

	public String convert(final Object o) {
		if (o != null) {
			return String.valueOf(o);
		} else {
			return null;
		}
	}

	public String upper(final String val) {
		if (val == null) {
			return null;
		} else {
			return val.toUpperCase();
		}
	}

	public String concat(final String sep, final String... values) {
		if (values == null) {
			return null;
		}
		StringBuilder b = null;
		for (final String s : values) {
			if (s == null) {
				continue;
			}
			if (b == null) {
				b = new StringBuilder();
			} else {
				b.append(sep);
			}
			b.append(s);
		}
		return (b == null) ? null : b.toString();
	}

	public String nvl(final Object test, final Object ifNotNull, final Object ifNull) {
		return (test == null) ? convert(ifNull) : convert(ifNotNull);
	}

	protected String booleanTo1or0(final Object o) {
		return ((Boolean) o) ? "1" : "0";
	}

	protected Date objectToDate(final Object val) {
		Date value = null;
		if (val instanceof Long) {
			value = new Date((Long) val);
		} else if (val instanceof Date) {
			value = (Date) val;
		}
		return value;
	}

	protected String dottedList(final List<Object[]> details, final int column) {
		if (details.isEmpty()) {
			return "";
		}
		if (details.size() == 1) {
			return convert(details.get(0)[column]);
		}
		return convert(details.get(0)[column]) + ", ...";
	}
}
