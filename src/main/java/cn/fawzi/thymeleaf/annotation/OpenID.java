package cn.fawzi.thymeleaf.annotation;

import java.lang.annotation.*;

/**
 * @author wenqi
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OpenID {

    String value() default "";

}
