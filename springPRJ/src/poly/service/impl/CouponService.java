package poly.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import poly.dto.CouponDTO;
import poly.service.ICouponService;
import poly.service.IJsonService;
import poly.service.comm.AbstractService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value = "CouponService")
public class CouponService extends AbstractService implements ICouponService {

    @Resource(name = "JsonService")
    private IJsonService jsonService;

    @Override
    public List<CouponDTO> findCouponInfo(CouponDTO pDTO) throws Exception{
        log.info(this.getClass().getName() + "findCouponInfo Start!");

        // Coupon Map<String, Object>를 담을 List
        List<CouponDTO> rList = new ArrayList<>();
        String callUrl = CmmUtil.nvl(pDTO.getUrl());

        String json = jsonService.getUrlForJSON(callUrl);

        org.json.simple.parser.JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        org.json.simple.JSONObject jsonObject = (JSONObject) obj;

        rList = (List<CouponDTO>) jsonObject.get("rList");// rList : [CouponDTO, CouponDTO2] / 쿠폰 List
        log.info("rList : " + rList);


        log.info(this.getClass().getName() + "findCouponInfo End!");
        return rList;

    }
}
