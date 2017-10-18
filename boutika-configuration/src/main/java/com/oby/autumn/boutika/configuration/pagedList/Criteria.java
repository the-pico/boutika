package com.oby.autumn.boutika.configuration.pagedList;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;

/**
 * Representation of a Criteria used to filter a list of data.<br/>
 * A criteria is composed of a property name to filter and a list of values to accept.
 */
public class Criteria implements Serializable {

	private static final long serialVersionUID = -1523430713302933713L;
	private static final String[] _empty = new String[0];
	public static final String DATE_PATTERN = "dd/MM/yyyy";

	private String property;
	private String[] values;

	public Criteria() {
	}

	public Criteria(final String property, final Long value) {
		this.property = property;
		this.values = (value != null) ? new String[] { String.valueOf(value) } : _empty;
	}

	public static Criteria of(final Enum<?> property, final Long... values) {
		return of(property.name(), values);
	}

	public static Criteria of(final String property, final Long... values) {
		return new Criteria(property, values);
	}

	public static Criteria of(final String property, final String... values) {
		return new Criteria(property, values);
	}

	public static Criteria of(final Enum<?> property, final String... values) {
		return of(property.name(), values);
	}

	public Criteria(final String property) {
		this.property = property;
		this.values = _empty;
	}

	public Criteria(final String property, final String... values) {
		this.setProperty(property);
		this.setValues(values);
	}

	public Criteria(final String property, final Long... values) {
		this.property = property;
		if (values != null) {
			boolean nullsOnly = true;
			this.values = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null) {
					this.values[i] = String.valueOf(values[i]);
					nullsOnly = false;
				}
			}
			if (nullsOnly) {
				this.values = _empty;
			}
		}
	}

	public Criteria(final String property, final List<String> values) {
		this(property, values == null ? _empty : values.toArray(new String[values.size()]));
	}

	public boolean hasValues() {
		return this.values != null && this.values.length > 0;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(final String property) {
		this.property = property;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(final String[] values) {
		if (values != null) {
			boolean nullsOnly = true;
			this.values = new String[values.length];
			for (int i = 0; i < values.length; i++) {
				if (StringUtils.isNotBlank(values[i])) {
					this.values[i] = values[i].trim();
					nullsOnly = false;
				}
			}
			if (nullsOnly) {
				this.values = _empty;
			}
		}
	}

	public Long[] valuesAsLong() {
		List<Long> ret = null;
		if (values != null) {
			ret = new LinkedList<Long>();
			for (final String val : values) {
				if (StringUtils.isNumeric(val) && StringUtils.isNotEmpty(val)) {
					ret.add(Long.valueOf(val));
				}
			}
		}
		return (ret == null) ? null : ret.toArray(new Long[ret.size()]);
	}

	public Boolean valueAsBoolean() {
		Boolean ret = null;
		if (values != null) {
			for (final String val : values) {
				if ("true".equals(val) || "1".equals(val)) {
					if (ret == null) {
						ret = Boolean.TRUE;
					} else if (!ret) {
						ret = null;
					}
				} else if ("false".equals(val) || "0".equals(val)) {
					if (ret == null) {
						ret = Boolean.FALSE;
					} else if (ret) {
						ret = null;
					}
				}
			}
		}
		return ret;
	}

	public Boolean[] valuesAsBoolean() {
		Boolean[] ret = null;
		if (values != null) {
			ret = new Boolean[values.length];
			for (int i = 0; i < values.length; i++) {
				if ("true".equals(values[i]) || "1".equals(values[i])) {
					if (ret[i] == null) {
						ret[i] = Boolean.TRUE;
					} else if (!ret[i]) {
						ret[i] = null;
					}

				} else if ("false".equals(values[i]) || "0".equals(values[i])) {
					if (ret[i] == null) {
						ret[i] = Boolean.FALSE;
					} else if (ret[i]) {
						ret[i] = null;
					}
				}
			}
		}
		return ret;
	}

	public Date[] valuesAsDate() {
		Date[] ret = null;
		if (values != null) {
			ret = new Date[values.length];
			for (int i = 0; i < values.length; i++) {
				ret[i] = (values[i] != null && values[i].trim().length() > 0) ? values[i].matches("\\d+") ? new Date(
						Long.valueOf(values[i])) : DateTimeFormat.forPattern(DATE_PATTERN).parseDateTime(values[i])
						.toDate() : null;
			}
		}
		return ret;
	}

	public Integer[] valuesAsInteger() {
		Integer[] ret = null;
		if (values != null) {
			ret = new Integer[values.length];
			for (int i = 0; i < values.length; i++) {
				ret[i] = Integer.valueOf(values[i]);
			}
		}
		return ret;
	}

	public Long valueAsLong() {
		final Long[] vals = valuesAsLong();
		if (vals != null && vals.length > 0) {
			return vals[0];
		} else {
			return null;
		}
	}

	public Integer valueAsInteger() {
		final Integer[] vals = valuesAsInteger();
		if (vals != null && vals.length > 0) {
			return vals[0];
		} else {
			return null;
		}
	}

	public int valueAsInteger(final Integer defValue) {
		final Integer[] vals = valuesAsInteger();
		if (vals != null && vals.length > 0) {
			return vals[0];
		} else {
			return defValue;
		}
	}

	public Date valueAsDate() {
		final Date[] vals = valuesAsDate();
		if (vals != null && vals.length > 0) {
			return vals[0];
		} else {
			return null;
		}
	}

	public String valueAsString() {
		if (values != null && values.length > 0) {
			return values[0];
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T valueAs(final Class<T> type) {
		if (type.equals(String.class)) {
			return (T) valueAsString();
		} else if (type.equals(Boolean.class)) {
			return (T) valueAsBoolean();
		} else if (type.equals(Long.class)) {
			return (T) valueAsLong();
		} else if (type.equals(Integer.class)) {
			return (T) valueAsInteger();
		} else if (type.equals(Date.class)) {
			return (T) valueAsDate();
		} else if (type.isEnum()) {
			return (T) valueAsEnum(type.asSubclass(Enum.class));
		} else {
			throw new IllegalArgumentException("type " + type + " is not supported for criterias");
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T[] valuesAs(final Class<T> type) {
		if (type.equals(String.class)) {
			return (T[]) getValues();
		} else if (type.equals(Long.class)) {
			return (T[]) valuesAsLong();
		} else if (type.equals(Integer.class)) {
			return (T[]) valuesAsInteger();
		} else if (type.equals(Date.class)) {
			return (T[]) valuesAsDate();
		} else if (type.isEnum()) {
			return (T[]) valuesAsEnum(type.asSubclass(Enum.class));
		} else {
			throw new IllegalArgumentException("type " + type + " is not supported for criterias");
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends Enum<T>> T[] valuesAsEnum(final Class<T> enumType) {
		final T[] ret = (T[]) Array.newInstance(enumType, values.length);
		for (int i = 0; i < values.length; i++) {
			ret[i] = Enum.valueOf(enumType, values[i]);
		}
		return ret;
	}

	public <T extends Enum<T>> T valueAsEnum(final Class<T> enumType) {
		final T[] vals = valuesAsEnum(enumType);
		if (vals != null && vals.length > 0) {
			return vals[0];
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "Criteria [property=" + property + ", values=" + Arrays.toString(values) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((property == null) ? 0 : property.hashCode());
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Criteria)) {
			return false;
		}
		final Criteria other = (Criteria) obj;
		if (property == null) {
			if (other.property != null) {
				return false;
			}
		} else if (!property.equals(other.property)) {
			return false;
		}
		if (!Arrays.equals(values, other.values)) {
			return false;
		}
		return true;
	}
}
