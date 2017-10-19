package com.oby.autumn.boutika.service.pagedList;

import java.util.Collection;
import java.util.StringJoiner;

import com.google.common.collect.Lists;
import com.oby.autumn.boutika.configuration.pagedList.PageRequest;
import com.oby.autumn.boutika.configuration.pagedList.PagedList;
import com.oby.autumn.boutika.model.entities.BasicEntity;

public abstract class PagedListBuilder<T extends BasicEntity> {

	protected T entity;

	public PagedList buildPagedList(PageRequest pageRequest, Class<T> type) {
		try {

			entity = type.newInstance();
			String COUNT = "SELECT count(t.id) FROM %s t %s";
			String FIND = "SELECT DISTINCT %s FROM %s t %s %s";

			StringJoiner stringJoiner = new StringJoiner(",");

			Lists.newArrayList(entity.getClass().getDeclaredFields()).forEach((field) -> {
				if (!Collection.class.isAssignableFrom(field.getClass())) {
					stringJoiner.add(String.format("%s%s", "t.", field.getName().toString()));
					System.out.println(field.getType().getName());
					switch (field.getType().getName()) {
					case "Long":
					case "Double":
						break;
					case "java.lang.String":
						break;
					case "Date":
						break;
					default:
						break;
					}
				}

			});
			COUNT = String.format(COUNT, entity.getClass().getSimpleName(), "%s");
			FIND = String.format(FIND, stringJoiner.toString(), entity.getClass().getSimpleName(), "%s", "%s");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
