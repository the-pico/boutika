package com.oby.autumn.boutika.initdata.builder;

import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.oby.autumn.boutika.model.entities.Hello;
import com.oby.autumn.boutika.model.entities.Helloer;

@SuppressWarnings("unused")
public class HelloerBuilder extends AbstractBuilder<Helloer> {

	private String name;

	private String email;

	private List<String> attributes;

	public HelloerBuilder name(String name) {
		this.name = name;
		return this;
	}

	public HelloerBuilder email(String email) {
		this.email = email;
		return this;
	}

	public HelloerBuilder addAttribute(String attribute) {
		if (CollectionUtils.isEmpty(this.attributes))
			this.attributes = Lists.newArrayList();
		this.attributes.add(attribute);
		return this;
	}

}
