package com.seda.book_author_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seda.book_author_crud_api.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
