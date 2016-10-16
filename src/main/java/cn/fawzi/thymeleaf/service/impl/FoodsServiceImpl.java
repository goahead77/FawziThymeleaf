package cn.fawzi.thymeleaf.service.impl;

import cn.fawzi.thymeleaf.entity.Foods;
import cn.fawzi.thymeleaf.service.FoodsService;
import org.springframework.stereotype.Service;

/**
 * @author wenqi
 */

@Service
public class FoodsServiceImpl implements FoodsService {

    @Override
    public Foods findById(Integer id) {
        Foods foods=new Foods();
        foods.setFId(id);
        foods.setFName("水果"+id+"号");
        return foods;
    }
}
