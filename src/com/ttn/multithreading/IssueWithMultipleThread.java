package com.ttn.multithreading;

public class IssueWithMultipleThread {
    private Integer integer = 10;
    private void increment() {
        this.integer++;
    }
    private void decrement() {
        this.integer--;
    }

    public static void main(String[] args) throws InterruptedException {
        IssueWithMultipleThread issueWithMultipleThread = new IssueWithMultipleThread();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                issueWithMultipleThread.increment();
            }
        }, "IncrementThread");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20000; i++) {
                issueWithMultipleThread.decrement();
            }
        }, "DecrementThread");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Thread.sleep(10000);
        System.out.println(issueWithMultipleThread.integer);
    }
}

