package com.liuhaozzu.limit.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.checkerframework.common.value.qual.IntRange;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

/**
 * @author: liuhaozzu
 * @date: 2019-07-04 16:44
 */
public class RateLimiterDemo {


    public static void main(String[] args) {
        //new RateLimiterDemo().testThreadSleep();
        RateLimiterDemo demo = new RateLimiterDemo();
        System.out.println(demo.getClass());
        System.out.println(demo.getClass().getClass());
        System.out.println(demo.getClass().getClass().getClass());

        System.out.println(30%15);

        System.out.println(Long.MIN_VALUE);
        System.out.println(Math.abs(Long.MIN_VALUE));
    }

    public void testAcquire() {
        RateLimiter limiter = RateLimiter.create(0.2);
        IntStream.range(1,100).forEach(i->{
            double waitTime=limiter.acquire(i);
            System.out.println(waitTime);
            System.out.println("cutTime=" + System.currentTimeMillis() + " acq:" + i + " waitTime:" + waitTime);
        });
    }

    @Test
    public void testThreadSleep() {
        Thread thread = new Thread(() -> {
            System.out.println("start to running");
            System.out.println("thread state:" + Thread.currentThread().getState());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("in thread after sleep state:" + Thread.currentThread().getState());

        });

        System.out.println("before start:" + thread.getState());
        thread.start();
        System.out.println("after start:" + thread.getState());


        try {
            Thread.sleep(100);
            System.out.println("state:"+thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (thread) {
            System.out.println("thread as a monitor");
            System.out.println("thread as monitor");
        }

        synchronized (this) {
            System.out.println("thread as a monitor");
            System.out.println("thread as monitor");
        }
        synchronized (thread.getClass()) {
            System.out.println("thread as a monitor");
            System.out.println("thread as monitor");
        }

        System.out.println("holdsLock:"+Thread.holdsLock(thread));
        System.out.println("holdsLock:"+Thread.holdsLock(thread.getClass()));
        System.out.println("holdsLock:"+Thread.holdsLock(this));


    }



}
