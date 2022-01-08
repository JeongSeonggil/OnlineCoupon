package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper(value = "UserMapper")
public interface IUserMapper {
    int insertUserInfo(UserDTO pDTO) throws Exception; // 유저 등록

    UserDTO getUserExists(UserDTO pDTO) throws Exception; // 중복 확인

    UserDTO findUserInfo(UserDTO pDTO) throws Exception; // 로그인

    UserDTO getUserSeq(UserDTO pDTO) throws Exception;
}
