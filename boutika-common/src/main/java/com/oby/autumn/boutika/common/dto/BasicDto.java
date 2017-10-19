package com.oby.autumn.boutika.common.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BasicDto {
	
	private String createdBy;
	private Date updatedOn;
	private String updatedBy;
	private Date createdOn;

	private Long id;
	
	@Override
	public String toString() {
		
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
