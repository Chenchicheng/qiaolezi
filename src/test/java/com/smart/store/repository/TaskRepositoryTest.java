package com.smart.store.repository;

import com.smart.store.model.entity.TaskEntity;
import javafx.concurrent.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author chenchicheng
 * @date 2022/4/13
 */
@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void save() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskScore(2);
        taskEntity.setStatus(0);
        taskRepository.save(taskEntity);
    }

    @Test
    void getByTaskId() {
        TaskEntity byTaskId = taskRepository.getByTaskId("1");
        System.out.println(byTaskId);
    }
}