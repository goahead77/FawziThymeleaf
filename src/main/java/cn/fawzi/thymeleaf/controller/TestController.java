package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Foods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author wenqi
 */

@Controller
public class TestController {


    @RequestMapping(value = "/test")
    public String toTestPage(Model model){

        List<Foods> foodses=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Foods f=new Foods();
            f.setFId(i);
            f.setFName("食物"+i);
            foodses.add(f);
        }
        model.addAttribute("value", UUID.randomUUID().toString());
        model.addAttribute("foods", foodses);
        return "test";
    }



}
