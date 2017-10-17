package com.oby.autumn.boutika.common.service;

import com.oby.autumn.boutika.common.dto.HelloDTO;

public interface HelloService extends BasicEntityService<HelloDTO>{
	
	public void createHello(HelloDTO helloDTO);
	
	public HelloDTO findHello(Long id);

}
