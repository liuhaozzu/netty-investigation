package com.liuhaozzu.designpattern.factory.simplefactory;

import java.util.ArrayList;

/**
 * @Author Administrator
 * @create 2019/5/4 0004 16:29
 */
public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected ArrayList toppings = new ArrayList();
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings");
        toppings.forEach(top->{
            System.out.println("   " + top);
        });
    }

    public void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in offical PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
