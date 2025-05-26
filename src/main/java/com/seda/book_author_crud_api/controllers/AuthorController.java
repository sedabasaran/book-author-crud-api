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
import org.springframework.web.bind.annotation.RestController;

import com.seda.book_author_crud_api.dto.AuthorDto;
import com.seda.book_author_crud_api.services.AuthorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/authors")
@RequiredArgsConstructor
public class AuthorController {

	private final AuthorService authorService;

	@PostMapping
	public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto authorDto) {
		AuthorDto created = authorService.createAuthor(authorDto);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<AuthorDto>> getAllAuthors() {
		List<AuthorDto> authors = authorService.getAllAuthors();
		return ResponseEntity.ok(authors);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
		AuthorDto authorDto = authorService.getAuthorById(id);
		return ResponseEntity.ok(authorDto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDto authorDto) {
		AuthorDto updated = authorService.updateAuthor(id, authorDto);
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
		authorService.deleteAuthor(id);
		return ResponseEntity.noContent().build();
	}
}
