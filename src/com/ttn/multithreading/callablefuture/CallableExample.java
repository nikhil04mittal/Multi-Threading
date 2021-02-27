package com.ttn.multithreading.callablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Calling Method with callable anonymous class call");
                Thread.sleep(5000);
                return 5;
            }
        });

        executorService.submit(() -> {
            System.out.println("Calling Method with lambda call");
            Thread.sleep(5000);
            return 5;
        });
    }
}
