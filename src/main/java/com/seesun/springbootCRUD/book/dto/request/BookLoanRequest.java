package com.seesun.springbootCRUD.book.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookLoanRequest {

    private Long bookId;

    private Long appUserId;
}
