package cn.fawzi.thymeleaf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author wenqi
 */
@Setter
@Getter
@ToString
/**
 * 即时消息
 */
public class Message {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private Date time;

    /**
     * 发消息者
     */
    private String name;

}
