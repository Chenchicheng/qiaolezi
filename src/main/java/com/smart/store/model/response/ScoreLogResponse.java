package com.smart.store.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScoreLogResponse {

    private int score;

    /**
     * 0-完成任务/1-兑换
     */
    private String action;

    private String name;

    private LocalDateTime dateTime;
}
