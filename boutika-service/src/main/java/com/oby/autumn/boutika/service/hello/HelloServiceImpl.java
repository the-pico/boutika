package com.oby.autumn.boutika.service.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oby.autumn.boutika.common.dto.HelloDTO;
import com.oby.autumn.boutika.common.service.HelloService;
import com.oby.autumn.boutika.configuration.logger.Autolog;
import com.oby.autumn.boutika.configuration.logger.IObyLogger;
import com.oby.autumn.boutika.model.entities.Hello;
import com.oby.autumn.boutika.model.repositories.HelloRepository;

@Service
@Autolog
public class HelloServiceImpl extends BasicEntityServiceImpl<HelloDTO, Hello> implements HelloService {

	@Autowired
	private HelloRepository helloRepository;
	
	@Autowired
	private IObyLogger obyLog4j;

	public void createHello(HelloDTO helloDTO) {
		
		obyLog4j.info("new hello %s ",helloDTO);
		helloRepository.save(HelloMapper.DtoToEntity.apply(helloDTO));

	}

	public HelloDTO findHello(Long id) {

		return HelloMapper.EntityToDto.apply(helloRepository.findById(id).get());
		
	}



}
