package com.oby.autumn.boutika.configuration.filters;

import java.util.Collections;
import java.util.Map;

import javax.persistence.Query;

/**
 * Filter that generates a <code>path IS NULL</code> clause.
 */
public class IsNullFilter extends Filter {

	private final String path;

	private final Boolean isNotNull;

	public IsNullFilter(final String path) {
		this.path = path;
		this.isNotNull = false;
	}

	public IsNullFilter(final String path, final Boolean isNotNull) {
		this.path = path;
		this.isNotNull = isNotNull;
	}

	@Override
	public boolean isEmptyFilter() {
		return isNotNull == null;
	}

	@Override
	public String clause() {
		if (isNotNull != null)
			return path + (isNotNull ? " is not null" : " is null");
		else
			return "";
	}

	@Override
	public void parameters(final Query query) {}

	@Override
	public Map<String, Object> parameters() {return Collections.emptyMap();}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		final IsNullFilter other = (IsNullFilter) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (isNotNull == null) {
			if (other.isNotNull != null)
				return false;
		} else if (!isNotNull.equals(other.isNotNull))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("IsNullFilter [");
		if (path != null) {
			builder.append("path=");
			builder.append(path);
			builder.append(",isNotNull=");
			builder.append(isNotNull);
		}
		builder.append("]");
		return builder.toString();
	}
}
