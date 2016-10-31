package cn.fawzi.thymeleaf.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author wenqi
 */
@Component
@Aspect
public class OpenIDAop {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("@annotation(cn.fawzi.thymeleaf.annotation.OpenID)")
    public void openIdPointCut(){}


    @Before("openIdPointCut()")
    public void getOpenID(JoinPoint joinPoint) throws NoSuchMethodException {
        Object o=request.getParameter("OpenID");
        if(StringUtils.isEmpty(o))
            return;
        String OpenID=o.toString();
        String methodName = joinPoint.getSignature().getName();
        Class<?> classTarget = joinPoint.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method objMethod = classTarget.getMethod(methodName, par);
        OpenID openID = objMethod.getAnnotation(OpenID.class);
        System.out.println(OpenID+":"+openID);

    }

}
