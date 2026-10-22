package com.getyourguide.supplier.service;
public class SupplierTimeout extends RuntimeException {
    public SupplierTimeout() { super("supplier timed out"); }
}
