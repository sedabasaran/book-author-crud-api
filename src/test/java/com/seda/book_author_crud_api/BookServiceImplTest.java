package com.seda.book_author_crud_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.seda.book_author_crud_api.dto.BookDto;
import com.seda.book_author_crud_api.entities.Author;
import com.seda.book_author_crud_api.entities.Book;
import com.seda.book_author_crud_api.mapper.BookMapper;
import com.seda.book_author_crud_api.repository.AuthorRepository;
import com.seda.book_author_crud_api.repository.BookRepository;
import com.seda.book_author_crud_api.services.BookServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private AuthorRepository authorRepository;

	@Mock
	private BookMapper bookMapper;

	@InjectMocks
	private BookServiceImpl bookService;

	@Test
	public void testGetBookById_Success() {
		Book mockBook = new Book();
		mockBook.setId(1L);
		mockBook.setTitle("İnce Memed");
		mockBook.setIsbn("123-456789");

		when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

		BookDto mockBookDto = new BookDto();
		mockBookDto.setId(1L);
		mockBookDto.setTitle("İnce Memed");
		mockBookDto.setIsbn("123-456789");
		when(bookMapper.toBookDto(mockBook)).thenReturn(mockBookDto);

		BookDto result = bookService.getBookById(1L);

		assertEquals("İnce Memed", result.getTitle());
		assertEquals("123-456789", result.getIsbn());
		assertEquals(1L, result.getId());
	}

	@Test
	public void testGetBookById_NotFound() {
		when(bookRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> {
			bookService.getBookById(1L);
		});
	}

	@Test
	public void testCreateBook_Success() {
		BookDto inputDto = new BookDto();
		inputDto.setTitle("Aforizmalar");
		inputDto.setIsbn("987-654321");
		inputDto.setAuthorId(1L);

		Author mockAuthor = new Author();
		mockAuthor.setId(1L);
		mockAuthor.setName("Franz Kafka");

		when(authorRepository.findById(1L)).thenReturn(Optional.of(mockAuthor));

		Book mappedBook = new Book();
		mappedBook.setTitle("Aforizmalar");
		mappedBook.setIsbn("987-654321");
		when(bookMapper.toBook(inputDto)).thenReturn(mappedBook);

		Book savedBook = new Book();
		savedBook.setId(10L);
		savedBook.setTitle("Aforizmalar");
		savedBook.setIsbn("987-654321");
		savedBook.setAuthor(mockAuthor);
		when(bookRepository.save(mappedBook)).thenReturn(savedBook);

		BookDto savedDto = new BookDto();
		savedDto.setId(10L);
		savedDto.setTitle("Aforizmalar");
		savedDto.setIsbn("987-654321");
		savedDto.setAuthorId(1L);
		when(bookMapper.toBookDto(savedBook)).thenReturn(savedDto);

		BookDto result = bookService.createBook(inputDto);

		assertEquals(10L, result.getId());
		assertEquals("Aforizmalar", result.getTitle());
		assertEquals("987-654321", result.getIsbn());
		assertEquals(1L, result.getAuthorId());

		verify(authorRepository).findById(1L);
	}
}