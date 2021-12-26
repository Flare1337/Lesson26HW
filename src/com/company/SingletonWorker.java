package com.company;
import java.util.concurrent.CountDownLatch;

public class SingletonWorker implements Runnable {
    public CountDownLatch countDownLatch;
    private static SingletonWorker singletonWorker;

    private SingletonWorker(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public static synchronized SingletonWorker getSingletonWorker(CountDownLatch countDownLatch) {
        if (singletonWorker == null) {
            singletonWorker = new SingletonWorker(countDownLatch);
        }
        return singletonWorker;
    }

    public static SingletonWorker getSingletonWorkerSync(CountDownLatch countDownLatch) {
        if (singletonWorker == null) {
            singletonWorker = new SingletonWorker(countDownLatch);
        }
        return singletonWorker;
    }

    @Override
    public void run() {
        countDownLatch.countDown();
    }
}
