package cn.fawzi.thymeleaf.dialect;

import cn.fawzi.thymeleaf.service.FawziPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wenqi
 */

@Component
public class FawziDialect extends AbstractProcessorDialect{

    @Autowired
    private FawziPathService fawziPathService;


    protected FawziDialect() {
        super("FawziDialect", "fz", 900);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        HashSet<IProcessor> processors = new HashSet<>();
        processors.add(new HrefTagProcessor(fawziPathService,dialectPrefix));
        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}
