package cn.fawzi.thymeleaf.exception;

import cn.fawzi.thymeleaf.controller.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author wenqi
 */

@ControllerAdvice
@Controller
public class MyExceptionHandler {

    /**
     *  请求的header 异常,响应 511
     * @param ex {@link cn.fawzi.thymeleaf.exception.IllegalRequestHeaderException}
     */
//    @ResponseStatus(code = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    @ResponseBody
    @ExceptionHandler(value = IllegalRequestHeaderException.class)
    public ApiResult notAcceptHeader(IllegalRequestHeaderException ex){
        System.out.println("未能识别的head");
        return new ApiResult(500,ex.getMessage(),null);
    }

}
