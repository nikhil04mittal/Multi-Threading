package com.ttn.multithreading.prodcons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProducerConsumerUsingWaitNotify {
    static List<Integer> store = new ArrayList<>();
    static Object lock = new Object();
    static class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if (store.size() == 10) {
                        try {
                            System.out.println("Producer Waiting");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    store.add(new Random().nextInt(100));
                    System.out.println("Size of Store: " + store.size()+ " Producer produced");
                    lock.notify();
                }
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    if (store.size() == 0) {
                        try {
                            System.out.println("Consumer Waiting");
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Size of Store: " + store.size() + " Consumer consuming");
                    store.remove(0);
                    lock.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }
}



