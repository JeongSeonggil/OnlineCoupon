package com.kopo.spring.service;

import com.kopo.spring.dto.UserDto;

public interface UserService {


    UserDto saveUserInfo(UserDto userDto) throws Exception;

    UserDto userLogin(UserDto userDto) throws Exception;
}
