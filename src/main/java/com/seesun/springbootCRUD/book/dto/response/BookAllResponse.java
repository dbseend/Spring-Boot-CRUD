package com.seesun.springbootCRUD.book.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookAllResponse {

    private List<BookResponse> bookList;

    private Integer count;
}
