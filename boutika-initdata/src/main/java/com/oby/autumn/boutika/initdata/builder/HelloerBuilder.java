package com.oby.autumn.boutika.initdata.builder;

import java.util.Set;

import com.oby.autumn.boutika.model.entities.Hello;
import com.oby.autumn.boutika.model.entities.Helloer;

@SuppressWarnings("unused")
public class HelloerBuilder extends AbstractBuilder<Helloer> {

	private String name;

	private String email;

	public HelloerBuilder name(String name) {
		this.name = name;
		return this;
	}

	public HelloerBuilder email(String email) {
		this.email = email;
		return this;
	}

}
