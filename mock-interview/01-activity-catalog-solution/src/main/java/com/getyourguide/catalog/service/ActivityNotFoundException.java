package com.getyourguide.catalog.service;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(long id) {
        super("Activity " + id + " does not exist");
    }
}
