package com.ttn.multithreading;

public class CreateThread {
    public static void main(String[] args) {
        //Create Thread using Thread Class
        new ThreadClass().start();

        //Create Thread using Runnable
        new Thread(new ThreadClass2()).start();

        //Create Thread anonymous inner class
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("here");
            }
        }).start();

        //Create Thread lambda
        new Thread(() -> System.out.println("here")).start();
    }
}

class ThreadClass extends Thread {
    @Override
    public void run() {
        System.out.println("Here");
    }
}

class ThreadClass2 implements Runnable {

    @Override
    public void run() {
        System.out.println("Here");
    }
}
