package com.seesun.springbootCRUD.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserCreateRequest {

    private String username;

    private Integer age;

    private String phoneNumber;
}
