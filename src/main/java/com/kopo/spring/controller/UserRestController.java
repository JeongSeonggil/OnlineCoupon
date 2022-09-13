package com.kopo.spring.controller;

import com.kopo.spring.dto.UserDto;
import com.kopo.spring.service.UserService;
import com.kopo.spring.vo.RegUserRequest;
import com.kopo.spring.vo.RegUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest")
public class UserRestController {
    private final UserService userService;


    @PostMapping("/users")
    public ResponseEntity<RegUserResponse> regUser(@RequestBody @Validated RegUserRequest request, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + "regUser Start");

        UserDto result = userService.saveUserInfo(UserDto.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .address1(request.getAddress1())
                .address2(request.getAddress2())
                .email(request.getEmail())
                .password(request.getPassword()).build());


        log.info(this.getClass().getName() + "regUser End");


        return ResponseEntity.status(HttpStatus.CREATED).body(RegUserResponse.builder()
                .userId(result.getUserId()).build());
    }

//    @PostMapping("/users/login")
//    public
}
