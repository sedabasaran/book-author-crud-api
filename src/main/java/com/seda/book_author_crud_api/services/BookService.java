package com.seda.book_author_crud_api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.seda.book_author_crud_api.dto.BookDto;

public interface BookService {

	BookDto createBook(BookDto bookDto);

	Page<BookDto> getAllBooks(Pageable pageable);

	List<BookDto> getAllBooks();

	BookDto getBookById(Long id);

	BookDto updateBook(Long id, BookDto bookDto);

	void deleteBook(Long id);

}
