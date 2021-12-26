package com.company;

public class PointMovingTask implements Runnable {
    private final Point point;

    public PointMovingTask(Point point) {
        this.point = point;
    }

    @Override
    public void run() {
        point.move(1, 1);
    }
}
