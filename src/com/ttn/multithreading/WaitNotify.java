package com.ttn.multithreading;

public class WaitNotify {
    private Object lock = new Object();

    public static void main(String[] args) {
        WaitNotify thread2 = new WaitNotify();
        new Thread(() -> {
            try {
                synchronized (thread2.lock) {
                    System.out.println("Acquired Lock Thread 1");
                    Thread.sleep(5000);
                    thread2.lock.wait();
                    System.out.println("Thread running back");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
                synchronized (thread2.lock) {
                    System.out.println("Acquired Lock Thread 2");
                    thread2.lock.notify();
                    System.out.println("Thread finishing work");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

