package com.smart.store.service;

import com.smart.store.model.ScoreAble;
import com.smart.store.model.entity.ScoreEntity;
import com.smart.store.model.response.ScoreLogListResponse;

public interface ScoreService {

    long getScoreByUserId(String userId);

    long getTotalScoreByUserId(String userId);

    ScoreLogListResponse getScoreLogListByUserId(String userId, int page, int count);

    ScoreEntity add(String userId, ScoreAble able);
}
