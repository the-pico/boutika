package com.oby.autumn.boutika.common.service;

import java.util.Set;

import com.oby.autumn.boutika.common.dto.BasicDto;
import com.oby.autumn.boutika.configuration.pagedList.PageRequest;
import com.oby.autumn.boutika.configuration.pagedList.PagedList;

public abstract interface BasicEntityService<T extends BasicDto> {

	public void create(T t);

	public void update(T t);
	
	public void delete(T t);

	public T findOne(Long arg);

	public Set<T> findAll();

	public PagedList getPagedList(PageRequest pageRequest);

}
