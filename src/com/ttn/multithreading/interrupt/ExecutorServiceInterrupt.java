package com.ttn.multithreading.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorServiceInterrupt {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 50; i++) {
            executorService.submit(() -> {
                int i1 =0;
                while (i1 < 100 && !Thread.currentThread().isInterrupted()) {
                    System.out.println("Task Number: " + atomicInteger.get());
                    System.out.println("Execution Number: " + ++i1);
                }
                atomicInteger.getAndIncrement();
            });
        }
        executorService.shutdown();
        executorService.shutdownNow();


    }
}
