package com.smart.store.model.request;

import lombok.Data;

@Data
public class TaskRequest {

    private int page;

    private int count;

    private String type;
}
