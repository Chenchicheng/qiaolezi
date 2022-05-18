package com.smart.store.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user_task")
public class UserTaskAsst extends BaseEntity{
    private String asstId;

    private LocalDateTime completeTime;

    private String userId;

    private String taskId;

    private String taskName;

    private int taskScore;
}
