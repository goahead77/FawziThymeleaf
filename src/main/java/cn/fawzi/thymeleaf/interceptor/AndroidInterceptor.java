package cn.fawzi.thymeleaf.interceptor;

import cn.fawzi.thymeleaf.Utils.MD5Util;
import cn.fawzi.thymeleaf.controller.ApiResult;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        ObjectMapper objectMapper=new ObjectMapper();
        if(request.getServletPath().startsWith("/android")){
            try{
                String authority= request.getHeader("user-authority");
                String md5Msg=MD5Util.md52String(authority);
                if(!md5Msg.split(",")[1].equals("fawzi77")){
                    String result= objectMapper.writeValueAsString(new ApiResult(500,"非法请求",null));
                    response.getWriter().write(result);
                    return false;

                }else{
                    String apiKey=request.getHeader("apiKey");
                    String contentSafety=request.getHeader("Content-Safety");
                    if(StringUtils.isEmpty(authority) || StringUtils.isEmpty(apiKey)|| StringUtils.isEmpty(contentSafety)){
                        String result= objectMapper.writeValueAsString(new ApiResult(500,"参数不能为空",null));
                        response.getWriter().write(result);
                        return false;
                    }
                }
            }catch (Exception e){
                String result= objectMapper.writeValueAsString(new ApiResult(500,"内部错误",null));
                response.getWriter().write(result);
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
