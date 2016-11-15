package cn.fawzi.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wenqi
 */
@Controller
@RequestMapping("/android")
public class AndroidController {


    @RequestMapping(value = "/login",method = RequestMethod.POST,headers = {"android-agent=12345"})
    public ApiResult login(@RequestParam("userName") String userName,@RequestParam("pwd") String userPwd){
        if("admin".equals(userName) && "admin".equals(userPwd))
            return new ApiResult(200,"登录成功",null);
        else
            return new ApiResult(500,"登录失败",null);
    }
}
