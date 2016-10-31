package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.annotation.OpenID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wenqi
 */

@Controller
@RequestMapping("/openId")
public class OpenIDController {


    @RequestMapping("/get")
    @ResponseBody
    public String getOpenId(@OpenID String openId){
        return openId;
    }

}
