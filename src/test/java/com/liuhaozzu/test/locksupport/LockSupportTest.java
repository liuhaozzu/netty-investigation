package com.liuhaozzu.test.locksupport;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: liuhaozzu
 * @date: 2019-03-15 21:19
 */
public class LockSupportTest {
    public static void main(String[] args) {

        TestThread thread = new TestThread(Thread.currentThread());
        System.out.println("1"+thread.getState());
        thread.start();
        System.out.println("2"+thread.getState());
        new Thread(()->{
            try {
                System.out.println("3"+thread.getState());
                Thread.sleep(300);
                System.out.println("4"+thread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("before park");
        Thread thread1 = Thread.currentThread();
        new Thread(()->{
            try {
                Thread.sleep(300);

                System.out.println("thread1" + thread1.getState() + " name:" + thread1.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        LockSupport.park("Park");
        System.out.println("after park");

    }

    @Test
    public void threadInterruptedTest() {
        AtomicInteger count = new AtomicInteger(0);
        Thread thread = new Thread(()->{
            System.out.println(System.currentTimeMillis());
            System.out.println("now");
            for (; ; ) {
                /*try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("now:" + System.currentTimeMillis());
                System.out.println("in thread: state》》》》》》》》》》》》》》》》》》"+Thread.currentThread().isInterrupted());
                System.out.flush();
                boolean flag = Thread.interrupted();
                System.out.println("flag+" + count.getAndIncrement() + " :::" + flag);
                System.out.println("in thread: state>>>>>>>>>>>>>>> "+Thread.currentThread().isInterrupted());



            }
        });
        thread.start();
        System.out.println("interrupt status after start:"+thread.isInterrupted());
        thread.interrupt();
        System.out.println("interrupt status after thread.interrupt():"+thread.isInterrupted());
        System.out.println("interrupt status after thread.interrupt():"+thread.isInterrupted());
        thread.interrupted();
        System.out.println("interrupt status after thread.interrupt():"+thread.isInterrupted());
    }

    @Test
    public void scheduledFutureTaskTest() {
      //  ScheduledFutureTask task = new ScheduledFutureT
    }

}
