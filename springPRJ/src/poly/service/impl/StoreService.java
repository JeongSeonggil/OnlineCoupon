package poly.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import poly.dto.StoreDTO;
import poly.service.IJsonService;
import poly.service.IStoreService;
import poly.service.comm.AbstractService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service(value = "StoreService")
public class StoreService extends AbstractService implements IStoreService {
    @Resource(name = "JsonService")
    private IJsonService jsonService;

    @Override
    public int insertStoreInfo(StoreDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".insertUserInfo Start!");
        Map<String, Object> rMap = new HashMap<>();
        String callUrl = CmmUtil.nvl(pDTO.getUrl());

        String json = jsonService.getUrlForJSON(callUrl);

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        String res_s = jsonObject.get("res").toString();
        String store_id = jsonObject.get("store_id").toString();

        int res = Integer.parseInt(res_s);

        log.info("res : " + res);
        log.info("store_id : " + store_id);
        rMap = null;

        return res;
    }

}
