package com.seesun.springbootCRUD.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserUpdateRequest {

    private String username;

    private Integer age;

    private String phoneNumber;
}
