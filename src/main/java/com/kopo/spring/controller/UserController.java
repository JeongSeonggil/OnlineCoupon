package com.kopo.spring.controller;

import com.kopo.spring.dto.UserDto;
import com.kopo.spring.service.UserService;
import com.kopo.spring.vo.LoginUserRequest;
import com.kopo.spring.vo.RegUserRequest;
import com.kopo.spring.vo.RegUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.kopo.spring.constants.UserConstants.USER_SESSION_KEY;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/users")
    public String regUser(HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + "regUser Start");

        UserDto result = userService.saveUserInfo(UserDto.builder()
                .userId(String.valueOf(request.getParameter("userId")))
                .userName(String.valueOf(request.getParameter("userName")))
                .address1(String.valueOf(request.getParameter("userName")))
                .address2(String.valueOf(request.getParameter("address2")))
                .email(String.valueOf(request.getParameter("email")))
                .password(String.valueOf(request.getParameter("password"))).build());


        model.addAttribute("msg", result.getUserName() + "회원가입 성공");
        model.addAttribute("url", "/index");


        log.info(this.getClass().getName() + "regUser End");


        return "/redirect";
    }

    @PostMapping("/users/login")
    public String userLogin(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        log.info("userId : " + request.getParameter("userId"));


        UserDto result = userService.userLogin(UserDto.builder()
                .userId(String.valueOf(request.getParameter("userId")))
                .password(String.valueOf(request.getParameter("password"))).build());

        session.setAttribute(USER_SESSION_KEY, result);

        model.addAttribute("msg", result.getUserId() + "님 로그인");
        model.addAttribute("url", "/idnex");


        return "/redirect";
    }
}
