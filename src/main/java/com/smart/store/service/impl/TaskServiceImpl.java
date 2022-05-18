package com.smart.store.service.impl;

import com.smart.store.exception.TaskNotFoundException;
import com.smart.store.model.entity.ScoreEntity;
import com.smart.store.model.entity.TaskEntity;
import com.smart.store.model.entity.UserTaskAsst;
import com.smart.store.model.request.TaskRequest;
import com.smart.store.model.response.TaskListResponse;
import com.smart.store.model.response.TaskResponse;
import com.smart.store.repository.ScoreRepository;
import com.smart.store.repository.TaskRepository;
import com.smart.store.repository.UserTaskAsstRepository;
import com.smart.store.service.ScoreService;
import com.smart.store.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserTaskAsstRepository taskAsstRepository;

    @Autowired
    ScoreService scoreService;

    @Autowired
    UserTaskAsstRepository userTaskAsstRepository;

    @Override
    public TaskListResponse getTaskList(TaskRequest taskRequest) {
        List<TaskResponse> taskResponseList = new ArrayList<>();
        Page<TaskEntity> taskEntityPage = taskRepository.findAll(PageRequest.of(taskRequest.getPage() - 1, taskRequest.getCount()));
        if (!taskEntityPage.isEmpty()) {
            List<TaskEntity> taskEntityList = taskEntityPage.getContent();
            for (TaskEntity taskEntity : taskEntityList) {
                TaskResponse taskResponse = new TaskResponse();
                taskResponse.setEmoji(taskEntity.getEmoji());
                taskResponse.setTaskId(taskEntity.getTaskId());
                taskResponse.setTaskScore(taskEntity.getTaskScore());
                taskResponse.setTaskName(taskEntity.getTaskName());
                taskResponse.setLeft(taskEntity.getLeftTask());
                taskResponseList.add(taskResponse);
            }
        }
        TaskListResponse taskListResponse = new TaskListResponse();
        taskListResponse.setCount(taskEntityPage.getTotalPages());
        taskListResponse.setTaskResponseList(taskResponseList);
        return taskListResponse;
    }

    @Override
    @Transactional
    public void completeTask(String taskId, String userId) {
        TaskEntity taskEntity  = taskRepository.getByTaskId(taskId);
        if(taskEntity == null) {
            throw new TaskNotFoundException("cannot find task by id " + taskId);
        }
        taskEntity.setLeftTask(taskEntity.getLeftTask() - 1);
        taskRepository.save(taskEntity);
        scoreService.add(userId, taskEntity);
        UserTaskAsst userTaskAsst = new UserTaskAsst();
        userTaskAsst.setCompleteTime(LocalDateTime.now());
        userTaskAsst.setAsstId(UUID.randomUUID().toString());
        userTaskAsst.setTaskId(taskId);
        userTaskAsst.setUserId(userId);
        userTaskAsst.setTaskName(taskEntity.getTaskName());
        userTaskAsst.setTaskScore(taskEntity.getTaskScore());
        userTaskAsstRepository.save(userTaskAsst);
    }

    @Override
    public void add(TaskEntity taskEntity) {
        taskEntity.setTaskId(UUID.randomUUID().toString());
        taskRepository.save(taskEntity);
    }
}
