package com.ttn.multithreading.interrupt;

public class ThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread Running");
            }
        });
        t1.start();
        Thread.sleep(5000);
        System.out.println("Interrupting thread");
        t1.interrupt();
    }
}
