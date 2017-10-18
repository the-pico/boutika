package com.oby.autumn.boutika.configuration.filters;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.joda.time.DateTime;

import com.google.common.collect.Maps;

/**
 * Filter that adds a restriction for a date column in a table to be included in an interval defined
 * by two dates passed as parameters (e.g: the order's creation date is last week).<br/>
 * If the option {@link DateRangeFilter#isExcludedRange(boolean)} is set, the filter will function
 * the opposite: the date has to be outside of the date range defined by the parameters.<br/>
 * The filter will set time to 00h00 for the minimum date and 23h59 for the maximum.<br/>
 * If any of the dates passed as parameters is null, no restriction will be generated for this side.<br/>
 */
public class DateRangeFilter extends Filter {

	private final Date min;
	private final Date max;
	private final String minAlias;
	private final String maxAlias;
	private final String path;
	private Boolean excludedRange;


	/**
	 * will check the first non null path
	 */
	public DateRangeFilter(List<String> paths, Date[] values) {
		this(nvl(paths), values);
	}

	static String nvl(List<String> paths) {
		String result = paths.get(paths.size()-1);
		for(int i = paths.size()-2; i>=0; i--) {
			result=String.format("nvl(%s,%s)",paths.get(i), result);
		}
		return result;
	}

	public DateRangeFilter(final String path, final Date[] values) {
		this(path, (values != null && values.length > 0) ? values[0] : null, (values != null && values.length > 1) ? values[1] : null);
	}

	public DateRangeFilter(final String path, final Date min, final Date max) {
		this.min = (min != null) ? time(min, 0, 0, 0, 0) : null;
		this.max = (max != null) ? time(max, 23, 59, 59, 999) : null;
		this.path = path;
		this.minAlias = escape(path) + "_min";
		this.maxAlias = escape(path) + "_max";
		this.excludedRange = false;
	}

	public DateRangeFilter(final String path, final Date min, final int daySpan) {
		this(path, min, (min != null) ? new DateTime(min).plusDays(daySpan-1).toDate() : null);
	}

	/**
	 * Allow to search date outside of the range.
	 * @param excludedRange If false, search date in range. If true, search date outside of range.
	 * @return this DateRangeFilter
	 */
	public DateRangeFilter isExcludedRange(final boolean excludedRange) {
		this.excludedRange = excludedRange;
		return this;
	}

	private static Date time(final Date d, final int hour, final int min, final int sec, final int millis) {
		return new DateTime(d).withTime(hour, min, sec, millis).toDate();
	}

	@Override
	public boolean isEmptyFilter() {
		return this.min == null && this.max == null;
	}

	private static final String max_clause = "%s <= :%s";
	private static final String min_clause = "%s >= :%s";

	@Override
	public String clause() {
		if (this.max == null && this.min == null) {
			return "";
		} else if (!excludedRange) {
			final StringBuilder buf = new StringBuilder();
			if (this.min != null) buf.append(String.format(min_clause, this.path, this.minAlias));
			if (this.max != null && this.min != null) buf.append(" and ");
			if (this.max != null) buf.append(String.format(max_clause, this.path, this.maxAlias));
			return buf.toString();
		} else {
			final StringBuilder buf = new StringBuilder();
			if (this.min != null) buf.append(String.format(max_clause, this.path, this.minAlias));
			if (this.max != null && this.min != null) buf.append(" or ");
			if (this.max != null) buf.append(String.format(min_clause, this.path, this.maxAlias));
			return buf.toString();
		}
	}

	@Override
	public void parameters(final Query query) {
		if (this.min != null) query.setParameter(this.minAlias, this.min);
		if (this.max != null) query.setParameter(this.maxAlias, this.max);
	}

	@Override
	public Map<String, Object> parameters() {
		final Map<String, Object> ret = Maps.newHashMap();
		if (this.min != null) ret.put(this.minAlias, this.min);
		if (this.max != null) ret.put(this.maxAlias, this.max);
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result
				+ ((maxAlias == null) ? 0 : maxAlias.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result
				+ ((minAlias == null) ? 0 : minAlias.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		final DateRangeFilter other = (DateRangeFilter) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (maxAlias == null) {
			if (other.maxAlias != null)
				return false;
		} else if (!maxAlias.equals(other.maxAlias))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (minAlias == null) {
			if (other.minAlias != null)
				return false;
		} else if (!minAlias.equals(other.minAlias))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("DateRangeFilter [");
		if (min != null) {
			builder.append("min=");
			builder.append(min);
			builder.append(", ");
		}
		if (max != null) {
			builder.append("max=");
			builder.append(max);
			builder.append(", ");
		}
		if (minAlias != null) {
			builder.append("minAlias=");
			builder.append(minAlias);
			builder.append(", ");
		}
		if (maxAlias != null) {
			builder.append("maxAlias=");
			builder.append(maxAlias);
			builder.append(", ");
		}
		if (path != null) {
			builder.append("path=");
			builder.append(path);
		}
		builder.append("]");
		return builder.toString();
	}

}
