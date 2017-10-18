package com.oby.autumn.boutika.service.hello;

import com.oby.autumn.boutika.common.dto.BasicDto;
import com.oby.autumn.boutika.common.service.BasicEntityService;
import com.oby.autumn.boutika.model.entities.BasicEntity;

public abstract class BasicEntityServiceImpl<T extends BasicDto, K extends BasicEntity>
		implements BasicEntityService<T> {
	
}
