package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        new Main().showPointWithoutSyncTaskResult();
        new Main().showAtomicPointTaskResult();
        new Main().finishAllTheTasks();
        new Main().getCollectionOfSingletonObjects();
  //      new Main().getOneSingletonObject();
    }

    public void getCollectionOfSingletonObjects() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newCachedThreadPool();
        Collection<Future<SingletonWorker>> singletonWorkers = new ArrayList<>();
        Future<SingletonWorker> singletonWorkerFuture;

        for (int i = 0; i < 1000; i++) {
            singletonWorkerFuture = executor.submit(new SingletonWorkerTask(countDownLatch));
            singletonWorkers.add(singletonWorkerFuture);
        }

        countDownLatch.countDown();

        for (Future<SingletonWorker> singletonWorker : singletonWorkers) {
            try {
                System.out.println(singletonWorker.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getOneSingletonObject() {
        System.out.println();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executor = Executors.newCachedThreadPool();
        Collection<Future<SingletonWorker>> singletonWorkers = new ArrayList<>();
        Future<SingletonWorker> singletonWorkerFuture;

        for (int i = 0; i < 500; i++) {
            singletonWorkerFuture = executor.submit(new SingletonWorkerSyncTask(countDownLatch));
            singletonWorkers.add(singletonWorkerFuture);
        }
        countDownLatch.countDown();

        for (Future<SingletonWorker> singletonWorker : singletonWorkers) {
            try {
                System.out.println(singletonWorker.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void finishAllTheTasks() {
        CountDownLatch countDownLatch = new CountDownLatch(2000);
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 2000; i++) {
            executor.submit(new Worker(countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showAtomicPointTaskResult() {
        VolatileFieldPoint volatilePoint = new VolatileFieldPoint(0, 0);
        ExecutorService executor = Executors.newCachedThreadPool();
        Collection<Future<?>> results = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            Future<?> result = executor.submit(new PointAtomicMovingTask(volatilePoint));
            results.add(result);
        }

        for (Future<?> result : results) {
            try {
                result.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        }
        System.out.printf("Volatile point, x and y value: %d & %d%n",
                                volatilePoint.atomicIntegerX.get(), volatilePoint.atomicIntegerY.get());
    }

    public void showPointWithoutSyncTaskResult() {
        Point point = new Point(0 , 0);
        ExecutorService executor = Executors.newCachedThreadPool();
        Collection<Future<?>> results = new ArrayList<>();

        for (int i = 0; i < 2000; i++) {
            Future<?> result = executor.submit(new PointMovingTask(point));
            results.add(result);
        }

        for (Future<?> result : results) {
            try {
                result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("Point without sync, x and y value: %d & %d%n", point.x, point.y);
    }
}
