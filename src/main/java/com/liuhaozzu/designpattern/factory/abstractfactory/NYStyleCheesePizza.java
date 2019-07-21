package com.liuhaozzu.designpattern.factory.abstractfactory;

import com.liuhaozzu.designpattern.factory.simplefactory.Pizza;

/**
 * @Author Administrator
 * @create 2019/5/4 0004 16:57
 */
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
    }

    @Override
    public

    void bake() {
        super.bake();
    }
}
