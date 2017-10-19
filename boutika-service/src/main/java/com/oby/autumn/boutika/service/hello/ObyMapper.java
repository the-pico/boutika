package com.oby.autumn.boutika.service.hello;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.oby.autumn.boutika.common.dto.BasicDto;
import com.oby.autumn.boutika.model.entities.BasicEntity;

public class ObyMapper<T extends BasicEntity, K extends BasicDto> {

	protected T entity;

	protected K dto;

	public ObyMapper(Class<T> entityClass, Class<K> dtoClass) {
		try {
			entity = entityClass.newInstance();
			dto = dtoClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public T DtoToEntity() {

			Map<String, Field> dtoFields = Lists.newArrayList(dto.getClass().getDeclaredFields()).stream()
					.filter(field -> field.getAnnotation(IgnoreMapping.class) == null)
					.collect(Collectors.toMap(field -> field.getName(), field -> field));
			
			Map<String, Field> entityFields = Lists.newArrayList(entity.getClass().getDeclaredFields()).stream()
					.filter(field -> field.getAnnotation(IgnoreMapping.class) == null)
					.collect(Collectors.toMap(field -> field.getName(), field -> field));
			
			Set<String> intersect = dtoFields.keySet().stream()
                    .filter(entityFields.keySet()::contains)
                    .collect(Collectors.toSet());

			dtoFields.forEach((s, field) -> field.setAccessible(true));
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
			return entity;
		}
};

}
