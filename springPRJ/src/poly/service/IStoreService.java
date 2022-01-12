package poly.service;

import poly.dto.StoreDTO;

public interface IStoreService {
    int insertStoreInfo(StoreDTO pDTO) throws Exception;
}
