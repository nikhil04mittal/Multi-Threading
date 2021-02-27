package com.ttn.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

    CountDownLatch latch = new CountDownLatch(50);
    public static void main(String[] args) {
        CountDownLatchExample countDownLatchExample = new CountDownLatchExample();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    System.out.println("CountDown Latch count before countDown is" + countDownLatchExample.latch.getCount());
                    Thread.sleep(5000);
                    countDownLatchExample.latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            countDownLatchExample.latch.await();
            System.out.println("50 Threads completed their work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
