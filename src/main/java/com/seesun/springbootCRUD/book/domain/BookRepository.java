package com.seesun.springbootCRUD.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByAppUserId(Long appUserId);
}
