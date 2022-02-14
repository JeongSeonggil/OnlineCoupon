package poly.service;

import poly.dto.CouponDTO;
import poly.dto.UserDTO;

import java.util.List;

public interface ICouponService {
    List<CouponDTO> findCoupon(UserDTO pDTO, String store_seq) throws Exception; // 쿠폰 조회하기
}
