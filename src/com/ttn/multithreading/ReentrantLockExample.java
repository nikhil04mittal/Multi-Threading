package com.ttn.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    private static Integer integer = 0;
    private static Integer integer2 = 0;
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private static Object lock = new Object();

    private static void increment() {
        lock1.lock();
        integer++;
        lock1.unlock();
    }

    private static void decrement() {
        lock2.lock();
        integer2--;
        lock2.unlock();
    }

    /**
     * Just to show clean code
     */
    /*private static void test() {
        Integer a;
        synchronized (lock) {
            a = 10;
            a = a +1;
        }
        System.out.println("Value of a is: " + a);
    }

    private static void test2() {
        lock2.lock();
        Integer a  = 10;
        a = a + 1;
        lock2.unlock();
        System.out.println("Value of a is: " + a);
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                increment();
            }
        }, "Increment1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                decrement();
            }
        }, "Decrement1");
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                decrement();
            }
        }, "Decrement2");
        Thread t4 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                increment();
            }
        }, "Increment2");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Final Value of Integer: " + integer);
        System.out.println("Final Value of Integer: " + integer2);
    }
}