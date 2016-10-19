package cn.fawzi.thymeleaf.service.impl;

import cn.fawzi.thymeleaf.entity.Foods;
import cn.fawzi.thymeleaf.service.FoodsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        if(id%2==0)
            foods.setFry(true);
        else
            foods.setFry(false);
        foods.setProductTime(new Date());
        return foods;
    }
    @Override
    public List<Foods> createFoods() {
        List<Foods> foodses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Foods f = new Foods();
            f.setFId(i);
            f.setFName("食物" + i);
            if (i % 2 == 0)
                f.setFry(true);
            else
                f.setFry(false);
            f.setProductTime(new Date());
            foodses.add(f);
        }
        return foodses;
    }
}
