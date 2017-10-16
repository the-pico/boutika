package com.oby.autumn.boutika.dto;

import java.io.Serializable;

import com.oby.autumn.boutika.logger.Autolog;

@Autolog
public class HelloDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1624511343577738711L;

	private Long id;

	private String message;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	
	
	

}