package com.ttn.multithreading;

public class BlockSynchronized {
    private Integer integer = 10;
    private void increment() {
        this.integer++;
    }
    private void decrement() {
        this.integer--;
    }

    public static void main(String[] args) throws InterruptedException {
        BlockSynchronized instanceMethodSynchronized = new BlockSynchronized();
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                synchronized(lock) {
                    instanceMethodSynchronized.increment();
                }
            }
        }, "IncrementThread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                synchronized (lock) {
                    instanceMethodSynchronized.decrement();
                }
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

