package cn.fawzi.thymeleaf.formatter;

import cn.fawzi.thymeleaf.entity.Foods;
import cn.fawzi.thymeleaf.service.FoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * @author wenqi
 */
public class FoodsFormatter implements Formatter<Foods> {

    @Autowired
    private FoodsService foodsService;

    @Override
    public Foods parse(String s, Locale locale) throws ParseException {
        Integer fid=Integer.valueOf(s);
        return foodsService.findById(fid);
    }

    @Override
    public String print(Foods foods, Locale locale) {
        return foods!=null?foods.getFId().toString():"";
    }
}
