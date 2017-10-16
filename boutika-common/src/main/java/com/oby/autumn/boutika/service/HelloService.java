package com.oby.autumn.boutika.service;

import com.oby.autumn.boutika.dto.HelloDTO;

public interface HelloService {
	
	public void createHello(HelloDTO helloDTO);
	
	public HelloDTO findHello(Long id);

}
