package com.liuhaozzu.test.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future future = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return "hello future";
            }
        });


        Thread a = new Thread();
        a.join();
        //Executors.callable(null);
        String result = (String) future.get();

    }
}
