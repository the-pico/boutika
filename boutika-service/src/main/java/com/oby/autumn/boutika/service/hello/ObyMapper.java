package com.oby.autumn.boutika.service.hello;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;
import com.google.common.collect.Lists;
import com.oby.autumn.boutika.common.dto.BasicDto;
import com.oby.autumn.boutika.configuration.IgnoreMapping;
import com.oby.autumn.boutika.model.entities.BasicEntity;

public class ObyMapper {

	// protected T entity;
	//
	// protected K dto;

	// public ObyMapper(, Class<K> dtoClass) {
	// try {
	//
	// dto = dtoClass.newInstance();
	// } catch (InstantiationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	public <T extends BasicEntity, K extends BasicDto> T dto2Entity(Class<T> entityClass, K dto) {
		try {
			final T entity = entityClass.newInstance();
			Map<String, Field> dtoFields = getAllObjectDeclaredFields(dto.getClass()).stream()
					.filter(field -> field.getAnnotation(IgnoreMapping.class) == null)
					.collect(Collectors.toMap(field -> field.getName(), field -> field));

			Map<String, Field> entityFields = getAllObjectDeclaredFields(entity.getClass()).stream()
					.filter(field -> field.getAnnotation(IgnoreMapping.class) == null)
					.collect(Collectors.toMap(field -> field.getName(), field -> field));

			Set<String> xintersect = dtoFields.keySet().stream().filter(field -> !entityFields.keySet().contains(field))
					.collect(Collectors.toSet());

			if (xintersect.size() > 0) {
				throw new Exception(
						"Undeclared Fields in entity which are declared in dto please annotate it with @IgnoreMapping if it "
								+ "will not be used in the mapping otherwise remove those objects from dto"
								+ ToStringBuilder.reflectionToString(xintersect));

			}

			entityFields.forEach((s, field) -> field.setAccessible(true));

			dtoFields.values().forEach((field) -> {
				field.setAccessible(true);
				try {
					// if(entity.getClass().isAssignableFrom(BasicEntity.class)) {
					//
					// }else if()
					entityFields.get(field.getName()).set(entity, field.get(dto));
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			});
			return entity;
		} catch (Exception e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends BasicEntity, K extends BasicDto> K entity2Dto(Class<K> dtoClass, T entity) {

		try {

			K dto = dtoClass.newInstance();
			Map<String, Field> entityFields = getAllObjectDeclaredFields(entity.getClass()).stream()
					.filter(field -> field.getAnnotation(IgnoreMapping.class) == null)
					.collect(Collectors.toMap(field -> field.getName(), field -> field));

			Map<String, Field> dtoFields = getAllObjectDeclaredFields(dto.getClass()).stream()
					.filter(field -> field.getAnnotation(IgnoreMapping.class) == null)
					.collect(Collectors.toMap(field -> field.getName(), field -> field));

			Set<String> xintersect = entityFields.keySet().stream().filter(field -> !dtoFields.keySet().contains(field))
					.collect(Collectors.toSet());

			if (xintersect.size() > 0) {
				throw new Exception(
						"Undeclared Fields in entity which are declared in dto please annotate it with @IgnoreMapping if it "
								+ "will not be used in the mapping otherwise remove those objects from dto"
								+ ToStringBuilder.reflectionToString(xintersect));

			}

			dtoFields.forEach((s, field) -> field.setAccessible(true));

			entityFields.values().forEach((field) -> {
				field.setAccessible(true);
				try {
					if (BasicEntity.class.isAssignableFrom(field.getType())) {
						String dtoClassName = String.format("%s%s%s", "com.oby.autumn.boutika.common.dto.",
								field.getType().getSimpleName(), "DTO");
						dtoFields.get(field.getName()).set(dto, this.entity2Dto(
								(Class<BasicDto>) Class.forName(dtoClassName), (BasicEntity) field.get(entity)));
					} else if (Collection.class.isAssignableFrom(field.getType())) {

					} else {
						dtoFields.get(field.getName()).set(dto, field.get(entity));
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
