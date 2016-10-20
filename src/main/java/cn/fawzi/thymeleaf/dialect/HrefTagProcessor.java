package cn.fawzi.thymeleaf.dialect;

import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author wenqi
 */
public class HrefTagProcessor extends AbstractLinkProcessor  {

    public HrefTagProcessor(String dialectPrefix){
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME);
    }
    private final static String TAG_NAME = "href";
}
