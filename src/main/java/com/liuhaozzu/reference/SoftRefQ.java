package com.liuhaozzu.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftRefQ {

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "hutaishi");
        softQueue = new ReferenceQueue<>();
        // 当对象的可达性变为不可达的时候，软引用对象会进入引用队列，通过这个引用队列，可以跟踪对象的回收情况
        // 在这里就是UserSoftReference 这个软引用对象对进入softQueue队列中
        UserSoftReference userSoftRef = new UserSoftReference(u, softQueue);
        u = null;  //此时对象没有强引用了
        System.out.println(userSoftRef.get());
        System.gc();
        System.out.println("after gc");
        System.out.println(userSoftRef.get());
        System.out.println("-------------------------------------");
        byte[] b = new byte[1024 * 960 *7 ];  // -Xmx10M -Xms10M
        // 注意分配的最大堆为10M， 在上一步中，申请内存的时候，发现堆内存不足，而User 对象只有软引用了，所以就回收U对象
        //对象已被回收，无法通过软引用获取对象
        System.out.println(userSoftRef.get());
    }

    private static ReferenceQueue<User> softQueue = null;
    public static class CheckRefQueue extends Thread {

        @Override
        public void run() {
            while (true) if (softQueue != null) {
                UserSoftReference obj = null;
                try {
                    Reference<? extends User> remove = softQueue.remove();
                    obj = (UserSoftReference) remove;

                    if (obj != null) {
                        System.out.println("user id = " + obj.uid + " is delete");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {
        private int uid;
        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            this.uid = referent.id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }

    public static class User {
        private int id;
        private String name;
        private String[] friends = new String[1000];  // 占用一定量的内存空间

        public User() {
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
            for (int i = 0; i < 1000; i++) {
                friends[i] = "hello world";
            }
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}