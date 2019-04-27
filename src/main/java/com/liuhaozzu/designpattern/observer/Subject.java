package com.liuhaozzu.designpattern.observer;


/**
 * @Author Administrator
 * @create 2019/4/27 0027 22:33
 */
public interface Subject {
    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
