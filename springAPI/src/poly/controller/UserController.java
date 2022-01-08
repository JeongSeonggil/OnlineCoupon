package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.controller.comm.AbstractController;
import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.service.impl.UserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
@Controller
public class UserController extends AbstractController {
    @Resource(name = "UserService")
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "springAPI/user/insertUserInfo", produces = "application/json; charset=utf8")
    public Map<String, Object> insertUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");

        Map<String, Object> rMap = new HashMap<String, Object>();

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_password = CmmUtil.nvl(request.getParameter("user_password"));
        String user_name = CmmUtil.nvl(EncryptUtil.decAES128CBC(request.getParameter("user_name")));
        String user_nic = CmmUtil.nvl(EncryptUtil.decAES128CBC(request.getParameter("user_nic")));
        String user_age = CmmUtil.nvl(request.getParameter("user_age"));
        String user_gender = CmmUtil.nvl(request.getParameter("user_gender"));

        log.info("user_id : " + user_id);
        log.info("user_name : " + user_name);
        log.info("user_nic : " + user_nic);
        log.info("user_age : " + user_age);
        log.info("user_gender : " + user_gender);

        UserDTO pDTO = new UserDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_password(user_password);
        pDTO.setUser_name(user_name);
        pDTO.setUser_nic(user_nic);
        pDTO.setUser_age(user_age);
        pDTO.setUser_gender(user_gender);

        int res = userService.insertUserInfo(pDTO);

        rMap.put("res", res);

        log.info(this.getClass().getName() + ".insertUserInfo End!");

        return rMap;
    }

    @ResponseBody
    @RequestMapping(value = "springAPI/user/findUserInfo", produces = "application/json; charset=utf8")
    public Map<String, Object> findUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info(this.getClass().getName() + ".findUserInfo Start!");

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_password = CmmUtil.nvl(request.getParameter("user_password"));

        log.info("user_id : " + user_id);

        UserDTO pDTO = new UserDTO();

        pDTO.setUser_id(user_id);
        pDTO.setUser_password(user_password);

        UserDTO rDTO = userService.findUserInfo(pDTO);
        pDTO = null;

        Map<String, Object> rMap = new HashMap<>();
        rMap.put("user_seq", CmmUtil.nvl(rDTO.getUser_seq()));
        rMap.put("user_id", CmmUtil.nvl(rDTO.getUser_id()));
        rMap.put("user_age", CmmUtil.nvl(rDTO.getUser_age()));
        rMap.put("user_gender", CmmUtil.nvl(rDTO.getUser_gender()));
        rMap.put("user_name", CmmUtil.nvl(rDTO.getUser_name()));
        rMap.put("user_nic", CmmUtil.nvl(rDTO.getUser_nic()));


        return rMap;


    }
    @ResponseBody
    @RequestMapping(value = "springAPI/user/getUserSeq", produces = "application/json; charset=utf8")
    public Map<String, Object> getUserSeq(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".getUserSeq Start!");
        Map<String, Object> rMap = new HashMap<>();
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        log.info("user_id : " + user_id);

        UserDTO pDTO = new UserDTO();

        pDTO.setUser_id(user_id);

        UserDTO rDTO = userService.getUserSeq(pDTO);
        String res = "";
        if (rDTO == null) {
            rDTO = new UserDTO();

            res = "0";

        } else {
            res = "1";
        }
        rMap.put("res", res);
        rMap.put("user_seq", rDTO.getUser_seq());

        log.info(this.getClass().getName() + ".getUserSeq End!");

        return rMap;
    }
}
