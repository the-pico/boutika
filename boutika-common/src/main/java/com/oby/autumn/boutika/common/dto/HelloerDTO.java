package com.oby.autumn.boutika.common.dto;

import java.util.List;

public class HelloerDTO extends BasicDto{
	
	private String name;

	private String email;
	
	private List<String> attributes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	
	
	
	

}
