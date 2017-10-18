package com.oby.autumn.boutika.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BasicDto {
	
	
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

}
