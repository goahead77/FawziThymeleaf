package cn.fawzi.thymeleaf.dialect;

import cn.fawzi.thymeleaf.service.FawziPathService;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * @author wenqi
 */
public class HrefTagProcessor extends AbstractLinkProcessor  {

    public HrefTagProcessor(FawziPathService fawziPathService,String dialectPrefix){
        super(TemplateMode.HTML, dialectPrefix, TAG_NAME,fawziPathService);
    }
    private final static String TAG_NAME = "href";
}
