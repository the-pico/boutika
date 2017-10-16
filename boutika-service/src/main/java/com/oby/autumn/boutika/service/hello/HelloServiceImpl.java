package com.oby.autumn.boutika.service.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oby.autumn.boutika.dto.HelloDTO;
import com.oby.autumn.boutika.logger.Autolog;
import com.oby.autumn.boutika.logger.IObyLogger;
import com.oby.autumn.boutika.repositories.HelloRepository;
import com.oby.autumn.boutika.service.HelloService;

@Service
@Autolog
public class HelloServiceImpl implements HelloService {

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
