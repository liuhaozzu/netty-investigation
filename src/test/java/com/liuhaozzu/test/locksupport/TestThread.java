package com.liuhaozzu.test.locksupport;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: liuhaozzu
 * @date: 2019-03-15 21:20
 */
public class TestThread extends Thread {

    private Thread object;

    public TestThread() {

    }

    public TestThread(Thread object) {
        this.object = object;
        System.out.println(object.getState() + " name:" + object.getName());
    }

    @Override
    public void run() {
        System.out.println("before unpark");

        //休眠，保证setBlocker
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Blocker info:" + LockSupport.getBlocker((Thread) object));
        LockSupport.unpark((Thread) object);
        //休眠500ms，保证执行setBlocker

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Blocker info:" + LockSupport.getBlocker((Thread) object));
        System.out.println("after unpark");

    }

}
