package poly.service.impl;

import org.springframework.stereotype.Service;
import poly.dto.UserCouponRelationDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IUserCouponRelationMapper;
import poly.service.IUserCouponRelationService;
import poly.service.comm.AbstractService;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "UserCouponRelationService")
public class UserCouponRelationService extends AbstractService implements IUserCouponRelationService {

    @Resource(name = "UserCouponRelationMapper")
    private IUserCouponRelationMapper userCouponRelationMapper;

    @Override
    public List<UserCouponRelationDTO> findRelation(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".findRelation Start!");

        List<UserCouponRelationDTO> rList = userCouponRelationMapper.findRelation(pDTO);


        log.info(this.getClass().getName() + ".findRelation End!");

        return rList;
    }


}
