package com.seda.book_author_crud_api.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Book title is required")
	@Column(nullable = false)
	private String title;

	@NotBlank(message = "ISBN is required")
	@Size(min = 10, max = 20, message = "ISBN must be between 10 and 20 characters")
	@Column(nullable = false, unique = true)
	private String isbn;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

}
