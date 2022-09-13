package com.kopo.spring.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class LoginUserRequest {
    @NotNull
    private String userId;

    @NotNull
    private String password;
}
