package com.oby.autumn.boutika.initdata.builder;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.oby.autumn.boutika.initdata.dataset.Dataset;
import com.oby.autumn.boutika.model.entities.BasicEntity;

public abstract class AbstractBuilder<T extends BasicEntity> {

	protected Dataset dataset;

	protected T entity;

	private String instanceName;
	
	protected Class<T> type;

	protected void builder2Entity() {

		Map<String, Field> entityFields = Lists.newArrayList(entity.getClass().getDeclaredFields()).stream()
				.collect(Collectors.toMap(field -> field.getName(), field -> field));
		
		entityFields.forEach((s, field) -> field.setAccessible(true));

		Lists.newArrayList(this.getClass().getDeclaredFields()).forEach((field) -> {
			field.setAccessible(true);
			try {
				entityFields.get(field.getName()).set(entity, field.get(this));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		});

	}

	protected AbstractBuilder() {
	}

	public void build() {
		
		try {
			if (entity == null || instanceName == null) {
				newInstance();
			}
			builder2Entity();
			dataset.save(entity, instanceName);
		} catch (InstantiationException e) {
			new RuntimeException(e);
		} catch (IllegalAccessException e) {
			new RuntimeException(e);
		}

	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
	protected void newInstance() throws InstantiationException, IllegalAccessException {
		entity = type.newInstance();
	}

	public Dataset getDataset() {
		return dataset;
	}

	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

}
