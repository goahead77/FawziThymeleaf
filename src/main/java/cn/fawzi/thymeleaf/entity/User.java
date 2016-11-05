package cn.fawzi.thymeleaf.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wenqi
 */

@Setter
@Getter
public class User {

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String userName;

}
