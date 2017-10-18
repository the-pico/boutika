package com.oby.autumn.boutika.configuration.pagedList;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * Holder for a list of {@link Criteria}s, with utility functions.
 */
public class Criterias {

	private Map<String,Criteria> criterias;

	public static Criterias of(final String property, final String value) {
		return new Criterias(new Criteria(property, value));
	}

	public static Criterias of(final Enum<?> property, final String value) {
		return of(property.name(), value);
	}

	public static Criterias of(final String property, final Long value) {
		return new Criterias(new Criteria(property, value));
	}

	public static Criterias of(final Enum<?> property, final Long value) {
		return of(property.name(), value);
	}


	public static Criterias of(final List<Criteria> critz) {
		return new Criterias(critz != null ? critz.toArray(new Criteria[critz.size()]) : null);
	}

	public static Criterias of(final Criteria... critz) {
		return new Criterias(critz);
	}

	public Criterias() {
		criterias = new HashMap<String,Criteria>();
	}

	public Criterias(final Criteria...initial) {
		criterias = new HashMap<String,Criteria>();
		if ( initial != null ) {
			for ( final Criteria c : initial ) this.criterias.put(c.getProperty(), c);
		}
	}

	public Map<String, Criteria> getCriterias() {
		return criterias;
	}

	public void setCriterias(final Map<String, Criteria> criterias) {
		this.criterias = criterias;
	}

	public String[] valuesAsString(final Enum<?> property) {
		return this.valuesAsString(property.name());
	}

	public String[] valuesAsString(final String property) {
		if (criterias.containsKey(property)) return criterias.get(property).getValues();
		else return null;
	}

	public Long[] valuesAsLong(final Enum<?> property) {
		return this.valuesAsLong(property.name());
	}

	public Long[] valuesAsLong(final String property) {
		if (criterias.containsKey(property)) return criterias.get(property).valuesAsLong();
		else return null;
	}

	public Boolean valueAsBoolean(final Enum<?> property) {
		return this.valueAsBoolean(property.name());
	}

	public Boolean valueAsBoolean(final String property) {
		if (criterias.containsKey(property)) return criterias.get(property).valueAsBoolean();
		else return null;
	}

	public Date[] valuesAsDate(final Enum<?> property) {
		return this.valuesAsDate(property.name());
	}

	public Date[] valuesAsDate(final String property) {
		if (criterias.containsKey(property)) return criterias.get(property).valuesAsDate();
		else return null;
	}

	public Integer[] valuesAsInteger(final Enum<?> property) {
		return this.valuesAsInteger(property.name());
	}

	public Integer[] valuesAsInteger(final String property) {
		if (criterias.containsKey(property)) return criterias.get(property).valuesAsInteger();
		else return null;
	}

	public Long valueAsLong(final Enum<?> property) {
		return this.valueAsLong(property.name());
	}

	public Long valueAsLong(final String property) {
		final Long[] vals = valuesAsLong(property);
		if (vals != null && vals.length > 0) return vals[0];
		else return null;
	}

	public Integer valueAsInteger(final Enum<?> property) {
		return this.valueAsInteger(property.name());
	}

	public Integer valueAsInteger(final String property) {
		final Integer[] vals = valuesAsInteger(property);
		if (vals != null && vals.length > 0) return vals[0];
		else return null;
	}

	public int valueAsInteger(final String property, final Integer defValue) {
		final Integer[] vals = valuesAsInteger(property);
		if (vals != null && vals.length > 0) return vals[0];
		else return defValue;
	}

	public Date valueAsDate(final Enum<?> property) {
		return this.valueAsDate(property.name());
	}

	public Date valueAsDate(final String property) {
		final Date[] vals = valuesAsDate(property);
		if (vals != null && vals.length > 0) return vals[0];
		else return null;
	}

	public String valueAsString(final Enum<?> property) {
		return this.valueAsString(property.name());
	}

	public String valueAsString(final String property) {
		final String[] vals = valuesAsString(property);
		if (vals != null && vals.length > 0) return vals[0];
		else return null;
	}

	public <T extends Enum<T>> T[] valuesAsEnum(final Enum<?> property, final Class<T> enumType) {
		return this.valuesAsEnum(property.name(), enumType);
	}

	public <T extends Enum<T>> T[] valuesAsEnum(final String property, final Class<T> enumType) {
		if (criterias.containsKey(property)) return criterias.get(property).valuesAsEnum(enumType);
		else return null;
	}

	public <T extends Enum<T>> T valueAsEnum(final Enum<?> property, final Class<T> enumType) {
		return this.valueAsEnum(property.name(), enumType);
	}

	public <T extends Enum<T>> T valueAsEnum(final String property, final Class<T> enumType) {
		final T[] vals = valuesAsEnum(property, enumType);
		if (vals != null && vals.length > 0) return vals[0];
		else return null;
	}

	public boolean hasCriteria(final String property) {
		return criterias.containsKey(property) && criterias.get(property).hasValues();
	}

	@JsonIgnore
	public void put(final Criteria c) {
		this.criterias.put(c.getProperty(),c);
	}

	public void remove(final String pname) {
		this.criterias.remove(pname);
	}

	public Criteria get(final String pname) {
		return this.criterias.get(pname);
	}

	public List<Criteria> values() {
		final List<Criteria> dest = new LinkedList<Criteria>();
		final Collection<Criteria> values = criterias.values();
		for (final Criteria value : values) {
			dest.add(value);
		}
		return dest;
	}

	public void addAll(final List<Criteria> criterias) {
		this.criterias.clear();
		for (final Criteria criteria : criterias) {
			if ( criteria.hasValues() ) this.criterias.put(criteria.getProperty(),criteria);
		}
	}

	public int size() {
		return criterias.values().size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((criterias == null) ? 0 : criterias.hashCode());
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
		final Criterias other = (Criterias) obj;
		if (criterias == null) {
			if (other.criterias != null)
				return false;
		} else if (!criterias.equals(other.criterias))
			return false;
		return true;
	}

}
