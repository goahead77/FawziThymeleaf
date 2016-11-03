package cn.fawzi.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wenqi
 */

@Controller
public class IndexController {


    @RequestMapping("/index")
    public String indexPage(){
        return "index";
    }
}
