package com.oby.autumn.boutika.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oby.autumn.boutika.entities.Hello;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public interface HelloRepository extends CrudRepository<Hello, Long> {

}
