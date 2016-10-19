package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Foods;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * @author wenqi
 */

@Controller
public class TestController {

    @RequestMapping(value = "/test")
    public String toTestPage(Model model, HttpSession session, HttpServletRequest request){

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
        session.setAttribute("user","fawzi");
        request.setAttribute("sex","男");
        request.setAttribute("selectFood",foodses.get(0));

        Calendar calendar=Calendar.getInstance();
        request.setAttribute("calendar",calendar);
        return "test";
    }



}
