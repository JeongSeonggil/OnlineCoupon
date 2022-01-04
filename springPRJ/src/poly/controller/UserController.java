package poly.controller;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import poly.controller.comm.AbstractController;
import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

@Controller
public class UserController extends AbstractController {
    @Resource(name = "UserService")
    private IUserService userService;


    @RequestMapping(value = "user/userMain")
    public String userMain(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".userMain Start!");

        log.info(this.getClass().getName() + ".userMain End!");

        return "/user/userMain";
    }
    @RequestMapping(value = "user/insertUserInfo", method = RequestMethod.POST)
    public String insertUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        UserDTO pDTO = new UserDTO();

        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_password_t = CmmUtil.nvl(request.getParameter("user_password"));
        String user_name_t = CmmUtil.nvl(request.getParameter("user_name"));
        String user_nic_t = CmmUtil.nvl(request.getParameter("user_nic"));
        String user_age = CmmUtil.nvl(request.getParameter("user_age"));
        String user_gender = CmmUtil.nvl(request.getParameter("user_gender"));

        log.info("user_id : " + user_id);

        String user_password = EncryptUtil.encHashSHA256(user_password_t);
        String user_name = EncryptUtil.encAES128CBC(user_name_t);
        String user_nic = EncryptUtil.encAES128CBC(user_nic_t);

        String url = "http://localhost:8090/springAPI/user/insertUserInfo.do?1=1";

        url += "&user_id=" + user_id;
        url += "&user_password=" + user_password;
        url += "&user_name=" + user_name;
        url += "&user_nic=" + user_nic;
        url += "&user_age=" + user_age;
        url += "&user_gender=" + user_gender;
        String msg = "";

        log.info("url : " + url);

        pDTO.setUrl(url);

        int res = userService.insertUserInfo(pDTO);

        if (res == 2) {
            msg = "회원 정보 중복";

        } else if (res == 1) {
            msg = "회원 가입 성공";
        }else{
            msg = "회원 가입 실패";
        }

        log.info(res);
        model.addAttribute("msg", msg);
        model.addAttribute("url", "/index.do");
        pDTO = null;


        return "/redirect";
    }

    @RequestMapping(value = "user/findUserInfo")
    public String findUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model , HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".findUserInfo Start !");
        UserDTO pDTO = new UserDTO();

        // jsp에서 로그인 정보 받아오기
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));
        String user_password_t = CmmUtil.nvl(request.getParameter("user_password"));
        log.info("user_id : " + user_id);

        //인코딩
        String user_password = EncryptUtil.encHashSHA256(user_password_t);

        String url = "http://localhost:8090/springAPI/user/findUserInfo.do?1=1";
        url += "&user_id=" + user_id;
        url += "&user_password=" + user_password;

        pDTO.setUser_id(user_id);
        pDTO.setUser_password(user_password);
        pDTO.setUrl(url);

        UserDTO rDTO = userService.findUserInfo(pDTO);
        pDTO = null;

        String msg = "";

        if (rDTO.getUser_seq().equals("")) {
            rDTO = new UserDTO();
            msg = "회원 정보를 확인하세요";
            url = "/index.do";
        } else {
            msg = "로그인 성공";
            session.setAttribute("user_id", rDTO.getUser_id());
            session.setAttribute("user_seq", rDTO.getUser_seq());
            session.setAttribute("user_name", rDTO.getUser_name());
            session.setAttribute("user_nic", rDTO.getUser_nic());
            url = "/user/userMain.do";
            // session 에 정보 저장
        }
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "/redirect";
    }

}
