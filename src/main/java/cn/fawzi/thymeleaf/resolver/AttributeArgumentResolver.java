package cn.fawzi.thymeleaf.resolver;

import cn.fawzi.thymeleaf.annotation.OpenID;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author wenqi
 */
public class AttributeArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(OpenID.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String attributeName = methodParameter.getParameterAnnotation(OpenID.class).value();
        if (StringUtils.isEmpty(attributeName)) {
            attributeName = methodParameter.getParameterName();
        }
        return webRequest.getAttribute(attributeName, RequestAttributes.SCOPE_REQUEST);
    }
}
