package com.seda.book_author_crud_api.mapper;

import org.mapstruct.Mapper;

import com.seda.book_author_crud_api.dto.AuthorDto;
import com.seda.book_author_crud_api.entities.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

	AuthorDto toDto(Author author);

	Author toEntity(AuthorDto authorDto);

}
