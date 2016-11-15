package cn.fawzi.thymeleaf.interceptor;

import cn.fawzi.thymeleaf.Utils.MD5Util;
import cn.fawzi.thymeleaf.exception.IllegalRequestHeaderException;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wenqi
 */
public class AndroidInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getServletPath().startsWith("/android")){
           String authority= request.getHeader("user-authority");
            if(!MD5Util.md52String(authority).split(",")[1].equals("fawzi77")){
                throw new IllegalRequestHeaderException("非法请求");
            }else{
                String apiKey=request.getHeader("apiKey");
                String contentSafety=request.getHeader("Content-Safety");
                if(StringUtils.isEmpty(authority) || StringUtils.isEmpty(apiKey)|| StringUtils.isEmpty(contentSafety)){
                    throw new IllegalRequestHeaderException("参数不全");
                }
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
