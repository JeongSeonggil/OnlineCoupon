package com.kopo.spring.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegUserRequest {

    @NotNull(message = "userId cannot be Null")
    @Size(min = 8)
    private String userId;

    @NotNull(message = "userName cannot be Null")
    private String userName;

    @NotNull
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotNull
    private String address1;

    private String address2;
}
