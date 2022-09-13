package com.kopo.spring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kopo.spring.dto.UserDto;
import com.kopo.spring.exception.UserException;
import com.kopo.spring.exception.UserExceptionResult;
import com.kopo.spring.repository.UserRepository;
import com.kopo.spring.repository.entity.UserEntity;
import com.kopo.spring.service.UserCheckService;
import com.kopo.spring.service.UserService;
import com.kopo.spring.util.DateUtil;
import com.kopo.spring.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCheckService userCheckService;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserDto saveUserInfo(UserDto userDto) throws Exception {
        log.info(this.getClass().getName() + "saveUserInfo Start!");

        if (userCheckService.canUseEmail(userDto.getEmail()) && userCheckService.canUseUserId(userDto.getUserId())) {

            log.info(this.getClass().getName() + "saveUserInfo End!");

            return new ObjectMapper().convertValue(userRepository.save(UserEntity.builder()
                    .userId(userDto.getUserId())
                    .userName(userDto.getUserName())
                    .email(EncryptUtil.encAES128CBC(userDto.getEmail()))
                    .address1(userDto.getAddress1())
                    .address2(userDto.getAddress2())
                    .password(EncryptUtil.encHashSHA256(userDto.getPassword()))
                    .chgDT(DateUtil.getDateTime("yyyyMMdd"))
                    .regDt(DateUtil.getDateTime("yyyyMMdd")).build()), UserDto.class);
        } else {
            throw new RuntimeException("Server Error");
        }
    }

    @Override
    public UserDto userLogin(UserDto userDto) throws Exception {
        log.info(this.getClass().getName() + ".userLogin Start!");

        UserEntity user = userRepository.findById(userDto.getUserId()).orElseThrow(()
                -> new UserException(UserExceptionResult.USER_NOT_FOUND));


        if (EncryptUtil.encHashSHA256(userDto.getPassword()).equals(user.getPassword())) {
            return new ObjectMapper().convertValue(user, UserDto.class);
        } else {
            throw new UserException(UserExceptionResult.USER_PASSWORD_NOT_MATCHED);
        }
    }
}
