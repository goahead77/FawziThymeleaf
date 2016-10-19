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
     * <i>注意：如果命名是isXX,则在html中应该写th:XXX ，去掉is，第二个单词首字母大写就可以</i>
     */
    private boolean isFry;

}
