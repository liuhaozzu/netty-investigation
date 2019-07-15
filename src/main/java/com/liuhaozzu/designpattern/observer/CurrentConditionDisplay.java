package com.liuhaozzu.designpattern.observer;

/**
 * @Author Administrator
 * @create 2019/4/27 0027 22:42
 */
public class CurrentConditionDisplay implements Observer, DisplayElement {
    private Subject subject;

    public CurrentConditionDisplay(Subject subject) {
        //最好维护一个Subject的引用，注销时，比较方便
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void display() {

    }

    @Override
    public void update(float temp, float humidity, float presure) {

    }

    @Override
    public void update() {

    }
}
