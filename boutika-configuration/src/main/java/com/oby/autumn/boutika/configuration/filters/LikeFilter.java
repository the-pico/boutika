package com.oby.autumn.boutika.configuration.filters;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;

/**
 * Filter that generates a <code>path LIKE %value%</code> clause (the <code>%</code> characters are
 * added by the filter).
 */
public class LikeFilter extends ParamableFilter {

	private final String value;
	private final String alias;
	private final List<String> paths;

	public LikeFilter(final String path, final String[] values) {
		this(ImmutableList.of(path), (values != null && values.length > 0) ? values[0] : null);
	}

	public LikeFilter(final String path, final String value) {
		this(ImmutableList.of(path), value);
	}

	public LikeFilter(final List<String> paths, final String value) {
		this.value = (value != null) ? value.trim().replace(' ', '%').toUpperCase() : value;
		this.paths = paths;
		if ( this.paths.size() == 1 ) {
			this.alias = escape(this.paths.iterator().next());
		} else {
			StringBuilder buf = null;
			for ( final String path : paths ) {
				if ( buf == null ) buf = new StringBuilder();
				else buf.append('_');
				buf.append(escape(path));
			}
			this.alias = buf.toString();
		}
		setParameter(this.alias, "%" + this.value + "%");
	}

	private static final String clause = "upper(%s) like :%s";

	@Override
	public String clause() {
		if (this.isEmptyFilter()) {
			return "";
		} else if (this.paths.size() == 1) {
			return String.format(clause, this.paths.get(0), this.alias);
		} else {
			final StringBuilder buf = new StringBuilder();
			buf.append("upper(concat(");
			for ( int i = 0; i < this.paths.size(); i++ ) {
				if ( i > 0 ) buf.append(",");
				buf.append(this.paths.get(i));
			}
			buf.append(")) like :");
			buf.append(this.alias);
			return buf.toString();
		}
	}

	@Override
	public boolean isEmptyFilter() {
		return StringUtils.isEmpty(value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paths == null) ? 0 : paths.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LikeFilter))
			return false;
		final LikeFilter other = (LikeFilter) obj;
		if (paths == null) {
			if (other.paths != null)
				return false;
		} else if (!paths.equals(other.paths))
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
		return String.format("LikeFilter [value=%s, alias=%s, paths=%s]", value, alias, paths);
	}
}
