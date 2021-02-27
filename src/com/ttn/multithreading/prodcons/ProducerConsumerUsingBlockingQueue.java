package com.ttn.multithreading.prodcons;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
    static BlockingQueue<Integer> store = new LinkedBlockingQueue<>(10);
    static class Producer extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (i< 50) {
                System.out.println("Producer Producing Value");
                try {
                    int bound = 100;
                    store.put(new Random().nextInt(bound));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Size after producing: " + store.size());
                i++;
            }
        }
    }

    static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i< 50) {
                System.out.println("Size of Store: " + store.size());
                try {
                    store.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer consumed value");
                i++;
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



