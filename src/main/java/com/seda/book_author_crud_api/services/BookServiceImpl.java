package com.seda.book_author_crud_api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seda.book_author_crud_api.dto.BookDto;
import com.seda.book_author_crud_api.entities.Author;
import com.seda.book_author_crud_api.entities.Book;
import com.seda.book_author_crud_api.mapper.BookMapper;
import com.seda.book_author_crud_api.repository.AuthorRepository;
import com.seda.book_author_crud_api.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final BookMapper bookMapper;

	@Override
	public BookDto createBook(BookDto bookDto) {
		Author author = authorRepository.findById(bookDto.getAuthorId())
				.orElseThrow(() -> new RuntimeException("Author not found with id: " + bookDto.getAuthorId()));

		Book book = bookMapper.toBook(bookDto);
		book.setAuthor(author);

		Book savedBook = bookRepository.save(book);

		return bookMapper.toBookDto(savedBook);
	}

	@Override
	public Page<BookDto> getAllBooks(Pageable pageable) {
		Page<Book> booksPage = bookRepository.findAll(pageable);
		return booksPage.map(bookMapper::toBookDto);
	}

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books.stream().map(bookMapper::toBookDto).collect(Collectors.toList());
	}

	@Override
	public BookDto getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
		return bookMapper.toBookDto(book);
	}

	@Override
	public BookDto updateBook(Long id, BookDto bookDto) {
		Book existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

		Author author = authorRepository.findById(bookDto.getAuthorId())
				.orElseThrow(() -> new RuntimeException("Author not found with id: " + bookDto.getAuthorId()));

		existingBook.setTitle(bookDto.getTitle());
		existingBook.setIsbn(bookDto.getIsbn());
		existingBook.setAuthor(author);

		Book updatedBook = bookRepository.save(existingBook);

		return bookMapper.toBookDto(updatedBook);
	}

	@Override
	public void deleteBook(Long id) {
		Book existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
		bookRepository.delete(existingBook);
	}
}
