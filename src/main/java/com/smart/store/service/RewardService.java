package com.smart.store.service;

import com.smart.store.model.entity.RewardEntity;
import com.smart.store.model.response.RecordListResponse;
import com.smart.store.model.response.RewardListResponse;

public interface RewardService {

    RewardListResponse getRewardList(int page, int count);

    void gainReward(String rewardId, String userId);

    RecordListResponse getRecordByUserId(String userId, String status, int page, int count);

    void confirmReward(String asstId);

    RewardEntity add(RewardEntity rewardEntity);
}
