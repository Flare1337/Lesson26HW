package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileFieldPoint {
    public volatile int x;
    public volatile int y;
    public AtomicInteger atomicIntegerX = new AtomicInteger(x);
    public AtomicInteger atomicIntegerY = new AtomicInteger(y);

    public VolatileFieldPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void atomicMove() {
        atomicIntegerX.incrementAndGet();
        atomicIntegerY.incrementAndGet();
    }
}
