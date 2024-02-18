package com.seesun.springbootCRUD.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserAllResponse {

    private List<AppUserResponse> appUserResponseList;

    private Integer count;
}
