package poly.service;

import poly.dto.UserDTO;

public interface IUserService {
    int insertUserInfo(UserDTO pDTO) throws Exception; // 사용자 정보 등록

    UserDTO findUserInfo(UserDTO pDTO) throws Exception; // 사용자 정보 조회  / 로그인

    UserDTO getUserSeq(UserDTO pDTO) throws Exception; // 사용자 SEQ (식별자 값) 조회
}
