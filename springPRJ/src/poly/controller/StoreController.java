package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import poly.controller.comm.AbstractController;
import poly.dto.StoreDTO;
import poly.service.IStoreService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
public class StoreController extends AbstractController {
    @Resource(name = "StoreService")
    private IStoreService storeService;

    @RequestMapping(value = "store/storeJoin") // 사업자 회원가입
    public String storeJoin()throws Exception {
        log.info(this.getClass().getName() + ".store/storeJoin Start!");

        log.info(this.getClass().getName() + ".store/storeJoin End!");

        return "/store/storeJoin";
    }

    @RequestMapping(value = "/store/storeLogin")
    public String storeLogin()throws Exception {
        log.info(this.getClass().getName() + ".store/storeLogin Start!");

        log.info(this.getClass().getName() + ".store/storeLogin End!");

        return "/store/storeLogin";
    }

    @RequestMapping(value = "store/insertStoreInfo", method = RequestMethod.POST)
    public String insertStoreInfo(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".store/insertStoreInfo Start!");

        StoreDTO pDTO = new StoreDTO();
        String msg = "";
        String url = "";

        log.info("store_id : " + CmmUtil.nvl(request.getParameter("store_id")));
        String store_id = CmmUtil.nvl(request.getParameter("store_id"));
        String store_password = CmmUtil.nvl(EncryptUtil.encHashSHA256(request.getParameter("store_password")));
        String store_address = CmmUtil.nvl(request.getParameter("store_address"));
        String store_address2 = CmmUtil.nvl(request.getParameter("store_address2"));

        url = "http://localhost:9000/springAPI/store/insertStoreInfo.do?1=1";

        url += "&store_id=" + store_id;
        url += "&store_password=" + store_password;
        url += "&store_address=" + URLEncoder.encode(store_address, "UTF-8");
        url += "&store_address2=" + URLEncoder.encode(store_address2, "UTF-8");

        pDTO.setUrl(url);
        int res = storeService.insertStoreInfo(pDTO);

        if (res == 2) {
            log.info("사업자 정보 중복");
            url = "/index.do";
            msg = "사업자 정보 중복";
        } else if (res == 1) {
            log.info("회원가입 성공");
            msg = "회원가입 성공";
            url = "/index.do";
        } else {
            log.info("Error");
            msg = "오류로 인한 가입 실패";
            url = "/index.do";
        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);

        return "/redirect";
    }

    @RequestMapping(value = "store/findStoreInfo", method = RequestMethod.POST)
    public String findStoreInfo(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".findStoreInfo Start!");
        String store_id = CmmUtil.nvl(request.getParameter("store_id"));
        String store_password = CmmUtil.nvl(EncryptUtil.encHashSHA256(request.getParameter("store_password")));
        String url = "http://localhost:9000/springAPI/store/findStoreInfo.do?1=1";

        url += "&store_id=" + store_id;
        url += "&store_password=" + store_password;
        StoreDTO pDTO = new StoreDTO();
        pDTO.setUrl(url);

        StoreDTO rDTO = storeService.findStoreInfo(pDTO);
        log.info("rDTO : " + rDTO.getStore_seq());
        return "/index";

    }


}
