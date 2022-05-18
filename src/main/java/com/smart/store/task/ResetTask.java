package com.smart.store.task;

import com.smart.store.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResetTask {

    @Autowired
    TaskRepository taskRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetDailyTask() {
        taskRepository.resetTask("0");
    }
}
