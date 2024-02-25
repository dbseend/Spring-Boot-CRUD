package com.seesun.springbootCRUD.book.controller;

import com.seesun.springbootCRUD.book.dto.request.BookCreateRequest;
import com.seesun.springbootCRUD.book.dto.request.BookLoanRequest;
import com.seesun.springbootCRUD.book.dto.response.BookAllResponse;
import com.seesun.springbootCRUD.book.dto.response.BookResponse;
import com.seesun.springbootCRUD.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    // 도서 등록 API
    @PostMapping("")
    public ResponseEntity<Void> registerBook(@RequestBody BookCreateRequest request) {

        bookService.registerBook(request);

        return ResponseEntity.ok().build();
    }

    // id를 통한 도서 조회 API
    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable Long bookId) {

        return ResponseEntity.ok(bookService.findBookById(bookId));
    }

    // 이름을 통한 도서 조회 API
    @GetMapping("/search")
    public ResponseEntity<BookAllResponse> findBookByTitle(@RequestParam String title) {

        return ResponseEntity.ok(bookService.findBookByTitle(title));
    }

    // 도서 전체 조회 API
    @GetMapping("")
    public ResponseEntity<BookAllResponse> findAllBooks() {

        return ResponseEntity.ok(bookService.findAllBooks());
    }

    // 도서 대여 API
    @PostMapping("/loan")
    public ResponseEntity<Void> loanBook(@RequestBody BookLoanRequest request) {

        bookService.borrowBook(request);

        return ResponseEntity.ok().build();
    }

    // 도서 반납 API
    @PostMapping("/return/{bookId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long bookId) {

        bookService.returnBook(bookId);

        return ResponseEntity.ok().build();
    }

}
