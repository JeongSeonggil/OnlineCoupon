package poly.service.impl;

import org.springframework.stereotype.Service;
import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IJsonService;
import poly.service.IUserService;
import poly.service.comm.AbstractService;

import javax.annotation.Resource;

@Service(value = "UserService")
public class UserService extends AbstractService implements IUserService {
    @Resource(name = "JsonService")
    private IJsonService jsonService;

    @Resource(name = "UserMapper")
    private IUserMapper userMapper;
    @Override
    public int insertUserInfo(UserDTO pDTO) throws Exception {
        int res;
        log.info(this.getClass().getName() + ".insertUserInfo Start");

        UserDTO rDTO = userMapper.getUserExists(pDTO);
        if (rDTO.getExists_yn().equals("Y")) {
            log.info("회원 정보 중복");
            res = 2;
        } else {
            res = userMapper.insertUserInfo(pDTO);
        }

        log.info(this.getClass().getName() + ".insertUser End");

        return res;

    }
    @Override
    public UserDTO findUserInfo(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".findUserInfo Start!");

        UserDTO rDTO = userMapper.findUserInfo(pDTO);

        // 받아온 정보가 없을 경우
        if (rDTO == null) {
            rDTO = new UserDTO();
        }

        log.info(this.getClass().getName() + ".findUserInfo End!");

        return rDTO;

    }

    @Override
    public UserDTO getUserSeq(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getUserSeq Start!");

        UserDTO rDTO = userMapper.getUserSeq(pDTO);

        if (rDTO == null) {
            rDTO = new UserDTO();
        }

        log.info(this.getClass().getName() + ".getUserSEQ End!");

        return rDTO;
    }
}
