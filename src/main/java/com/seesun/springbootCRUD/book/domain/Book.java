package com.seesun.springbootCRUD.book.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.seesun.springbootCRUD.appUser.domain.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private Boolean isBorrowed;

    @ManyToOne()
    @JoinColumn(name = "app_user_id")
    @JsonManagedReference
    private AppUser appUser;

    public Book(String title, String author, Boolean isBorrowed) {
        this.title = title;
        this.author = author;
        this.isBorrowed = isBorrowed;
    }

    public void borrow(AppUser appUser) {
        this.appUser = appUser;
        this.isBorrowed = true;
    }

    public void returnBook() {
        this.appUser = null;
        this.isBorrowed = false;
    }
}
