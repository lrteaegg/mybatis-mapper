package com.felton.mybatismapper.util;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {

    static CountDownLatch latch = new CountDownLatch(6);

    private static class InitThread implements Runnable {

        @Override
        public void run() {
            System.out.println("InitThread_" + Thread.currentThread().getId()
                    + " ready init work.....");
            latch.countDown();
            System.out.println("InitThread_" + Thread.currentThread().getId()
                    + "countinue do its work...");
        }
    }

    // 业务线程
    private static class BusiThread implements Runnable {

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BusiThread_" + Thread.currentThread().getId()
                    + "do business-----");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + " ready init work step 1st...");
                latch.countDown();
                System.out.println("begin step 2nd...");
//                SleepTools.ms(1);
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + "ready init work step 3rd");
                latch.countDown();
            }
        }).start();

// 业务处理
        new Thread(new BusiThread()).start();

        for (int i = 0; i <= 3; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
        latch.await();
        System.out.println("Main do ites work.....");
    }

}