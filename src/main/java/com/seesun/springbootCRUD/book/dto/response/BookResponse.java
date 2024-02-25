package com.seesun.springbootCRUD.book.dto.response;

import com.seesun.springbootCRUD.appUser.dto.response.AppUserResponse;
import com.seesun.springbootCRUD.book.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private Boolean isBorrowed;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isBorrowed = book.getIsBorrowed();
    }
}
