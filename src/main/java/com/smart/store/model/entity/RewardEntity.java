package com.smart.store.model.entity;

import com.smart.store.model.ScoreAble;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "reward")
@NoArgsConstructor
@AllArgsConstructor
public class RewardEntity extends BaseEntity implements ScoreAble {

    @Column(name = "reward_id")
    private String rewardId;

    @Column(name = "reward_score", columnDefinition = "int(11) default 0 comment '奖励分数'")
    private int rewardScore;

    @Column(name = "reward_name", columnDefinition = "varchar(255) default null comment '奖励名称'")
    private String rewardName;

    @Column(name = "picture_id", columnDefinition = "varchar(255) default null comment '图片ID'")
    private String pictureId;

    @Column(name = "reward_left", columnDefinition = "int(11) default 0 comment '剩余可兑换次数'")
    private int rewardLeft;

    @Column(name = "status", columnDefinition = "int(11) default 0 comment '状态0-审核中1-使用中'")
    private int status;

    @Column(name = "total", columnDefinition = "int(11) default 0 comment '总共可兑换次数'")
    private int total;

    @Column(name = "emoji", columnDefinition = "varchar(50) default null comment 'emoji'")
    private String emoji;

    @Column(name = "reward_url", columnDefinition = "varchar(255) default null comment '奖品详情'")
    private String rewardUrl;

    @Override
    public int getScore() {
        return rewardScore;
    }
}
