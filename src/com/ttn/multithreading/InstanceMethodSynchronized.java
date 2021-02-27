package com.ttn.multithreading;

public class InstanceMethodSynchronized {
    private Integer integer = 10;
    private synchronized void increment() {
        System.out.println(Thread.currentThread().getName() + " is locking.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.integer++;
        System.out.println(Thread.currentThread().getName() + " is unlocking.");
    }
    private synchronized void decrement() {
        System.out.println(Thread.currentThread().getName() + " is locking.");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.integer--;
        System.out.println(Thread.currentThread().getName() + " is unlocking.");
    }

    private void print() {
        System.out.println(Thread.currentThread().getName() + " is printing.");
        System.out.println(this.integer);
        System.out.println(Thread.currentThread().getName() + " is printed.");
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceMethodSynchronized instanceMethodSynchronized = new InstanceMethodSynchronized();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                instanceMethodSynchronized.print();
                instanceMethodSynchronized.increment();
            }
        }, "IncrementThread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                instanceMethodSynchronized.print();
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

