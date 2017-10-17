package com.oby.autumn.boutika.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oby.autumn.boutika.common.dto.HelloDTO;
import com.oby.autumn.boutika.common.service.HelloService;
import com.oby.autumn.boutika.configuration.logger.Autolog;

@RestController
@Autolog
public class HelloRC {

	@Autowired
	private HelloService helloService;

	@RequestMapping(value = "/hello/{id}/{message}")
	public HelloDTO hello(@PathVariable Long id, @PathVariable String message) {

		HelloDTO helloDTO = new HelloDTO();
		helloDTO.setId(id);
		helloDTO.setMessage(message);

		helloService.createHello(helloDTO);
		
		return helloService.findHello(id);
	}
}
