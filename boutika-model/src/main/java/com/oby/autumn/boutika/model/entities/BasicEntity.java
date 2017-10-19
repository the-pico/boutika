package com.oby.autumn.boutika.model.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;

@MappedSuperclass
public abstract class BasicEntity {
	protected String createdBy;
	protected Date updatedOn;
	protected String updatedBy;
	protected Date createdOn;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@GenericGenerator(name = "SEQ_ID", strategy = "com.oby.autumn.boutika.configuration.ConfigurableSequenceGenerator")
	protected Long id;

	public BasicEntity() {
		super();
	}

	public BasicEntity(final Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(final Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@JsonIgnore
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(final String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@JsonIgnore
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(final Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@JsonIgnore
	public boolean isNew() {
		return getId() == null;
	}

	@Override
	public String toString() {
		return String.format("%s [%s]", this.getClass().getSimpleName(), getId());
	}

	public void setCreatedBy(final String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object param);

	public static boolean contains(final Collection<? extends BasicEntity> all, final Long id) {
		if (all == null || id == null)
			return false;
		for (final BasicEntity e : all) {
			if (!e.isNew() && e.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static final Collection<Long> ids(final Collection<? extends BasicEntity> in) {
		return Collections2.transform(in, new Function<BasicEntity, Long>() {
			@Override
			public Long apply(final BasicEntity input) {
				return input.getId();
			}
		});
	}

//	public static final <T extends BasicEntity> T withId(final Collection<T> in, final Long id) {
//		try {
//			return Iterables.find(in, new Predicate<T>() {
//				@Override
//				public boolean apply(final T input) {
//					return input.getId().equals(id);
//				}
//			});
//		} catch (final NoSuchElementException e) {
//			return null;
//		}
//	}

}