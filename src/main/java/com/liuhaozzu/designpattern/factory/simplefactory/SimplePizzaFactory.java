package com.liuhaozzu.designpattern.factory.simplefactory;

/**
 * @Author Administrator
 * @create 2019/5/4 0004 16:28
 */
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if ("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if ("pepperoni".equals(type)) {
            pizza = new PepperoniPizza();
        }
        return pizza;
    }
}
