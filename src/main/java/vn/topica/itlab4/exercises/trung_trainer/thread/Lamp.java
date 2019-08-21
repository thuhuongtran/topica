package vn.topica.itlab4.exercises.trung_trainer.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Lamp {
    private String status;
    private static final AtomicInteger index = new AtomicInteger();
    private int count;

    protected Lamp(String status) {
        this.status = status;
        count = index.getAndIncrement();
    }

    protected Lamp() {
        index.getAndIncrement();
    }

    protected enum Status{
        ON,
        OFF,
        REPAIR
    }

    public void setStatus(String status) {
        this.status = status;
    }

    protected String getStatus() {
        return status;
    }

    protected int getIndex() {
        return count;
    }

    @Override
    public String toString() {
        return (new StringBuilder())
                .append(String.format("Index: %d", getIndex()))
                .append(String.format(" ,Status: %s", getStatus()))
                .toString();
    }
}
