package com.getyourguide.webhooks.service;

public class SignatureException extends RuntimeException {
    public SignatureException() {
        super("invalid signature");
    }
}
