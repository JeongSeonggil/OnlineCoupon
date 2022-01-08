package poly.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.net.URLCodec;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import poly.dto.UserDTO;
import poly.service.IJsonService;
import poly.service.IUserService;
import poly.service.comm.AbstractService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

@Service(value = "UserService")
public class UserService extends AbstractService implements IUserService {

    @Resource(name = "JsonService")
    private IJsonService jsonService;

    public int insertUserInfo(UserDTO pDTO) throws ParseException {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");
        Map<String, Object> rMap = new HashMap<String, Object>();

        String callUrl = CmmUtil.nvl(pDTO.getUrl());

        String json = jsonService.getUrlForJSON(CmmUtil.nvl(callUrl));

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        String res_s = jsonObject.get("res").toString();
        int res = Integer.parseInt(res_s);
        rMap = null;


        return res;
    }

    public UserDTO findUserInfo(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".findUserInfo Start!");

        UserDTO rDTO = new UserDTO();

        String callUrl = CmmUtil.nvl(pDTO.getUrl());

        String json = jsonService.getUrlForJSON(callUrl);

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        // API에서 받아온 정보
        String user_id = jsonObject.get("user_id").toString();
        String user_seq = jsonObject.get("user_seq").toString();
        String user_name = jsonObject.get("user_name").toString();
        String user_nic = jsonObject.get("user_nic").toString();
        String user_age = jsonObject.get("user_age").toString();
        String user_gender = jsonObject.get("user_gender").toString();

        rDTO.setUser_id(user_id);
        rDTO.setUser_age(user_age);
        rDTO.setUser_seq(user_seq);
        rDTO.setUser_gender(user_gender);
        rDTO.setUser_name(user_name);
        rDTO.setUser_nic(user_nic);

        log.info(this.getClass().getName() + ".findUserInfo End!");

        return rDTO;
    }

    public UserDTO getUserSeq(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".getUserSeq Start!");

        String callUrl = CmmUtil.nvl(pDTO.getUrl());

        String json = jsonService.getUrlForJSON(callUrl);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        String user_seq = jsonObject.get("user_seq").toString();
        String res = jsonObject.get("res").toString();

        log.info("user_seq : " + user_seq);

        if (res.equals("0")) {
            user_seq = "";
        }
        UserDTO rDTO = new UserDTO();
        rDTO.setUser_seq(user_seq);


        log.info(this.getClass().getName() + ".getUSerSeq End!");


        return rDTO;
    }
}
