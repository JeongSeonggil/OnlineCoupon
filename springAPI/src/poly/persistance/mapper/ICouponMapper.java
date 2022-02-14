package poly.persistance.mapper;

import config.Mapper;
import poly.dto.CouponDTO;

import java.util.List;

@Mapper(value = "CouponMapper")
public interface ICouponMapper {
    List<CouponDTO> findCoupon(CouponDTO pDTO) throws Exception; // 쿠폰 찾기
    /*
    * 릴레이션쉽 테이블에서 user seq값으로 테이블을 조회 / store_seq와 같은 값을 찾고, 그 값을 가지고 쿠폰 리스트에서 store_seq + user_seq값으로 AND 쿼리
    * 작성하기, List<CouponDTO> 리스트를 리턴하기
    */
}
