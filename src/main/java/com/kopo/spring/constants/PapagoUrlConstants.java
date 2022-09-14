package com.kopo.spring.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PapagoUrlConstants {
    public static final String DETECT_LANG_API_URL = "https://openapi.naver.com/v1/papago/detectLangs";
    public static final String TRANSLATE_API_URL = "https://openapi.naver.com/v1/papago/n2mt";

}
