package com.seesun.springbootCRUD.dto.response;

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
}
