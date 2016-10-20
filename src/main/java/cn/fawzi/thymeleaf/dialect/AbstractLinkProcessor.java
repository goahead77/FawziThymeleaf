package cn.fawzi.thymeleaf.dialect;

import cn.fawzi.thymeleaf.service.FawziPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.*;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.FragmentExpression;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.StandardExpressionExecutionContext;
import org.thymeleaf.standard.util.StandardProcessorUtils;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.Validate;
import org.unbescape.html.HtmlEscape;

/**
 * @author wenqi
 */
public class AbstractLinkProcessor extends AbstractAttributeTagProcessor implements IAttributeDefinitionsAware {

    private final String attributeName;
    private AttributeDefinition targetAttributeDefinition;
    private FawziPathService fawziPathService;

    public AbstractLinkProcessor(TemplateMode templateMode, String dialectPrefix, String attributeName,FawziPathService fawziPathService) {
        super(templateMode, dialectPrefix, null, false, attributeName, true, 1000, true);
        this.attributeName=attributeName;
        this.fawziPathService=fawziPathService;
    }


    @Override
    public void setAttributeDefinitions(AttributeDefinitions attributeDefinitions) {
        Validate.notNull(attributeDefinitions, "Attribute Definitions cannot be null");
        this.targetAttributeDefinition = attributeDefinitions.forName(getTemplateMode(), attributeName);
    }

    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName
            , String attributeValue, IElementTagStructureHandler structureHandler) {
        String targetLink = attributeValue;
        try {
            final Object expressionResult;
            if (attributeValue != null) {

                final IStandardExpression expression = EngineEventUtils.computeAttributeExpression(context, tag, attributeName, attributeValue);

                if (expression != null && expression instanceof FragmentExpression) {
                    // This is merely a FragmentExpression (not complex, not combined with anything), so we can apply a shortcut
                    // so that we don't require a "null" result for this expression if the template does not exist. That will
                    // save a call to resource.exists() which might be costly.

                    final FragmentExpression.ExecutedFragmentExpression executedFragmentExpression =
                            FragmentExpression.createExecutedFragmentExpression(context, (FragmentExpression) expression, StandardExpressionExecutionContext.NORMAL);

                    expressionResult =
                            FragmentExpression.resolveExecutedFragmentExpression(context, executedFragmentExpression, true);

                } else {
                    assert expression != null;
                    expressionResult = expression.execute(context);
                }
                targetLink = expressionResult.toString();
            }

        } catch (Exception ignored) {
        }

        targetLink = HtmlEscape.escapeHtml4Xml(targetLink);
        try {
            targetLink = fawziPathService.getPath()+targetLink;
            StandardProcessorUtils.replaceAttribute(
                    structureHandler, attributeName, this.targetAttributeDefinition, this.attributeName
                    , targetLink);
        } catch (Exception e) {
            throw new IllegalStateException("please use this tag in EWP pages.");
        }

    }

}
