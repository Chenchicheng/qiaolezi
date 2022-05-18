package com.smart.store.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "score")
public class ScoreEntity extends BaseEntity {

    @Column(name = "score", columnDefinition = "int(11) default 0 comment '当前分数'")
    private long score;

    @Column(name = "user_id", columnDefinition = "varchar(255) default null comment '用户ID'")
    private String userId;

    @Column(name = "total_score", columnDefinition = "int(11) default 0 comment '总分数'")
    private long totalScore;

    public static ScoreEntity of(String userId) {
        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setScore(0);
        scoreEntity.setTotalScore(0);
        scoreEntity.setUserId(userId);
        return scoreEntity;
    }
}
