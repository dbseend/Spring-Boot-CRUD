package com.seesun.springbootCRUD.appUser.dto.response;

import com.seesun.springbootCRUD.book.dto.response.BookAllResponse;
import com.seesun.springbootCRUD.book.dto.response.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserResponse {

    private Long id;

    private String username;

    private Integer age;

    private String phoneNumber;

    private BookAllResponse bookList;
}
