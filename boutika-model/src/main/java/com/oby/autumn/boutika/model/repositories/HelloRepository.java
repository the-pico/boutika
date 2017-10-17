package com.oby.autumn.boutika.model.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oby.autumn.boutika.model.entities.Hello;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public interface HelloRepository extends BasicEntityRepository<Hello> {

}
