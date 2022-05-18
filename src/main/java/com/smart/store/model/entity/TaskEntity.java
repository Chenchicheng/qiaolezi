package com.smart.store.model.entity;

import com.smart.store.model.ScoreAble;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskEntity extends BaseEntity implements ScoreAble {

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "task_name", columnDefinition = "varchar(255) default null comment '任务名称'")
    private String taskName;

    @Column(name = "task_score", columnDefinition = "int(11) default 0 comment '任务分数'")
    private int taskScore;

    @Column(name = "picture_id", columnDefinition = "varchar(255) default null comment '图片ID'")
    private String pictureId;

    @Column(name = "status", columnDefinition = "int(11) default 0 comment '状态0-未完成/1-已完成'")
    private int status;

    @Column(name = "emoji", columnDefinition = "varchar(20) default null comment 'emoji'")
    private String emoji;

    @Column(name = "type", columnDefinition = "varchar(2) default null comment '任务类型0-每日任务1-月度任务2-年度任务3-一次性任务'")
    private String type;

    @Column(name = "left_task", columnDefinition = "int(11) default 0 comment '剩余任务'")
    private int leftTask;

    @Override
    public int getScore() {
        return taskScore;
    }
}
