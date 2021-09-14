package com.ttn.multithreading;

public class InstanceMethodSynchronized {
    private Integer integer = 10;
    private synchronized void increment() {
        this.integer++;
    }
    private synchronized void decrement() {
        this.integer--;
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceMethodSynchronized instanceMethodSynchronized = new InstanceMethodSynchronized();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                instanceMethodSynchronized.increment();
            }
        }, "IncrementThread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                instanceMethodSynchronized.decrement();
            }
        }, "DecrementThread");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Thread.sleep(10000);
        System.out.println(instanceMethodSynchronized.integer);
    }
}

