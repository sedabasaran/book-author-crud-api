package com.seda.book_author_crud_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seda.book_author_crud_api.dto.AuthorDto;
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
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testCreateAuthor() throws Exception {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Sabahattin Ali ");

		mockMvc.perform(post("/api/authors").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(authorDto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Sabahattin Ali "));
	}

	@Test
	public void testGetAuthorById() throws Exception {
		AuthorDto authorDto = new AuthorDto();
		authorDto.setName("Yaşar Kemal");

		String response = mockMvc
				.perform(post("/api/authors").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(authorDto)))
				.andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

		AuthorDto createdAuthor = objectMapper.readValue(response, AuthorDto.class);

		mockMvc.perform(get("/api/authors/" + createdAuthor.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Yaşar Kemal"));
	}
}
