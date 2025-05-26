package com.seda.book_author_crud_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seda.book_author_crud_api.dto.BookDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testCreateBookAndGetBooks() throws Exception {
		BookDto bookDto = new BookDto();
		bookDto.setTitle("Sırça Köşk");
		bookDto.setIsbn("1234567890");
		bookDto.setAuthorId(1L);

		String bookJson = objectMapper.writeValueAsString(bookDto);

		mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(bookJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("Sırça Köşk"))
				.andExpect(jsonPath("$.isbn").value("1234567890"));

		mockMvc.perform(
				get("/api/books").param("page", "0").param("size", "10").param("sortBy", "id")
				.param("sortDir", "asc")).andExpect(status().isOk())
		        .andExpect(jsonPath("$.content").isArray());
	}
}
