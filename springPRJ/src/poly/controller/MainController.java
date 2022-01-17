package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.controller.comm.AbstractController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController extends AbstractController {

    @RequestMapping(value = "index")
    public String index(HttpServletRequest request, ModelMap model) throws Exception {
        log.info(this.getClass().getName()); // Git test

        return "/index";
    }
}
