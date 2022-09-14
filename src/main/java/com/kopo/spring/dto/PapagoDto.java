package com.kopo.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PapagoDto {
    private String langCode; // 원문의 언어

    private String text; // 분석을 위한 문장

    private String translatedText;
    private String scrLangType; // 번역 전 문장 사용 언어
    private String tarLangType; // 번역 후 문장 사용 언오
}
