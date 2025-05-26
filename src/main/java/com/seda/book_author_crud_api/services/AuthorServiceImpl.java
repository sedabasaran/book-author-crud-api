package com.seda.book_author_crud_api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.seda.book_author_crud_api.dto.AuthorDto;
import com.seda.book_author_crud_api.entities.Author;
import com.seda.book_author_crud_api.exceptions.ResourceNotFoundException;
import com.seda.book_author_crud_api.mapper.AuthorMapper;
import com.seda.book_author_crud_api.repository.AuthorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;
	private final AuthorMapper authorMapper;

	@Override
	public AuthorDto createAuthor(AuthorDto authorDto) {
		Author author = authorMapper.toEntity(authorDto);
		author = authorRepository.save(author);
		return authorMapper.toDto(author);
	}

	@Override
	public List<AuthorDto> getAllAuthors() {
		List<Author> authors = authorRepository.findAll();
		return authors.stream().map(authorMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public AuthorDto getAuthorById(Long id) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with id" + id));

		return authorMapper.toDto(author);
	}

	@Override
	public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
		Author existingAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with id" + id));
		existingAuthor.setName(authorDto.getName());
		existingAuthor = authorRepository.save(existingAuthor);
		return authorMapper.toDto(existingAuthor);
	}

	@Override
	public void deleteAuthor(Long id) {

		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
		authorRepository.delete(author);
	}

}
