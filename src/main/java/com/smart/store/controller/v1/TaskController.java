package com.smart.store.controller.v1;

import com.smart.store.model.entity.TaskEntity;
import com.smart.store.model.request.TaskRequest;
import com.smart.store.model.response.TaskListResponse;
import com.smart.store.service.TaskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/task")
@Api(value = "Task_Op")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{page}/{count}")
    public TaskListResponse getTaskList(@PathVariable("page") int page, @PathVariable("count") int count, String type) {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setType(type);
        taskRequest.setPage(page);
        taskRequest.setCount(count);
        return taskService.getTaskList(taskRequest);
    }

    @PostMapping("/complete/{userId}/{taskId}")
    public void completeTask(@PathVariable("userId") String userId, @PathVariable("taskId") String taskId) {
        Assert.notNull(userId, "userId cannot be null");
        Assert.notNull(taskId, "taskId cannot be null");
        taskService.completeTask(taskId, userId);
    }

    @PostMapping("/add")
    public void addTask(TaskEntity taskEntity) {
        taskService.add(taskEntity);
    }
}
