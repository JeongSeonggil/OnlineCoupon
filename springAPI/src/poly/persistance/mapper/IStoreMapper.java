package poly.persistance.mapper;

import config.Mapper;
import poly.dto.StoreDTO;
import poly.dto.UserDTO;

@Mapper(value = "StoreMapper")
public interface IStoreMapper {
    int insertStoreInfo(StoreDTO pDTO) throws Exception; // 유저 등록

    StoreDTO getStoreExists(StoreDTO pDTO) throws Exception; // 중복 확인

    StoreDTO findStoreInfo(StoreDTO pDTO) throws Exception; // 로그인

    StoreDTO getStoreSeq(StoreDTO pDTO) throws Exception; // seq 조회
}
