package com.kopo.spring.controller;

import com.kopo.spring.exception.UserExceptionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MainController {

    @RequestMapping("/index")
    public String index() {
        log.info("go to index page");
        return "/index";
    }

    @RequestMapping("/regForm")
    public String regForm() {
        log.info("go to Reg Form");
        return "/regForm";
    }


}

