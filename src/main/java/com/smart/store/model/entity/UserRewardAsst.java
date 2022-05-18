package com.smart.store.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "user_reward")
@Entity
public class UserRewardAsst extends BaseEntity{

    private String asstId;

    private LocalDateTime gainTime;

    private String userId;

    private String rewardId;

    private String rewardName;

    private int rewardScore;

    @Column(name = "status", columnDefinition = "varchar(5) comment '0-备货中/1-已催单/2-已发放' default '0'")
    private String status;
}
