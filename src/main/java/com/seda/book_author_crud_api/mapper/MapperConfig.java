package com.seda.book_author_crud_api.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

	@Bean
	public AuthorMapper authorMapper() {
		return Mappers.getMapper(AuthorMapper.class);
	}

}
