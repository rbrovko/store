package com.example.brovkoroman.exception;

/**
 * Created by brovkoroman on 28.08.17.
 */

public class StoreFullException extends RuntimeException {
    public StoreFullException(String pDetailMessage) {
        super(pDetailMessage);
    }
}
