package com.ttn.multithreading;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithWaitAndNotifyExample {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ReentrantLockWithWaitAndNotifyExample example = new ReentrantLockWithWaitAndNotifyExample();
        new Thread(() -> {
            example.lock.lock();
            System.out.println("Acquired Lock Thread 1");
            try {
                Thread.sleep(5000);
                example.condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread running back");
            example.lock.unlock();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            example.lock.lock();
            System.out.println("Acquired Lock Thread 2");
            example.condition.signal();
            System.out.println("Thread finishing work");
            example.lock.unlock();
        }).start();
    }
}