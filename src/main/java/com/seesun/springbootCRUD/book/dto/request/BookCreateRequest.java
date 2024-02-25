package com.seesun.springbootCRUD.book.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookCreateRequest {

    private String title;
    private String author;
}
