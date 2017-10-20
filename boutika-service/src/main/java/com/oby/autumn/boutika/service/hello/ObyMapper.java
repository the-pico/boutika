package com.oby.autumn.boutika.service.hello;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.collection.internal.PersistentSet;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.oby.autumn.boutika.common.dto.BasicDto;
import com.oby.autumn.boutika.configuration.IgnoreMapping;
import com.oby.autumn.boutika.model.entities.BasicEntity;

public class ObyMapper {

	private final static int DTO2ENTITY = 1;

	private final static int ENTITY2DTO = 2;

	public Boolean fieldIsAnnotatedIgnoring(Field field) {
		return field.getAnnotation(IgnoreMapping.class) == null;
	}

	public <T extends BasicEntity> Map<String, Field> getEntityMappedFields(T entity) {
		return getAllObjectDeclaredFields(entity.getClass()).stream().filter(field -> {
			field.setAccessible(true);
			return fieldIsAnnotatedIgnoring(field);
		}).collect(Collectors.toMap(field -> field.getName(), field -> field));
	}

	public <K extends BasicDto> Map<String, Field> getDtoMappedFields(K dto) {
		return getAllObjectDeclaredFields(dto.getClass()).stream().filter(field -> {
			field.setAccessible(true);
			return fieldIsAnnotatedIgnoring(field);
		}).collect(Collectors.toMap(field -> field.getName(), field -> field));
	}

	public Set<String> mapsInterComplement(Map<String, Field> map1, Map<String, Field> map2) {

		return map1.keySet().stream().filter(field -> !map2.keySet().contains(field)).collect(Collectors.toSet());
	}

	public <T extends BasicEntity, K extends BasicDto> T dto2Entity(Class<T> entityClass, K dto) {
		try {

			final T entity = entityClass.newInstance();

			Map<String, Field> dtoFields = getDtoMappedFields(dto);

			Map<String, Field> entityFields = getEntityMappedFields(entity);

			checkForCompatibility(entity, dto, DTO2ENTITY);

			dtoFields.values().forEach((field) -> {
				processSimpleFieldsOf(entityFields, dto, entity, field);
			});

			return entity;

		} catch (IllegalArgumentException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void processSimpleFieldsOf(Map<String, Field> map, Object from, Object to, Field field) {
		try {
			map.get(field.getName()).set(to, field.get(from));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BasicEntity, K extends BasicDto> K entity2Dto(Class<K> dtoClass, T entity) {

		try {

			K dto = dtoClass.newInstance();

			Map<String, Field> entityFields = getEntityMappedFields(entity);

			Map<String, Field> dtoFields = getDtoMappedFields(dto);

			checkForCompatibility(entity, dto, ENTITY2DTO);

			entityFields.values().forEach((field) -> {
				try {
					if (BasicEntity.class.isAssignableFrom(field.getType())) {

						String dtoClassName = String.format("%s%s%s", "com.oby.autumn.boutika.common.dto.",
								field.getType().getSimpleName(), "DTO");

						dtoFields.get(field.getName()).set(dto, entity2Dto(
								(Class<BasicDto>) Class.forName(dtoClassName), (BasicEntity) field.get(entity)));
					} else if (Collection.class.isAssignableFrom(field.getType())) {

						ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
						Class<?> objectListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
						if (BasicEntity.class.isAssignableFrom(objectListClass)) {
							Collection<K> ts = Sets.newHashSet();
							PersistentSet p = (PersistentSet) field.get(entity);
							p.forEach(t -> {
								String dtoClassName = String.format("%s%s%s", "com.oby.autumn.boutika.common.dto.",
										objectListClass.getSimpleName(), "DTO");
								try {
									ts.add((K) entity2Dto((Class<BasicDto>) Class.forName(dtoClassName),
											(BasicEntity) t));
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							});
							dtoFields.get(field.getName()).set(dto, ts);
						} else {
							processSimpleFieldsOf(dtoFields, entity, dto, field);
						}
					} else {
						processSimpleFieldsOf(dtoFields, entity, dto, field);
					}

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
			return dto;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private <K extends BasicDto, T extends BasicEntity> void checkForCompatibility(T entity, K dto, int type) {

		Map<String, Field> entityFields = getEntityMappedFields(entity);

		Map<String, Field> dtoFields = getDtoMappedFields(dto);

		Set<String> xintersect = null;
		switch (type) {
		case DTO2ENTITY:
			xintersect = mapsInterComplement(dtoFields, entityFields);
			break;
		case ENTITY2DTO:
			xintersect = mapsInterComplement(entityFields, dtoFields);
			break;
		}

		if (xintersect.size() > 0) {
			throw new IllegalArgumentException(String.format(
					"these fields : %s are not declared in both :  %s and %s; you can use @IgnoreMapping to ignore it.",
					ToStringBuilder.reflectionToString(xintersect), dto.getClass().getName(),
					entity.getClass().getName()));

		}
	}

	private List<Field> getAllObjectDeclaredFields(Class<?> clazz) {
		List<Field> result = Lists.newArrayList();
		Class<?> i = clazz;
		while (i != null && i != Object.class) {
			result.addAll(Lists.newArrayList(i.getDeclaredFields()));
			i = i.getSuperclass();
		}
		return result;
	}

}
