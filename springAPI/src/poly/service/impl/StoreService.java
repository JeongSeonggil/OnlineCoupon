package poly.service.impl;

import org.springframework.stereotype.Service;
import poly.dto.StoreDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IStoreMapper;
import poly.service.IStoreService;
import poly.service.comm.AbstractService;

import javax.annotation.Resource;

@Service(value = "StoreService")
public class StoreService extends AbstractService implements IStoreService {
    @Resource(name = "StoreMapper")
    private IStoreMapper storeMapper;

    @Override
    public int insertStoreInfo(StoreDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertStoreInfo Start!");
        int res = 0;
        StoreDTO rDTO = storeMapper.getStoreExists(pDTO);
        if (rDTO.getExists_yn().equals("Y")) {
            log.info("사업자 정보 중복");
            res = 2;
        } else {
            res = storeMapper.insertStoreInfo(pDTO);
        }

        log.info(this.getClass().getName() + ".insertUser End");

        return res;
    }
}
