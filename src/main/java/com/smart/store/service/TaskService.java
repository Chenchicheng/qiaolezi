package com.smart.store.service;

import com.smart.store.model.entity.TaskEntity;
import com.smart.store.model.request.TaskRequest;
import com.smart.store.model.response.TaskListResponse;

public interface TaskService {

    TaskListResponse getTaskList(TaskRequest taskRequest);

    void completeTask(String taskId, String userId);

    void add(TaskEntity taskEntity);
}
