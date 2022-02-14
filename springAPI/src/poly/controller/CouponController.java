package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.controller.comm.AbstractController;
import poly.dto.CouponDTO;
import poly.dto.UserDTO;
import poly.service.ICouponService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CouponController extends AbstractController {

    @Resource(name = "CouponService")
    private ICouponService couponService;

    @ResponseBody
    @RequestMapping(value = "springAPI/coupon/findCoupon")
    public Map<String, Object> findCoupon(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".findCoupon Start!");

        String user_seq = CmmUtil.nvl(request.getParameter("user_seq"));
        String store_seq = CmmUtil.nvl(request.getParameter("store_seq"));
        UserDTO pDTO = new UserDTO();
        pDTO.setUser_seq(user_seq);

        List<CouponDTO> rList = couponService.findCoupon(pDTO, store_seq); // 특정 사용자가 보유한 쿠폰 List

        Map<String, Object> rMap = new HashMap<>();
        rMap.put("rList", rList);


        return rMap;
    }
}
