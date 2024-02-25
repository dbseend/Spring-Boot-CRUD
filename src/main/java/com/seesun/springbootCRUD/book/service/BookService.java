package com.seesun.springbootCRUD.book.service;

import com.seesun.springbootCRUD.appUser.domain.AppUser;
import com.seesun.springbootCRUD.appUser.domain.AppUserRepository;
import com.seesun.springbootCRUD.book.domain.Book;
import com.seesun.springbootCRUD.book.domain.BookRepository;
import com.seesun.springbootCRUD.book.dto.request.BookCreateRequest;
import com.seesun.springbootCRUD.book.dto.request.BookLoanRequest;
import com.seesun.springbootCRUD.book.dto.response.BookAllResponse;
import com.seesun.springbootCRUD.book.dto.response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AppUserRepository appUserRepository;

    // 도서 등록
    public void registerBook(BookCreateRequest request) {

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isBorrowed(false)
                .build();
        bookRepository.save(book);
    }

    // id를 통한 도서 조회(도서 정보 + 도서 대여 여부)
    public BookResponse findBookById(Long id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다. id=" + id));

        return new BookResponse(book);
    }

    // 이름을 통한 도서 조회(도서 정보 + 도서 대여 여부)
    public BookAllResponse findBookByTitle(String title) {

        List<Book> bookList = bookRepository.findByTitle(title);

        List<BookResponse> bookResponseList = bookList.stream()
                .map(BookResponse::new)
                .toList();

        return new BookAllResponse(bookResponseList, bookResponseList.size());
    }

    // 도서 전체 조회(도서 정보 + 도서 대여 여부)
    public BookAllResponse findAllBooks() {

        List<Book> bookList = bookRepository.findAll();

        List<BookResponse> bookResponseList = bookList.stream()
                .map(BookResponse::new)
                .toList();

        return new BookAllResponse(bookResponseList, bookResponseList.size());
    }

    // 도서 대여
    public void borrowBook(BookLoanRequest request) {

        AppUser appUser = appUserRepository.findById(request.getAppUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id=" + request.getAppUserId()));

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다. id=" + request.getBookId()));

        if (book.getIsBorrowed()) {
            throw new IllegalArgumentException("해당 도서는 이미 대여 중입니다.");
        }

        book.borrow(appUser);
        bookRepository.save(book);
    }

    // 도서 반납
    public void returnBook(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("해당 도서가 존재하지 않습니다. id=" + bookId));
        book.returnBook();
        bookRepository.save(book);
    }
}
