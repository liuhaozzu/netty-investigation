package com.liuhaozzu.designpattern.observer;

import java.util.Observable;

/**
 * @Author Administrator
 * @create 2019/4/27 0027 22:35
 */
public interface Observer {
    /**
     * <b>第一阶段</b>：直接把观察值传递给观察者：对扩展不友好
     * @param temp
     * @param humidity
     * @param presure
     */
    void update(float temp, float humidity, float presure);

    void update();

    //java.util.Observer observer();
    //Observable
}
