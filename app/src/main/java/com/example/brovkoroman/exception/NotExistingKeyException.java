package com.example.brovkoroman.exception;

/**
 * Created by brovkoroman on 28.08.17.
 */

public class NotExistingKeyException extends Exception {
    public NotExistingKeyException(String pDetailMessage) {
        super(pDetailMessage);
    }
}
