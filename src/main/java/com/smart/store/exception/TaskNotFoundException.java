package com.smart.store.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException() {

    }

    public TaskNotFoundException(String s) {
        super(s);
    }

    public TaskNotFoundException(String s, Throwable e) {
        super(s, e);
    }

    public TaskNotFoundException(Throwable e) {
        super(e);
    }
}
