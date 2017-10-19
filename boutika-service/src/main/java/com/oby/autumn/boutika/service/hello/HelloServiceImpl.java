package com.oby.autumn.boutika.service.hello;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.oby.autumn.boutika.common.dto.HelloDTO;
import com.oby.autumn.boutika.common.service.HelloService;
import com.oby.autumn.boutika.configuration.logger.Autolog;
import com.oby.autumn.boutika.configuration.logger.IObyLogger;
import com.oby.autumn.boutika.configuration.pagedList.PageRequest;
import com.oby.autumn.boutika.configuration.pagedList.PagedList;
import com.oby.autumn.boutika.model.entities.Hello;
import com.oby.autumn.boutika.model.repositories.HelloRepository;
import com.oby.autumn.boutika.service.pagedList.HelloPagedListBuilder;

@Service
@Autolog
public class HelloServiceImpl extends BasicEntityServiceImpl<HelloDTO, Hello> implements HelloService {

	@Autowired
	private HelloRepository helloRepository;

	@Autowired
	private IObyLogger obyLog4j;

	public void createHello(HelloDTO helloDTO) {

		helloRepository.save(HelloMapper.DtoToEntity.apply(helloDTO));

	}

	public HelloDTO findHello(Long id) {

		return HelloMapper.EntityToDto.apply(helloRepository.findById(id).get());

	}

	@Override
	public void create(HelloDTO t) {
		ObyMapper obyMapper = new ObyMapper();
		obyMapper.dto2Entity(Hello.class, t);
		// HelloPagedListBuilder helloPagedListBuilder = new HelloPagedListBuilder();
		// helloPagedListBuilder.buildPagedList(new PageRequest(), Hello.class);
		helloRepository.save(HelloMapper.DtoToEntity.apply(t));
	}

	@Override
	public void update(HelloDTO t) {
		helloRepository.save(HelloMapper.DtoToEntity.apply(t));

	}

	@Override
	public void delete(HelloDTO t) {
		helloRepository.delete(HelloMapper.DtoToEntity.apply(t));
	}

	@Override
	public HelloDTO findOne(Long arg) {
		ObyMapper obyMapper = new ObyMapper();
		return obyMapper.entity2Dto(HelloDTO.class, helloRepository.findById(arg).get());
		// return HelloMapper.EntityToDto.apply(helloRepository.findById(arg).get());
	}

	@Override
	public Set<HelloDTO> findAll() {
		Set<Hello> list = Sets.newHashSet(helloRepository.findAll());
		return list.stream().map(HelloMapper.EntityToDto).collect(Collectors.toSet());
	}

	@Override
	public PagedList getPagedList(PageRequest pageRequest) {

		return null;
	}

}
