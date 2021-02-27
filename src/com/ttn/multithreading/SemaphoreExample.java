package com.ttn.multithreading;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    Semaphore semaphore = new Semaphore(10);
    public static void main(String[] args) {
        SemaphoreExample semaphoreExample = new SemaphoreExample();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    semaphoreExample.semaphore.acquire();
                    System.out.println("Acquired semaphore");
                    System.out.println("Size of semaphore after acquire: " + semaphoreExample.semaphore.availablePermits());
                    Thread.sleep(5000);
                    semaphoreExample.semaphore.release();
                    System.out.println("Released semaphore");
                    System.out.println("Size of semaphore after release: " + semaphoreExample.semaphore.availablePermits());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
