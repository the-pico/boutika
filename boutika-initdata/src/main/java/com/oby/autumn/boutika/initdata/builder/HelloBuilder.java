package com.oby.autumn.boutika.initdata.builder;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.mapping.Collection;

import com.google.common.collect.Sets;
import com.oby.autumn.boutika.model.entities.Hello;
import com.oby.autumn.boutika.model.entities.HelloHome;
import com.oby.autumn.boutika.model.entities.Helloer;

@SuppressWarnings("unused")
public class HelloBuilder extends AbstractBuilder<Hello> {

	private String message;

	private Double price;

	private Long quantity;

	private int done;

	private Boolean active;

	private HelloHome helloHome;

	private Set<Helloer> helloers;

	public HelloBuilder helloer(String instanceName) {
		if (CollectionUtils.isEmpty(this.helloers))
			this.helloers = Sets.newHashSet();
		this.helloers.add(this.dataset.getEntity(Helloer.class, instanceName));
		return this;
	}

	public HelloBuilder helloHome(String instanceName) {
		this.helloHome = this.dataset.getEntity(HelloHome.class, instanceName);
		return this;
	}

	public HelloBuilder message(String message) {
		this.message = message;
		return this;
	}

	public HelloBuilder quantity(Long quantity) {
		this.quantity = quantity;
		return this;
	}

	public HelloBuilder price(Double price) {
		this.price = price;
		return this;
	}

	public HelloBuilder done(int done) {
		this.done = done;
		return this;
	}

	public HelloBuilder active(Boolean active) {
		this.active = active;
		return this;
	}

}
