package poly.service;

import poly.dto.CouponDTO;

import java.util.List;
import java.util.Map;

public interface ICouponService {
    List<CouponDTO> findCouponInfo(CouponDTO pDTO) throws Exception; // 사용자 쿠폰 리스트 가져오기
}
