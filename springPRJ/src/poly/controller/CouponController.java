package poly.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.controller.comm.AbstractController;
import poly.dto.CouponDTO;
import poly.dto.UserCouponRelationDTO;
import poly.service.ICouponService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CouponController extends AbstractController {

    @Resource(name = "CouponService")
    private ICouponService couponService;

    @RequestMapping(value = "coupon/findCouponInfo")
    public String findCouponInfo(HttpSession session, HttpResponse response, ModelMap model, HttpServletRequest request) throws Exception{
        log.info(this.getClass().getName() + ".findCouponInfo Start!");

        // user_seq 를 가져오기 UR L에서 get // QR 생성 시 적용
        String user_seq = CmmUtil.nvl(request.getParameter("user_seq"));
        String store_seq = CmmUtil.nvl((String) session.getAttribute("store_seq"));

        try {
            // DTO값을 넘겨주지 않고 사용자 seq 값과 사업자 seq 값을 따로 넘겨준 후 API 서버에서 처리
            CouponDTO pDTO = new CouponDTO();

            String url = "http://localhost:9000/springAPI/coupon/findCoupon.do?1=1";
            url += "&user_seq=" + user_seq;
            url += "&store_seq=" + store_seq;

            pDTO.setUrl(url);

            List<Map<String, Object>> rList = couponService.findCouponInfo(pDTO);

            model.addAttribute("rList", rList);




            /* 만약 사용자가 여러 개의 쿠폰을 가지고 있을 경우 / 사용자가 가지고 있는 쿠폰을 조회 (CouponDTO)
             * 겨울 여름으로 쿠폰을 발행할 경우 찍어야하는 쿠폰이 다름 일단 리스트 조회 / 인터페이스 제공 무슨 쿠폰에 찍을지
             * But 같은 store에서 발행된 쿠폰 리스트만 제공해야하기 때문에 쿠폰 DTO에 담아서 AND 조건으로 조회
             * 선택 시 쿠폰 seq와 함께 카운트 증가 or 쿠폰이 없을 경우 생성 **
             * 1. requst에서 사용자 seq, 사업자 seq 넘겨주기
             *
             *
             *
             *
             * */
        } catch (Exception e) {
            log.info("Exception :" + e);
        }


        return "user/userMain"; // 수정


    }

}
