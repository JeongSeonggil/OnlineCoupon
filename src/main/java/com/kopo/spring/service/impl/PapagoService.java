package com.kopo.spring.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kopo.spring.exception.PapagoException;
import com.kopo.spring.exception.PapagoExceptionResult;
import com.kopo.spring.util.CmmUtil;
import com.kopo.spring.util.NetworkUtil;
import com.kopo.spring.dto.PapagoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.kopo.spring.constants.PapagoUrlConstants.DETECT_LANG_API_URL;
import static com.kopo.spring.constants.PapagoUrlConstants.TRANSLATE_API_URL;

@Slf4j
@RequiredArgsConstructor
@Service
public class PapagoService {


    @Value("${papago.clientId}")
    private String clientId;

    @Value("${papago.clientSecret}")
    private String clientSecret;

    public PapagoDto detectLangs(final String rText) throws Exception {
        log.info(this.getClass().getName() + "detectLangs Start!");

        String text = CmmUtil.nvl(rText);
        String param = "query=" + URLEncoder.encode(text, "UTF-8");
        String json = NetworkUtil.post(DETECT_LANG_API_URL, this.setNaverInfo(), param);

        log.info("json");

        PapagoDto response = new ObjectMapper().readValue(json, PapagoDto.class);
        response.setText(text);


        log.info(this.getClass().getName() + "detectLangs End!");

        return response;
    }


    public PapagoDto translate(final PapagoDto request) throws Exception {
        log.info(this.getClass().getName() + ".translate Start!");

        final String langCode = CmmUtil.nvl(this.detectLangs(request.getText()).getLangCode());

        StringBuilder postParmas = new StringBuilder("");
        if (langCode.equals("ko")) {
            postParmas.append("source=ko&target=en&text=" + URLEncoder.encode(request.getText(), "UTF-8"));

        } else if (langCode.equals("en")) {
            postParmas.append("source=en&target=ko&text=" + URLEncoder.encode(request.getText(), "UTF-8"));
        } else {
            throw new PapagoException(PapagoExceptionResult.BAD_LANGUAGE_TYPE);
        }

        log.info("postParams : " + postParmas.toString());

        String json = NetworkUtil.post(TRANSLATE_API_URL, this.setNaverInfo(), postParmas.toString());

        log.info("json : ", json);
        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, LinkedHashMap.class);
        Map<String, Object> messageMap = (Map<String, Object>) jsonMap.get("message");
        final Map<String, String> resultMap = (Map<String, String>) messageMap.get("result");

        log.info("result : " + resultMap);

        log.info(this.getClass().getName() + ".translate End!");


        return PapagoDto.builder()
                .text(request.getText())
                .translatedText(CmmUtil.nvl(resultMap.get("translatedText")))
                .scrLangType(CmmUtil.nvl(resultMap.get("srcLangType")))
                .tarLangType(CmmUtil.nvl(resultMap.get("tarLangType"))).build();
    }


    private Map<String, String> setNaverInfo() {
        Map<String, String> requestHeader = new HashMap<>();
        requestHeader.put("X-Naver-Client-Id", clientId);
        requestHeader.put("X-Naver-Client-Secret", clientSecret);
        requestHeader.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        log.info("clientId : " + clientId);
        return requestHeader;
    }
}
