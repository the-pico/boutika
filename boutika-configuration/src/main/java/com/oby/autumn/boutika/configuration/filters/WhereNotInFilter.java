package com.oby.autumn.boutika.configuration.filters;

import java.util.Arrays;
import java.util.List;

/**
 * Parameterized filter used to generate a <code>path IN (value1, value2, &hellip;)</code> clause.
 * 
 * @param <T>
 *            the java type for values and column.
 */
public class WhereNotInFilter<T> extends ParamableFilter {
	protected final List<T> notInValues;
	protected final String parameterName;
	private final String hqlPath;

	public WhereNotInFilter(final String hqlPath, @SuppressWarnings("unchecked") final T... notInValues) {
		this(hqlPath, (notInValues != null) ? Arrays.asList(notInValues) : null);
	}

	public WhereNotInFilter(final String hqlPath, final List<T> notInValues) {
		this.notInValues = notInValues;
		this.parameterName = escape(hqlPath);
		setParameter(parameterName, this.notInValues);
		this.hqlPath = hqlPath;
	}

	@Override
	public String clause() {
		final StringBuilder filterRequest = new StringBuilder();
		if (!isEmptyFilter()) {
			filterRequest.append(hqlPath + " not in (:" + parameterName + ")");
		}
		return filterRequest.toString();
	}

	@Override
	public boolean isEmptyFilter() {
		return notInValues == null || notInValues.isEmpty() || notInValues.contains(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hqlPath == null) ? 0 : hqlPath.hashCode());
		result = prime * result + ((notInValues == null) ? 0 : notInValues.hashCode());
		result = prime * result + ((parameterName == null) ? 0 : parameterName.hashCode());
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
		final WhereNotInFilter<T> other = (WhereNotInFilter<T>) obj;
		if (hqlPath == null) {
			if (other.hqlPath != null)
				return false;
		} else if (!hqlPath.equals(other.hqlPath))
			return false;
		if (notInValues == null) {
			if (other.notInValues != null)
				return false;
		} else if (!notInValues.equals(other.notInValues))
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
		builder.append("WhereNotInFilter [");
		if (notInValues != null) {
			builder.append("notInValues=");
			builder.append(notInValues);
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
