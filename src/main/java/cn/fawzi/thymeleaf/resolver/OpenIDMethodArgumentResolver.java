package cn.fawzi.thymeleaf.resolver;

import cn.fawzi.thymeleaf.annotation.OpenID;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wenqi
 */
public class OpenIDMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(OpenID.class)!=null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object openID = webRequest.getNativeRequest(HttpServletRequest.class).getSession(true).getAttribute("OpenID");
        if (openID == null) {
            openID = "my OpenID";
            webRequest.getNativeRequest(HttpServletRequest.class).getSession(true).setAttribute("OpenID", openID);
        }
        return openID;
    }
}
