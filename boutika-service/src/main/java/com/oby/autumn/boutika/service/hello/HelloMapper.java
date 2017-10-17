package com.oby.autumn.boutika.service.hello;

import java.util.function.Function;

import com.oby.autumn.boutika.common.dto.HelloDTO;
import com.oby.autumn.boutika.model.entities.Hello;

public class HelloMapper {

	public static Function<HelloDTO, Hello> DtoToEntity = new Function<HelloDTO, Hello>() {
		public Hello apply(HelloDTO helloDTO) {

			if (helloDTO == null)
				return null;

			Hello hello = new Hello();
			hello.setId(helloDTO.getId());
			hello.setMessage(helloDTO.getMessage());

			return hello;
		}
	};

	public static Function<Hello, HelloDTO> EntityToDto = new Function<Hello, HelloDTO>() {
		public HelloDTO apply(Hello hello) {

			if (hello == null)
				return null;

			HelloDTO helloDTO = new HelloDTO();
			helloDTO.setId(hello.getId());
			helloDTO.setMessage(hello.getMessage());

			return helloDTO;
		}
	};

}
