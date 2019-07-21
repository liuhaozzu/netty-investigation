package com.liuhaozzu.designpattern.state;

/**
 * @Author Administrator
 * @create 2019/5/12 0012 10:42
 */
public interface State {
    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
