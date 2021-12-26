package com.company;
import java.util.concurrent.CountDownLatch;

public class SingletonWorker {
    public CountDownLatch countDownLatch;
    private static SingletonWorker singletonWorker;
    public static int counterOfCreatedSingletons = 0;

    private SingletonWorker(CountDownLatch countDownLatch) {
        ++counterOfCreatedSingletons;
        this.countDownLatch = countDownLatch;
    }

    public static SingletonWorker getSingletonWorker(CountDownLatch countDownLatch) {
        if (singletonWorker == null) {
            singletonWorker = new SingletonWorker(countDownLatch);
        }
        return singletonWorker;
    }

    public static synchronized SingletonWorker getSingletonWorkerSync(CountDownLatch countDownLatch) {
        if (singletonWorker == null) {
            singletonWorker = new SingletonWorker(countDownLatch);
        }
        return singletonWorker;
    }
}
