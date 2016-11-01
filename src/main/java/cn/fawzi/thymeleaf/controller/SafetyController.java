package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.exception.IllegalRequestHeaderException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * 关于安全相关的测试
 * @author wenqi
 */
@Controller
@RequestMapping("/safe")
public class SafetyController {

    @RequestMapping(value = "/head",headers = "myHeader=fawzi")
    @ResponseBody
    public String header( ){
        return "OK";
    }

    @RequestMapping("/intoHeader")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void intoHeader(HttpServletRequest request) throws IOException, IllegalRequestHeaderException {
        String myHeader=request.getHeader("myHeader");
        if(StringUtils.isEmpty(myHeader))
            throw new IllegalRequestHeaderException();
    }

}
