package cn.fawzi.thymeleaf.service;

import cn.fawzi.thymeleaf.entity.Foods;

import java.util.List;

/**
 * @author wenqi
 */
public interface FoodsService {

    Foods findById(Integer id);

    List<Foods> createFoods();

}
