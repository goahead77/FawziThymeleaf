package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Foods;
import cn.fawzi.thymeleaf.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author wenqi
 */

@Controller
public class TestController {

    @Autowired
    private FoodsService foodsService;

    @RequestMapping(value = "/test")
    public String toTestPage(Model model, HttpSession session, HttpServletRequest request){
        List<Foods> foodses=foodsService.createFoods();
        session.setAttribute("user","fawzi");
        model.addAttribute("home_welcome","home.welcome");
        model.addAttribute("value", UUID.randomUUID().toString());
        model.addAttribute("foods", foodses);
        session.setAttribute("user","fawzi");
        request.setAttribute("sex","男");
        request.setAttribute("selectFood",foodses.get(0));
        Calendar calendar=Calendar.getInstance();
        request.setAttribute("calendar",calendar);
        return "test";
    }
}
