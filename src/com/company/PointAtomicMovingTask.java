package com.company;

public class PointAtomicMovingTask implements Runnable {
    private final VolatileFieldPoint point;

    public PointAtomicMovingTask(VolatileFieldPoint point) {
        this.point = point;
    }

    @Override
    public void run() {
        point.atomicMove();
    }
}
