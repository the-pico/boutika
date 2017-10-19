package com.oby.autumn.boutika.initdata.builder;

import com.oby.autumn.boutika.model.entities.HelloHome;

@SuppressWarnings("unused")
public class HelloHomeBuilder extends AbstractBuilder<HelloHome> {

	private String address;

	public HelloHomeBuilder address(String address) {
		this.address = address;
		return this;
	}

}
