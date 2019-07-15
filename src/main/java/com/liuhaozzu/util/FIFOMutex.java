package com.liuhaozzu.util;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: liuhaozzu
 * @date: 2019-03-15 18:42
 */
public class FIFOMutex {
    private AtomicBoolean locked = new AtomicBoolean(false);
    private Queue<Thread> waiters = new ConcurrentLinkedDeque<>();

    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        //block if not the first in queue or can't acquire the lock
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            //Thread is in waiting state---block current thread
            LockSupport.park();
            if (current.isInterrupted()) {
                wasInterrupted = true;
            }
        }
        waiters.remove(current);
        if (wasInterrupted) {
            current.interrupt(); // reassert interrupt status on exit
        }
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
