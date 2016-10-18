package cn.fawzi.thymeleaf.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wenqi
 */

@Setter
@Getter
public class Foods {

    /**
     * 食物编号
     */
    private Integer fId;

    /**
     * 食物名称
     */
    private String fName;

    /**
     * 是否 油炸食品
     */
    private boolean isFry;

}
