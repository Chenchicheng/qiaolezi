package com.smart.store.model.response;

import lombok.Data;

import java.util.List;

@Data
public class TaskListResponse {

    private List<TaskResponse> taskResponseList;

    private int page;

    private int total;

    private int count;
}
