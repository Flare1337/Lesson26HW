package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();
        new Thread(deadlock::executeOperation1, "T1").start();
        new Thread(deadlock::executeOperation2, "T2").start();
    }

    public void executeOperation1() {
        lock1.lock();
        System.out.println("lock1 acquired, waiting to acquire lock2.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock2.lock();
        System.out.println("lock2 acquired");

        System.out.println("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void executeOperation2() {
        lock2.lock();
        System.out.println("lock2 acquired, waiting to acquire lock1.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock1.lock();
        System.out.println("lock1 acquired");

        System.out.println("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }
}
