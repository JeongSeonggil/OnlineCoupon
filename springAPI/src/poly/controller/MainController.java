package poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.controller.comm.AbstractController;
@Controller
public class MainController extends AbstractController {

    @RequestMapping(value="index")
    public String Index() {
        log.info(this.getClass());

        return "/index";
    }

}
