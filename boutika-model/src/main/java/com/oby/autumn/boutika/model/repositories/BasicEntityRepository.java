package com.oby.autumn.boutika.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.oby.autumn.boutika.model.entities.BasicEntity;

@NoRepositoryBean
public interface BasicEntityRepository<T extends BasicEntity> extends CrudRepository<T, Long>  {
	
}
