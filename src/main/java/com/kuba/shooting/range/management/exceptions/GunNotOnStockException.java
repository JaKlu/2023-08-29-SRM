package com.kuba.shooting.range.management.exceptions;

public class GunNotOnStockException extends RuntimeException {
    public GunNotOnStockException(String message) {
        super(message);
    }
}
