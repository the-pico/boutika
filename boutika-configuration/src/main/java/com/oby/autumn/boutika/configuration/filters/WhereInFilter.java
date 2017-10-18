package com.oby.autumn.boutika.configuration.filters;

import java.util.Arrays;
import java.util.List;

/**
 * Parameterized filter used to generate a <code>path IN (value1, value2, &hellip;)</code> clause.
 *
 * @param <T> the java type for values and column.
 */
public class WhereInFilter<T> extends ParamableFilter {
	protected final List<T> inValues;
	protected final String parameterName;
	private final String hqlPath;

	public WhereInFilter(final String hqlPath, @SuppressWarnings("unchecked") final T... inValues) {
		this(hqlPath, (inValues != null) ? Arrays.asList(inValues) : null);
	}

	public WhereInFilter(final String hqlPath, final List<T> inValues) {
		this.inValues = inValues;
		this.parameterName = escape(hqlPath);
		setParameter(parameterName,this.inValues);
		this.hqlPath = hqlPath;
	}

	@Override
	public String clause() {
		final StringBuilder filterRequest = new StringBuilder();
		if (!isEmptyFilter()) {
			filterRequest.append(hqlPath + " in (:" + parameterName + ")");
		}
		return filterRequest.toString();
	}

	@Override
	public boolean isEmptyFilter() {
		return inValues == null || inValues.isEmpty() || inValues.contains(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hqlPath == null) ? 0 : hqlPath.hashCode());
		result = prime * result
				+ ((inValues == null) ? 0 : inValues.hashCode());
		result = prime * result
				+ ((parameterName == null) ? 0 : parameterName.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final WhereInFilter<T> other = (WhereInFilter<T>) obj;
		if (hqlPath == null) {
			if (other.hqlPath != null)
				return false;
		} else if (!hqlPath.equals(other.hqlPath))
			return false;
		if (inValues == null) {
			if (other.inValues != null)
				return false;
		} else if (!inValues.equals(other.inValues))
			return false;
		if (parameterName == null) {
			if (other.parameterName != null)
				return false;
		} else if (!parameterName.equals(other.parameterName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("WhereInFilter [");
		if (inValues != null) {
			builder.append("inValues=");
			builder.append(inValues);
			builder.append(", ");
		}
		if (parameterName != null) {
			builder.append("parameterName=");
			builder.append(parameterName);
			builder.append(", ");
		}
		if (hqlPath != null) {
			builder.append("hqlPath=");
			builder.append(hqlPath);
		}
		builder.append("]");
		return builder.toString();
	}
}
