package com.smart.store.exception;

public class RewardNotFoundException extends RuntimeException{
    public RewardNotFoundException() {

    }

    public RewardNotFoundException(String s) {
        super(s);
    }

    public RewardNotFoundException(String s, Throwable e) {
        super(s, e);
    }

    public RewardNotFoundException(Throwable e) {
        super(e);
    }
}
