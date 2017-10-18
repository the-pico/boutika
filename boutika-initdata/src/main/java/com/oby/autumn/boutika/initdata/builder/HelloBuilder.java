package com.oby.autumn.boutika.initdata.builder;

import com.oby.autumn.boutika.model.entities.Hello;


public class HelloBuilder extends AbstractBuilder<Hello>{
	
	private String message;

	@Override
	protected void builder2Entity() {
		entity.setMessage(message);
		
	}
	
	public HelloBuilder message(String message) {
		this.message = message;
		return this;
	}

}
