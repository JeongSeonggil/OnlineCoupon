package poly.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import poly.controller.comm.AbstractController;
import poly.dto.UserDTO;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
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

    @RequestMapping("/user/userJoin")
    public String userJoin(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".userJoin Start!");

        log.info(this.getClass().getName() + "userJoin End!");

        return "/user/userJoin";
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

            // seq 값 가져와서 QR 생성하기
            url = "http://localhost:8090/springAPI/user/getUserSeq.do?user_id=" + user_id;
            pDTO.setUrl(url);

            UserDTO rDTO = userService.getUserSeq(pDTO);

            String user_seq = CmmUtil.nvl(rDTO.getUser_seq());
            // 회원 가입 성공시 QR 코드 함께 생성
            String name = user_seq + ".png"; // 생성되는 파일 이름 / user_seq로 변경
            String qrUrl = "index.do?user_seq=" + user_seq;

            try {
                File file = null;
                // qr코드 이미지를 저장할 디렉토리 지정
                file = new File("/resources/QR/" + user_seq);
                if(!file.exists()) {
                    file.mkdirs();
                }
                // qr코드 인식시 이동할 url 주소
                String codeurl = new String(qrUrl.getBytes("UTF-8"), "ISO-8859-1");
                // qr코드 바코드 생성값
                int qrcodeColor = 0xFF2e4e96;
                // qr코드 배경색상값
                int backgroundColor = 0xFFFFFFFF;

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                // 3,4번째 parameter값 : width/height값 지정
                BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE,200, 200);
                //
                MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor,backgroundColor);
                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
                // ImageIO를 사용한 바코드 파일쓰기
                ImageIO.write(bufferedImage, "png", new File("/resources/QR/"+name));

            } catch (Exception e) {
                log.info("QR Exception : " + e.toString());
            }
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
