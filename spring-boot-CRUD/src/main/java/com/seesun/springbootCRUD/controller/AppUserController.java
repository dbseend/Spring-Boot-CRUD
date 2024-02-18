package com.seesun.springbootCRUD.controller;

import com.seesun.springbootCRUD.dto.request.AppUserCreateRequest;
import com.seesun.springbootCRUD.dto.request.AppUserUpdateRequest;
import com.seesun.springbootCRUD.dto.response.AppUserAllResponse;
import com.seesun.springbootCRUD.dto.response.AppUserResponse;
import com.seesun.springbootCRUD.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // RESTFul API를 처리하는 컨트롤러로 등록, JSON 또는 XML 형식의 데이터를 반환
@RequestMapping("/api/v0/appUser") // 해당 컨트롤러에서 처리하는 API의 공통 URL
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    // 사용자 등록 컨트롤러
    @PostMapping("") // request body에 json 형태로 데이터를 받아와서 리소스 생성
    public ResponseEntity<AppUserResponse> createAppUser(@RequestBody AppUserCreateRequest request) {

        AppUserResponse appUserResponse = appUserService.createAppUser(request);

        return ResponseEntity.ok(appUserResponse);
    }

    // 사용자 ID로 사용자 조회 컨트롤러
    @GetMapping("/{appUserId}") // path variable로 데이터를 받아와서 리소스 조회
    public ResponseEntity<AppUserResponse> readAppUser(@PathVariable Long appUserId) {

        AppUserResponse appUserResponse = appUserService.readAppUser(appUserId);

        return ResponseEntity.ok(appUserResponse);
    }

    // 사용자 전체 조회 컨트롤러
    @GetMapping("")
    public ResponseEntity<AppUserAllResponse> readAllAppUser() {

        AppUserAllResponse appUserAllResponse = appUserService.readAllAppUser();

        return ResponseEntity.ok(appUserAllResponse);
    }

    // 사용자 수정 컨트롤러
    @PatchMapping("/{appUserId}") // request body에 json 형태로 데이터를 받아와서 리소스 수정
    public ResponseEntity<AppUserResponse> updateAppUser(@PathVariable Long appUserId,  @RequestBody AppUserUpdateRequest request) {

        AppUserResponse appUserResponse = appUserService.updateAppUser(appUserId, request);

        return ResponseEntity.ok(appUserResponse);
    }

    // 사용자 삭제 컨트롤러
    @DeleteMapping("/{appUserId}") // path variable로 데이터를 받아와서 리소스 삭제
    public ResponseEntity<Void> deleteAppUser(@PathVariable Long appUserId) {

        appUserService.deleteAppUser(appUserId);

        return ResponseEntity.ok().build();
    }
}
