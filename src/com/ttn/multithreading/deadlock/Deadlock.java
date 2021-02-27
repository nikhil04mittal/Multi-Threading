package com.ttn.multithreading.deadlock;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        Deadlock deadlock = new Deadlock();
        Account account1 = new Account("12345", 10000);
        Account account2 = new Account("123456", 20000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                deadlock.transfer(account1, account2, 100);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                deadlock.transfer(account2, account1, 100);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Total balance for both account: " + account1.getAmount() + account2.getAmount());
    }

    private void transfer(Account fromAccount, Account toAccount, Integer amount) {
        synchronized (fromAccount.getLock()) {
            synchronized (toAccount.getLock()) {
                System.out.println("Transferring amount: " + amount + " from account: " + fromAccount.getAccountNumber() + " to account: " + toAccount.getAccountNumber());
                toAccount.setAmount(toAccount.getAmount() - amount);
                fromAccount.setAmount(fromAccount.getAmount() + amount);
            }
        }
    }
}

class Account {
    private String accountNumber;
    private Integer amount;
    private Object lock;

    public Account(String accountNumber, Integer amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.lock = new Object();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Object getLock() {
        return lock;
    }
}