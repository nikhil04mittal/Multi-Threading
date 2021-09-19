package com.ttn.multithreading.interrupt;

public class ThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName()+": Thread Running");
            }
        }, "Thread 1");
        Thread t2 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName()+": Thread Running");
            }
        }, "Thread 2");
        t1.start();
        t2.start();
        Thread.sleep(5000);
        System.out.println("Interrupting thread");
        t1.interrupt();
        t2.interrupt();
    }
}
