package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.controller.comm.AbstractController;
import poly.dto.StoreDTO;
import poly.service.IStoreService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StoreController extends AbstractController {
    @Resource(name = "StoreService")
    private IStoreService storeService;

    @ResponseBody
    @RequestMapping(value = "springAPI/store/insertStoreInfo", produces = "application/json; charset=utf8")
    public Map<String, Object> insertStoreInfo(HttpServletRequest request, ModelMap model, HttpSession session) throws Exception {
        log.info(this.getClass().getName() + ".springAPI insertStore Start!");
        Map<String, Object> rMap = new HashMap<>();

        StoreDTO pDTO = new StoreDTO();
        try {
            String store_id = CmmUtil.nvl(request.getParameter("store_id"));
            String store_password = CmmUtil.nvl(request.getParameter("store_password"));
            String store_address = CmmUtil.nvl(URLDecoder.decode(request.getParameter("store_address"), "UTF-8"));
            String store_address2 = CmmUtil.nvl(URLDecoder.decode(request.getParameter("store_address2"), "UTF-8"));


            pDTO.setStore_id(store_id);
            pDTO.setStore_password(store_password);
            pDTO.setStore_address(store_address);
            pDTO.setStore_address2(store_address2);
            int res = storeService.insertStoreInfo(pDTO);

            log.info("res : " + res);

            rMap.put("store_id", store_id);
            rMap.put("res", res);
        } catch (Exception e) {
            log.info("Exception : " + e);
        }

        return rMap;
    }
    @ResponseBody
    @RequestMapping(value = "springAPI/store/findStoreInfo", produces = "application/json; charset=utf8")
    public Map<String, Object> findStoreInfo(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".findStoreInfo Start!");
        Map<String, Object> rMap = new HashMap<>();
        StoreDTO pDTO = new StoreDTO();

        String store_id = CmmUtil.nvl(request.getParameter("store_id"));
        String store_password = CmmUtil.nvl(request.getParameter("store_password"));
        log.info("store_id : " + store_id);

        pDTO.setStore_id(store_id);
        pDTO.setStore_password(store_password);

        StoreDTO rDTO = storeService.findStoreInfo(pDTO);

        String store_seq = CmmUtil.nvl(rDTO.getStore_seq());
        store_id = CmmUtil.nvl(rDTO.getStore_id());
        String store_address = CmmUtil.nvl(rDTO.getStore_address());
        String store_address2 = CmmUtil.nvl(rDTO.getStore_address2());
        rMap.put("rDTO", rDTO);

        return rMap;
    }
}
