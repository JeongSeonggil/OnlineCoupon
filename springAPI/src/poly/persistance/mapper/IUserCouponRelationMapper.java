package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserCouponRelationDTO;
import poly.dto.UserDTO;

import java.util.List;

@Mapper(value = "UserCouponRelationMapper")
public interface IUserCouponRelationMapper {
    List<UserCouponRelationDTO> findRelation(UserDTO pDTO) throws Exception; // 사용자가 가지고 있는 쿠폰의 seq 조회하기


}
