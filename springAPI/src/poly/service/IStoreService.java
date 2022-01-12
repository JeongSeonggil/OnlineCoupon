package poly.service;

import poly.dto.StoreDTO;
import poly.dto.UserDTO;

public interface IStoreService {
    int insertStoreInfo(StoreDTO pDTO) throws Exception; // 사용자 정보 등록

//    StoreDTO findStoreInfo(StoreDTO pDTO) throws Exception; // 사용자 정보 조회  / 로그인
//
//    StoreDTO getStoreSeq(StoreDTO pDTO) throws Exception; // 사용자 SEQ (식별자 값) 조회
}
