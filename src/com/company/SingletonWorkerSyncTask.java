package com.company;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class SingletonWorkerSyncTask implements Callable<SingletonWorker> {
    CountDownLatch countDownLatch;

    public SingletonWorkerSyncTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public SingletonWorker call() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return SingletonWorker.getSingletonWorkerSync(countDownLatch);
    }
}
