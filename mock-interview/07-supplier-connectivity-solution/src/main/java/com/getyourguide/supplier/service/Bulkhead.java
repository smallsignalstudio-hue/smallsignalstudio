package com.getyourguide.supplier.service;

import java.util.concurrent.Semaphore;

public final class Bulkhead {
    private final Semaphore permits;

    public Bulkhead(int maxInFlight) {
        this.permits = new Semaphore(maxInFlight);
    }

    public boolean tryAcquire() {
        return permits.tryAcquire();
    }

    public void release() {
        permits.release();
    }
}
