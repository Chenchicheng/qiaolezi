package com.smart.store.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordResponse {

    private String rewardName;

    private String status;

    private int tookScore;

    private String asstId;

    private String dateTime;
}
