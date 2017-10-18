package com.oby.autumn.boutika.initdata.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oby.autumn.boutika.initdata.dataset.SimpleDataset;
import com.oby.autumn.boutika.model.entities.BasicEntity;
import com.oby.autumn.boutika.model.repositories.BasicEntityRepository;

@Component
public class DatabasePopulator<T extends BasicEntity> {

	@Autowired
	private BasicEntityRepository<T> basicEntityRepository;

	@Autowired
	private SimpleDataset simpleDataset;

	public void populate() {

		simpleDataset.setRepository(basicEntityRepository);
		simpleDataset.defineDataset();

	}

}
