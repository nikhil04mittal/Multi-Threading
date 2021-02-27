package com.ttn.multithreading;

public class StaticMethodSynchronized {
    private static Integer integer = 0;

    private synchronized static void increment() {
        integer++;
    }

    private synchronized static void decrement() {
        integer--;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                decrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final Value of Integer: " + integer);
    }
}