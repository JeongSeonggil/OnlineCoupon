package com.kopo.spring.service.impl;

import com.kopo.spring.exception.UserException;
import com.kopo.spring.exception.UserExceptionResult;
import com.kopo.spring.repository.UserRepository;
import com.kopo.spring.repository.entity.UserEntity;
import com.kopo.spring.service.UserCheckService;
import com.kopo.spring.util.EncryptUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCheckServiceImpl implements UserCheckService {
    private final UserRepository userRepository;

    @Override
    public boolean canUseEmail(String userEmail) throws Exception {
        log.info("userEmail : " + userEmail);

        Optional<UserEntity> result = userRepository.findByEmail(EncryptUtil.encAES128CBC(userEmail));

        if (result.isPresent()) {
            throw new UserException(UserExceptionResult.USER_EMAIL_ERROR);
        }

        return true;
    }

    @Override
    public boolean canUseUserId(String userId) throws Exception {

        Optional<UserEntity> result = userRepository.findById(userId);

        if (result.isPresent()) {
            throw new UserException(UserExceptionResult.USER_ID_ERROR);
        }

        return true;
    }
}
