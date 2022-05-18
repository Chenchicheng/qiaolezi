package com.smart.store.exception;

public class ScoreNotEnoughException extends RuntimeException{

    public ScoreNotEnoughException() {

    }

    public ScoreNotEnoughException(String s) {
        super(s);
    }

    public ScoreNotEnoughException(String s, Throwable e) {
        super(s, e);
    }

    public ScoreNotEnoughException(Throwable e) {
        super(e);
    }
}
