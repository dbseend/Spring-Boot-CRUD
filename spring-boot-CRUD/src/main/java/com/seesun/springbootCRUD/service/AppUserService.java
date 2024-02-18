package com.seesun.springbootCRUD.service;

import com.seesun.springbootCRUD.domain.AppUser;
import com.seesun.springbootCRUD.domain.AppUserRepository;
import com.seesun.springbootCRUD.dto.request.AppUserCreateRequest;
import com.seesun.springbootCRUD.dto.request.AppUserUpdateRequest;
import com.seesun.springbootCRUD.dto.response.AppUserAllResponse;
import com.seesun.springbootCRUD.dto.response.AppUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 해당 클래스를 서비스로 등록
@RequiredArgsConstructor // Autowired를 사용하지 않고 final로 선언된 객체를 생성자로 주입받는다.
public class AppUserService {

    private final AppUserRepository appUserRepository;
    /*
    @Autowired
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    */

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
                appUser.getPhoneNumber()
        );
    }

    // 사용자 ID로 사용자 조회 메소드
    @Transactional
    public AppUserResponse readAppUser(Long id) {

        // JPA Repository에서 기본으로 제공하는 메소드는 Optional이므로 orElseThrow()를 사용하여 예외처리
        AppUser appUser = appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new AppUserResponse(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getAge(),
                appUser.getPhoneNumber()
        );
    }

    // 사용자 전체 조회 메소드
    @Transactional
    public AppUserAllResponse readAllAppUser() {

        List<AppUser> appUserList = appUserRepository.findAll();

        List<AppUserResponse> appUserResponseList = appUserList.stream()
                .map(appUser -> new AppUserResponse(
                        appUser.getId(),
                        appUser.getUsername(),
                        appUser.getAge(),
                        appUser.getPhoneNumber()
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
                appUser.getPhoneNumber()
        );
    }

    // 사용자 ID로 사용자 삭제 메소드
    @Transactional
    public void deleteAppUser(Long id) {
        appUserRepository.deleteById(id);
    }
}
