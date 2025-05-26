package com.seda.book_author_crud_api;

import com.seda.book_author_crud_api.controllers.BookController;
import com.seda.book_author_crud_api.dto.BookDto;
import com.seda.book_author_crud_api.services.BookService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private BookService bookService;

	@Test
	public void testGetBookById() throws Exception {
		BookDto mockBookDto = new BookDto();
		mockBookDto.setId(1L);
		mockBookDto.setTitle("Sırça Köşk");
		mockBookDto.setIsbn("1234567890");
		mockBookDto.setAuthorId(1L);

		when(bookService.getBookById(anyLong())).thenReturn(mockBookDto);

		mockMvc.perform(get("/api/books/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title")
			    .value("Sırça Köşk "))
				.andExpect(jsonPath("$.isbn")
			    .value("1234567890"));
	}
}