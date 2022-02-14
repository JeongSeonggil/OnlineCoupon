package poly.service.impl;

import org.springframework.stereotype.Service;
import poly.dto.CouponDTO;
import poly.dto.UserCouponRelationDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.ICouponMapper;
import poly.persistance.mapper.IUserCouponRelationMapper;
import poly.service.ICouponService;
import poly.service.IUserCouponRelationService;
import poly.service.comm.AbstractService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service(value = "CouponService")
public class CouponService extends AbstractService implements ICouponService {

    @Resource(name = "CouponMapper")
    private ICouponMapper couponMapper;

    @Resource(name = "UserCouponRelationMapper")
    private IUserCouponRelationMapper userCouponRelationMapper;

    @Override
    public List<CouponDTO> findCoupon(UserDTO pDTO, String store_seq) throws Exception {
        log.info(this.getClass().getName() + ".findCoupon Start!");

        List<UserCouponRelationDTO> rList = userCouponRelationMapper.findRelation(pDTO); // Relation List

        List<CouponDTO> CrList = new ArrayList<>(); // 쿠폰 정보를 담을 List
        CouponDTO CpDTO = new CouponDTO(); // rList에서 가져온 쿠폰 seq 담아서 넘겨줄 DTO

        for (int i = 0; i < rList.size(); i++) {
            CpDTO.setCoupon_seq(rList.get(i).getUcr_coupon_seq()); // rList에서 꺼내온 seq Info
            CpDTO.setCoupon_store_seq(store_seq); // PRJ에서 넘어온 store Info

//        List<String> CpList = rList.parallelStream().filter(p -> p.getUcr_user_seq() ) / 리스트 필터링
//            CrList.add((CouponDTO) couponMapper.findCoupon(CpDTO)); 같은 Store에 여러장의 쿠폰이 있을 수 있기 때문에,
            CrList.addAll(couponMapper.findCoupon(CpDTO)); // List + List / [CouponDTO, CouponDTO2]
            log.info("CrList :" + CrList);
        }

        return CrList;
    }
}
