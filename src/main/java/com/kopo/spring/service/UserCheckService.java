package com.kopo.spring.service;

public interface UserCheckService {
    boolean canUseEmail(String userEmail) throws Exception;

    boolean canUseUserId(String userId) throws Exception;

}
