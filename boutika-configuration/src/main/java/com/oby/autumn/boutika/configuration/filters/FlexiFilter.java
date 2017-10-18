package com.oby.autumn.boutika.configuration.filters;

/**
 * Flexible parameterized {@link Filter} implementation that support different operators and data
 * types. <br/>
 * The operator can be any valid JPA-QL operator (<code>=</code>, <code>>=</code>, <code>!=</code>,
 * ...) or the special operator <code>==</code>, only applicable for <code>String</code> parameters
 * that means "equals ignoring case".
 * 
 * @param <T> the java type for the value and column.
 */
public class FlexiFilter<T> extends ParamableFilter {

	private final T value;
	private final String alias;
	private final String path;
	private final String operator;

	public FlexiFilter(final String path, final String operator, final T[] values) {
		this(path, operator, (values != null && values.length > 0) ? values[0] : null);
	}

	public FlexiFilter(final String path, final String operator, final T value) {
		this(path, operator, value, escape(path));
	}

	public FlexiFilter(final String path, final String operator, final T value, final String alias) {
		//this(path, escape(path), operator, value);
		this.value = value;
		this.path =  path;
		this.alias = alias;
		this.operator = operator;
		setParameter(alias,value);
	}

	@Override
	public boolean isEmptyFilter() {
		return this.value == null;
	}

	private static final String clause = "%s %s :%s";
	private static final String upperClause = "upper(%s) = upper(:%s)";

	@Override
	public String clause() {
		String query = "";
		if (!this.isEmptyFilter()) {
			if (this.operator.equals("==")) {
				query = String.format(upperClause, path, alias);
			} else {
				query = String.format(clause, path, operator, alias);
			}
		}
		return query;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		final FlexiFilter<T> other = (FlexiFilter<T>) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
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
		builder.append("FlexiFilter [");
		if (value != null) {
			builder.append("value=");
			builder.append(value);
			builder.append(", ");
		}
		if (alias != null) {
			builder.append("alias=");
			builder.append(alias);
			builder.append(", ");
		}
		if (path != null) {
			builder.append("path=");
			builder.append(path);
			builder.append(", ");
		}
		if (operator != null) {
			builder.append("operator=");
			builder.append(operator);
		}
		builder.append("]");
		return builder.toString();
	}
}
