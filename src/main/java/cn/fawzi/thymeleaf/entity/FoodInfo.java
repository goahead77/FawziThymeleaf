package cn.fawzi.thymeleaf.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wenqi
 */

@Setter
@Getter
public class FoodInfo {

    /**
     * 价格
     */
    private double price;

    /**
     * 数量
     */
    private int number;

    public FoodInfo(double price,int number) {
        this.price=price;
        this.number=number;
    }
}
