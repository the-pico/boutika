package com.oby.autumn.boutika.initdata.dataset;

import org.springframework.stereotype.Component;

import com.oby.autumn.boutika.initdata.builder.HelloBuilder;
import com.oby.autumn.boutika.initdata.builder.HelloHomeBuilder;
import com.oby.autumn.boutika.initdata.builder.HelloerBuilder;
import com.oby.autumn.boutika.model.entities.Hello;
import com.oby.autumn.boutika.model.entities.HelloHome;
import com.oby.autumn.boutika.model.entities.Helloer;

@Component
public class SimpleDataset extends AbstractDataSet {

	@Override
	public void defineDataset() {
		createHelloHome("myHome").address("this adress is my adress").build();

		createHelloer("helloerOmar").name("omar").email("azeze@email.com").addAttribute("koko").addAttribute("bobo")
				.addAttribute("soso").build();
		createHelloer("helloerZakia").name("zakia").email("azeze@email.com").build();
		createHelloer("helloerWadie").name("wadie").email("azeze@email.com").build();

		createHello("hello_1").message("omar").active(true).done(4).price(12315.55).quantity(4549L).helloHome("myHome")
				.helloer("helloerOmar").helloer("helloerZakia").helloer("helloerWadie").build();
		createHello("hello_2").message("zakia").active(false).done(5).price(10315.55).quantity(479L).build();
		createHello("hello_3").message("fatna").active(true).done(2).price(3315.55).quantity(221L).build();
		createHello("hello_4").message("wadie").active(false).done(1).price(1315.55).quantity(777L).build();
		createHello("hello_5").message("imane").active(true).done(8).price(9315.55).quantity(49L).build();
	}

	public HelloBuilder createHello(String instanceName) {
		return (HelloBuilder) create(instanceName, Hello.class);
	}

	public HelloHomeBuilder createHelloHome(String instanceName) {
		return (HelloHomeBuilder) create(instanceName, HelloHome.class);
	}

	public HelloerBuilder createHelloer(String instanceName) {
		return (HelloerBuilder) create(instanceName, Helloer.class);
	}

}
