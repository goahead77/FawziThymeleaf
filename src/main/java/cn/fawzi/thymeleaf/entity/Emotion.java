package cn.fawzi.thymeleaf.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wenqi
 */

@Setter
@Getter
public class Emotion {

    @JsonProperty("phrase")
    private String phrase;

    @JsonProperty("type")
    private String type;

    private String url;

    private boolean hot;

    private boolean common;

    private String category;

    private String icon;

    private String value;

    private String picid;

}
