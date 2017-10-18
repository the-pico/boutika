package com.oby.autumn.boutika.configuration.filters;

import java.util.Date;
import java.util.Map;

import javax.persistence.Query;

import org.joda.time.DateTime;

import com.google.common.collect.Maps;


/**
 * Filter that adds a restriction for a date parameter to be included within to date columns in a
 * database table (e.g: the current date is within the validity period for a document).
 * This filter considers null date values for any of the two date column to mean no restriction.
 */
public class DateInPeriodFilter extends Filter {

	private final Date value;
	private final String startAlias;
	private final String endAlias;
	private final String startPath;
	private final String endPath;

	/**
	 * Complete constructor with all mandatory data.
	 * @param startPath the path (JPA-QL) to the start of period date.
	 * @param endPath the path (JPA-QL) to the end of period date.
	 * @param value the date value.
	 */
	public DateInPeriodFilter(final String startPath, final String endPath, final Date value) {
		this.value = value != null ? new DateTime(value).withTime(23,59,59,999).toDate() : null;
		this.startPath = startPath;
		this.endPath = endPath;
		this.endAlias = escape(endPath);
		this.startAlias = escape(startPath);
	}

	@Override
	public boolean isEmptyFilter() {
		return this.value == null;
	}

	private static final String clause = "( %s is null or %s <= :%s ) and ( %s is null or %s >= :%s )";

	@Override
	public String clause() {
		if (!this.isEmptyFilter()) {
			return String.format(clause, startPath, startPath, startAlias, endPath, endPath, endAlias);
		}
		return "";
	}

	@Override
	public void parameters(final Query query) {
		if (!this.isEmptyFilter()) {
			query.setParameter(startAlias, value);
			query.setParameter(endAlias, value);
		}
	}

	@Override
	public Map<String, Object> parameters() {
		final Map<String, Object> ret = Maps.newHashMap();
		if (!this.isEmptyFilter()) {
			ret.put(startAlias, value);
			ret.put(endAlias, value);
		}
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endAlias == null) ? 0 : endAlias.hashCode());
		result = prime * result + ((endPath == null) ? 0 : endPath.hashCode());
		result = prime * result
				+ ((startAlias == null) ? 0 : startAlias.hashCode());
		result = prime * result
				+ ((startPath == null) ? 0 : startPath.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		final DateInPeriodFilter other = (DateInPeriodFilter) obj;
		if (endAlias == null) {
			if (other.endAlias != null)
				return false;
		} else if (!endAlias.equals(other.endAlias))
			return false;
		if (endPath == null) {
			if (other.endPath != null)
				return false;
		} else if (!endPath.equals(other.endPath))
			return false;
		if (startAlias == null) {
			if (other.startAlias != null)
				return false;
		} else if (!startAlias.equals(other.startAlias))
			return false;
		if (startPath == null) {
			if (other.startPath != null)
				return false;
		} else if (!startPath.equals(other.startPath))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("DateInPeriodFilter [");
		if (value != null) {
			builder.append("value=");
			builder.append(value);
			builder.append(", ");
		}
		if (startAlias != null) {
			builder.append("startAlias=");
			builder.append(startAlias);
			builder.append(", ");
		}
		if (endAlias != null) {
			builder.append("endAlias=");
			builder.append(endAlias);
			builder.append(", ");
		}
		if (startPath != null) {
			builder.append("startPath=");
			builder.append(startPath);
			builder.append(", ");
		}
		if (endPath != null) {
			builder.append("endPath=");
			builder.append(endPath);
		}
		builder.append("]");
		return builder.toString();
	}
}
