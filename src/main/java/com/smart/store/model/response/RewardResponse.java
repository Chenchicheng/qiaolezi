package com.smart.store.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RewardResponse {

    private String rewardId;

    private int rewardScore;

    private String rewardName;

    private String url;

    private String emoji;

    private int left;

    private int total;
}
