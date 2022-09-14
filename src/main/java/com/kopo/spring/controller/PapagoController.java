package com.kopo.spring.controller;

import com.kopo.spring.service.impl.PapagoService;
import com.kopo.spring.util.CmmUtil;
import com.kopo.spring.dto.PapagoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PapagoController {
    private final PapagoService papagoService;


    @GetMapping("/papagos/detectLangs")
    public PapagoDto detectLangs(HttpServletRequest request) throws Exception {

        PapagoDto response = papagoService.detectLangs(CmmUtil.nvl(
                request.getParameter("text")));

        return response != null ? response : new PapagoDto();
    }

    @GetMapping("/papagos/translate")
    public PapagoDto translate(HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + ".translate Start!");

        String text = CmmUtil.nvl(request.getParameter("text"));

        log.info("text : " + text);

        PapagoDto result = papagoService.translate(PapagoDto.builder().text(text).build());

        if (result.equals(null)) {
            result = new PapagoDto();
        }

        log.info(this.getClass().getName() + ".translate End!");


        return result;
    }
}
