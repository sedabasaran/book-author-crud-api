package com.seda.book_author_crud_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.seda.book_author_crud_api.dto.BookDto;
import com.seda.book_author_crud_api.entities.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

	@Mapping(source = "author.id", target = "authorId")
	BookDto toBookDto(Book book);

	@Mapping(source = "authorId", target = "author.id")
	Book toBook(BookDto bookDto);
}
