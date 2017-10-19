package com.oby.autumn.boutika.initdata.dataset;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.oby.autumn.boutika.initdata.builder.AbstractBuilder;
import com.oby.autumn.boutika.model.entities.BasicEntity;
import com.oby.autumn.boutika.model.repositories.BasicEntityRepository;

public abstract class AbstractDataSet implements Dataset {

	private BasicEntityRepository<?> basicEntityRepository;

	private final Map<Class<?>, Map<String, BasicEntity>> entities = new HashMap<Class<?>, Map<String, BasicEntity>>();

	@Override
	public <T extends BasicEntity> void setRepository(BasicEntityRepository<T> basicEntityRepository) {
		this.basicEntityRepository = basicEntityRepository;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T extends BasicEntity> void save(T entity, String instanceName) {
		entity.setCreatedBy("InitData");
		entity.setCreatedOn(new Date());
		((BasicEntityRepository<T>) this.basicEntityRepository).save(entity);
		if (instanceName != null) {
			final Map<String, BasicEntity> entityMap = getEntityMapByBuilderClass(
					(Class) getBuilderClass(entity.getClass()));
			entityMap.put(instanceName, entity);
		}
	}

	@Override
	public <T extends BasicEntity> AbstractBuilder<T> create(String instanceName, Class<T> type) {
		try {
			if (getEntity(type, instanceName) != null) {
				throw new RuntimeException(
						String.format("%s of type %s already register", instanceName, type.getSimpleName()));
			}

			final AbstractBuilder<T> builder = getBuilderClass(type).newInstance();
			builder.setType(type);
			builder.setInstanceName(instanceName);
			builder.setDataset(this);
			return builder;
		} catch (final InstantiationException e) {
			throw new RuntimeException(e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T extends BasicEntity> T getEntity(final Class<T> type, final String instanceName) {
		return getEntityMapByEntityClass(type).get(instanceName);
	}

	private <T extends BasicEntity> Map<String, T> getEntityMapByEntityClass(final Class<T> type) {
		return getEntityMapByBuilderClass(getBuilderClass(type));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasicEntity> Class<AbstractBuilder<T>> getBuilderClass(Class<T> type) {
		final String className = String.format("com.oby.autumn.boutika.initdata.builder.%sBuilder",
				type.getSimpleName());
		try {
			return (Class<AbstractBuilder<T>>) Class.forName(className);
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasicEntity> Map<String, T> getEntityMapByBuilderClass(Class<AbstractBuilder<T>> builderClass) {
		Map<String, BasicEntity> builders;
		if (entities.containsKey(builderClass)) {
			builders = entities.get(builderClass);
		} else {
			builders = new HashMap<String, BasicEntity>();
			entities.put(builderClass, builders);
		}
		return (Map<String, T>) builders;
	}

}
