package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Foods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author wenqi
 */

@Controller
public class TestController {


    @RequestMapping(value = "/test")
    public String toTestPage(Model model, HttpSession session){

        List<Foods> foodses=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Foods f=new Foods();
            f.setFId(i);
            f.setFName("食物"+i);
            if(i%2==0)
                f.setFry(true);
            else
                f.setFry(false);
            foodses.add(f);
        }
        session.setAttribute("user","fawzi");
        model.addAttribute("home_welcome","home.welcome");
        model.addAttribute("value", UUID.randomUUID().toString());
        model.addAttribute("foods", foodses);
        return "test";
    }



}
