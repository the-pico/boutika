package com.oby.autumn.boutika.initdata.dataset;

import org.springframework.stereotype.Component;

import com.oby.autumn.boutika.initdata.builder.HelloBuilder;
import com.oby.autumn.boutika.model.entities.Hello;

@Component
public class SimpleDataset extends AbstractDataSet {

	@Override
	public void defineDataset() {
		createHello("hello_1").message("omar").build();
		createHello("hello_2").message("zakia").build();
		createHello("hello_3").message("fatna").build();
		createHello("hello_4").message("wadie").build();
		createHello("hello_5").message("imane").build();
	}

	public HelloBuilder createHello(String instanceName) {
		return (HelloBuilder) create(instanceName, Hello.class);
	}

}
