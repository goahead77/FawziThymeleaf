package cn.fawzi.thymeleaf.service;

import cn.fawzi.thymeleaf.entity.Foods;

/**
 * @author wenqi
 */
public interface FoodsService {

    Foods findById(Integer id);

}
