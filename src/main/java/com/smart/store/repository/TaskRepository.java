package com.smart.store.repository;

import com.smart.store.model.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {

    TaskEntity getByTaskId(String taskId);

    @Query("update TaskEntity task set task.leftTask = 1 where task.type = ?1")
    @Modifying
    @Transactional
    void resetTask(String taskType);
}
