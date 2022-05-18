package com.smart.store.model.response;

import lombok.Data;

@Data
public class TaskResponse {

    private String taskId;

    private String taskName;

    private long taskScore;

    private String taskType;

    private int left;

    private String pictureId;

    private String emoji;

    private int status;
}
