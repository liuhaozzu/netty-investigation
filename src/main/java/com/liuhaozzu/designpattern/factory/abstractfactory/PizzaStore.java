package com.liuhaozzu.designpattern.factory.abstractfactory;

import com.liuhaozzu.designpattern.factory.simplefactory.Pizza;

/**
 * @Author Administrator
 * @create 2019/5/4 0004 16:42
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 实例化的责任被移到一个 方法 中，此方法就如同是一个工厂
     * 工厂方法用来处理对象的创建，并将这样的行为封装在子类中。这样，客户端程序中关于超类的代码，就和子类对象创建代码解耦了
     * @param type
     * @return
     */
    protected abstract Pizza createPizza(String type);
}
