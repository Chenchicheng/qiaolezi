package com.smart.store.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Setter
@Getter
public class Result<T> implements Serializable {

    private String code;

    private String msg;

    private T data;
}
