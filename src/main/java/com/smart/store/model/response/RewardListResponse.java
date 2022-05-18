package com.smart.store.model.response;

import lombok.Data;

import java.util.List;

@Data
public class RewardListResponse {

    private List<RewardResponse> rewardResponseList;

    private int page;

    private int count;

    private int totalPage;
}
