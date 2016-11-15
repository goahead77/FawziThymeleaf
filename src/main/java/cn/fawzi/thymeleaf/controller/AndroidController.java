package cn.fawzi.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author wenqi
 */
@Controller
@RequestMapping("/android")
public class AndroidController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult login(HttpServletRequest request) throws IOException {
        String json = StreamUtils.copyToString(request.getInputStream(), Charset.forName("utf-8"));
        System.out.println(json);
        return new ApiResult(200, "登录成功", null);
    }
}
