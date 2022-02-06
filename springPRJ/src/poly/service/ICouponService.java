package poly.service;

import poly.dto.CouponDTO;

import java.util.List;
import java.util.Map;

public interface ICouponService {
    List<Map<String, Object>> findCouponInfo(CouponDTO pDTO) throws Exception; // 사용자 쿠폰 리스트 가져오기
}
