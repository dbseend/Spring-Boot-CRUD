package com.seesun.springbootCRUD.appUser.service;

import com.seesun.springbootCRUD.appUser.domain.AppUser;
import com.seesun.springbootCRUD.appUser.domain.AppUserRepository;
import com.seesun.springbootCRUD.appUser.dto.response.AppUserAllResponse;
import com.seesun.springbootCRUD.appUser.dto.response.AppUserResponse;
import com.seesun.springbootCRUD.appUser.dto.request.AppUserCreateRequest;
import com.seesun.springbootCRUD.appUser.dto.request.AppUserUpdateRequest;
import com.seesun.springbootCRUD.book.domain.Book;
import com.seesun.springbootCRUD.book.domain.BookRepository;
import com.seesun.springbootCRUD.book.dto.response.BookAllResponse;
import com.seesun.springbootCRUD.book.dto.response.BookResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 해당 클래스를 서비스로 등록
@RequiredArgsConstructor // Autowired를 사용하지 않고 final로 선언된 객체를 생성자로 주입받는다.
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final BookRepository bookRepository;

    // 사용자 등록 메소드
    @Transactional
    public AppUserResponse createAppUser(AppUserCreateRequest request) {

        AppUser appUser = AppUser.builder()
                .username(request.getUsername())
                .age(request.getAge())
                .phoneNumber(request.getPhoneNumber())
                .build();
        appUserRepository.save(appUser);

        return new AppUserResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getAge(),
                appUser.getPhoneNumber(),
                null
        );
    }

    // 사용자 ID로 사용자 조회 메소드(인적 사항 + 도서 대여 여부)
    @Transactional
    public AppUserResponse readAppUser(Long id) {

        // JPA Repository에서 기본으로 제공하는 메소드는 Optional이므로 orElseThrow()를 사용하여 예외처리
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        List<Book> bookList = bookRepository.findByAppUserId(id);
        List<BookResponse> bookResponseList = bookList.stream()
                .map(BookResponse::new)
                .toList();
        BookAllResponse bookAllResponse = new BookAllResponse(bookResponseList, bookResponseList.size());

        return new AppUserResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getAge(),
                appUser.getPhoneNumber(),
                bookAllResponse
        );
    }

    // 사용자 전체 조회 메소드(인적 사항)
    @Transactional
    public AppUserAllResponse readAllAppUser() {

        List<AppUser> appUserList = appUserRepository.findAll();

        List<AppUserResponse> appUserResponseList = appUserList.stream()
                .map(appUser -> new AppUserResponse(
                        appUser.getId(),
                        appUser.getUsername(),
                        appUser.getAge(),
                        appUser.getPhoneNumber(),
                        null
                ))
                .toList();

        return new AppUserAllResponse(appUserResponseList, appUserResponseList.size());
    }

    // 사용자 ID로 사용자 수정 메소드
    @Transactional
    public AppUserResponse updateAppUser(Long appUserId, AppUserUpdateRequest request) {

        AppUser appUser = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + appUserId));

        appUser.update(request.getUsername(), request.getAge(), request.getPhoneNumber());
        appUserRepository.save(appUser);

        return new AppUserResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getAge(),
                appUser.getPhoneNumber(),
                null
        );
    }

    // 사용자 ID로 사용자 삭제 메소드
    @Transactional
    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }
}
