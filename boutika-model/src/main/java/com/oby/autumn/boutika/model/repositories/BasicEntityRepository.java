package com.oby.autumn.boutika.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oby.autumn.boutika.model.entities.BasicEntity;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public interface BasicEntityRepository<T extends BasicEntity> extends CrudRepository<T, Long> {

}
