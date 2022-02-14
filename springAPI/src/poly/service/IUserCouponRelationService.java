package poly.service;

import poly.dto.UserCouponRelationDTO;
import poly.dto.UserDTO;

import java.util.List;

public interface IUserCouponRelationService {
    List<UserCouponRelationDTO> findRelation(UserDTO pDTO) throws Exception;
}
