package com.ttn.multithreading.callablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            Thread.sleep(5000);
            return 5;
        });
        System.out.println("Main Thread Running");
        executorService.shutdown();

        while (!future.isDone()) {
            System.out.println("Main Thread waiting for the response");
            Thread.sleep(1000);
        }
        System.out.println("Main Thread got the response");
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Get with timeout
        try {
            future.get(5, TimeUnit.MINUTES);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
