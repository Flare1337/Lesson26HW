package com.company;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {
    CountDownLatch isDoneSignal;

    public Worker(CountDownLatch isDoneSignal) {
        this.isDoneSignal = isDoneSignal;
    }

    @Override
    public void run() {
        isDoneSignal.countDown();
    }
}
