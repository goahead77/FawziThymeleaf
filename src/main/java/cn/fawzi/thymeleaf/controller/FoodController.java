package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Foods;
import cn.fawzi.thymeleaf.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wenqi
 */

@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodsService foodsService;

    @RequestMapping("/foodDetail")
    public String foodDetail1(Integer fid, Model model, @RequestParam(value = "fType",required = false) String fType){
        Foods foods=foodsService.findById(fid);
        model.addAttribute("selectFood",foods);
        model.addAttribute("function","foodDetail");
        return "foodDetail";
    }

    @RequestMapping("/foodDetail/{fid}")
    public String foodDetail2(@PathVariable("fid") Integer fid, Model model
            ,@RequestParam(value = "fType",required = false) String fType){
        Foods foods=foodsService.findById(fid);
        model.addAttribute("selectFood",foods);
        model.addAttribute("url","/food/foodDetail/{fid}");//如果url中有"{}" 表达式，不会在html中解析出来 会解析为"/food/foodDetail"
        model.addAttribute("function","foodDetail");
        return "foodDetail";
    }

}
