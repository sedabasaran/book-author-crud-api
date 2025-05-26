package com.seda.book_author_crud_api.services;

import java.util.List;

import com.seda.book_author_crud_api.dto.AuthorDto;

public interface AuthorService {

	AuthorDto createAuthor(AuthorDto authorDto);

	List<AuthorDto> getAllAuthors();

	AuthorDto getAuthorById(Long id);

	AuthorDto updateAuthor(Long id, AuthorDto authorDto);

	void deleteAuthor(Long id);

}
