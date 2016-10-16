package cn.fawzi.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

/**
 * @author wenqi
 */

@Controller
public class TestController {


    @RequestMapping("/test")
    public String toTestPage(Model model){
        model.addAttribute("value", UUID.randomUUID().toString());
        return "test";
    }


}
