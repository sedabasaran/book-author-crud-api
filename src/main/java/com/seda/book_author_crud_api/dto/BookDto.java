package com.seda.book_author_crud_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

	private Long id;

	@NotBlank(message = "Book title must is required")
	@Size(min = 2, max = 200, message = "Title must be between 2 and 200 characters")
	private String title;

	@NotBlank(message = "ISBN is required")
	@Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
	private String isbn;

	@NotNull(message = "AuthorId must is required")
	private Long authorId;

}
