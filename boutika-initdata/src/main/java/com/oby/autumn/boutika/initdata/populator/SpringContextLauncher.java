package com.oby.autumn.boutika.initdata.populator;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringContextLauncher {

	@Autowired
	private DatabasePopulator<?> dp;

	@PostConstruct
	public void bootStrap() {
		dp.populate();
	}

}
