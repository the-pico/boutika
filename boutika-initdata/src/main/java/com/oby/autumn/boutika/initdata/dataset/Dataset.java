package com.oby.autumn.boutika.initdata.dataset;

import java.util.Map;

import com.oby.autumn.boutika.initdata.builder.AbstractBuilder;
import com.oby.autumn.boutika.model.entities.BasicEntity;
import com.oby.autumn.boutika.model.repositories.BasicEntityRepository;

public interface Dataset {

	<T extends BasicEntity> void setRepository(BasicEntityRepository<T> basicEntityRepository);

	void defineDataset();

	<T extends BasicEntity> void save(T bean, String instanceName);

	<T extends BasicEntity> AbstractBuilder<T> create(String instanceName, Class<T> type);

	<T extends BasicEntity> T getEntity(Class<T> type, String instanceName);

	<T extends BasicEntity> Class<AbstractBuilder<T>> getBuilderClass(Class<T> type);

	<T extends BasicEntity> Map<String, T> getEntityMapByBuilderClass(Class<AbstractBuilder<T>> builderClass);

	// <H extends BasicEntity> AbstractBuilder<H> update(Class<H> type, String
	// instanceName);
	//
	// <H extends BasicEntity> H get(Class<H> type, String instanceName);
	//
	// <H extends BasicEntity> H getEntity(Class<H> type, String instanceName);
	//
	// <H extends BasicEntity> Map<String, H>
	// getEntityMapByBuilderClass(Class<AbstractBuilder<H>> builderClass);
	//
	// <H extends BasicEntity> AbstractBuilder<H> create(String instanceName,
	// Class<H> type);
	//
	// <H extends BasicEntity> Class<AbstractBuilder<H>> getBuilderClass(Class<H>
	// type);
	//
	// void putList(String listNameKey, String instanceName);
	//
	// List<String> getList(String listNameKey);
}
