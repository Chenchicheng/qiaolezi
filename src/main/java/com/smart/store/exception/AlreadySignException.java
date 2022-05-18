package com.smart.store.exception;

public class AlreadySignException extends RuntimeException{
    public AlreadySignException() {

    }

    public AlreadySignException(String s) {
        super(s);
    }

    public AlreadySignException(String s, Throwable e) {
        super(s, e);
    }

    public AlreadySignException(Throwable e) {
        super(e);
    }
}
