package com.seda.book_author_crud_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seda.book_author_crud_api.dto.BookDto;
import com.seda.book_author_crud_api.services.BookService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	@PostMapping
	public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
		BookDto createdBook = bookService.createBook(bookDto);
		return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Page<BookDto>> getAllBooks(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<BookDto> booksPage = bookService.getAllBooks(pageable);
		return ResponseEntity.ok(booksPage);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BookDto>> getAllBooksWithoutPagination() {
		List<BookDto> books = bookService.getAllBooks();
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
		BookDto book = bookService.getBookById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto bookDto) {
		BookDto updatedBook = bookService.updateBook(id, bookDto);
		return new ResponseEntity<>(updatedBook, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
