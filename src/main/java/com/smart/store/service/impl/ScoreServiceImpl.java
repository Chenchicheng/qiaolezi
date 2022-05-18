package com.smart.store.service.impl;

import com.smart.store.model.ScoreAble;
import com.smart.store.model.entity.ScoreEntity;
import com.smart.store.model.entity.UserRewardAsst;
import com.smart.store.model.entity.UserTaskAsst;
import com.smart.store.model.response.ScoreLogListResponse;
import com.smart.store.model.response.ScoreLogResponse;
import com.smart.store.repository.ScoreRepository;
import com.smart.store.repository.UserRewardAsstRepository;
import com.smart.store.repository.UserTaskAsstRepository;
import com.smart.store.service.RewardService;
import com.smart.store.service.ScoreService;
import com.smart.store.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserTaskAsstRepository userTaskAsstRepository;

    @Autowired
    UserRewardAsstRepository userRewardAsstRepository;

    @Override
    public long getScoreByUserId(String userId) {
        ScoreEntity scoreEntity = scoreRepository.findByUserId(userId);
        return scoreEntity == null ? 0 : scoreEntity.getScore();
    }

    @Override
    public long getTotalScoreByUserId(String userId) {
        ScoreEntity scoreEntity = scoreRepository.findByUserId(userId);
        return scoreEntity == null ? 0 : scoreEntity.getTotalScore();
    }

    @Override
    public ScoreLogListResponse getScoreLogListByUserId(String userId, int page, int count) {
        Page<UserRewardAsst> userRewardAsstPage = userRewardAsstRepository.findAllByUserIdOrderByGainTimeDesc(userId, PageRequest.of(page - 1, count));
        Page<UserTaskAsst> userTaskPage = userTaskAsstRepository.findAllByUserIdOrderByCompleteTimeDesc(userId, PageRequest.of(page - 1, count));
        ScoreLogListResponse scoreLogListResponse = new ScoreLogListResponse();
        scoreLogListResponse.setCount(userTaskPage.getTotalPages() + userRewardAsstPage.getTotalPages());
        if (userRewardAsstPage.getContent().size() == 0 && userTaskPage.getContent().size() == 0) {
            scoreLogListResponse.setScoreLogResponseList(new ArrayList<>());
            return scoreLogListResponse;
        }
        List<ScoreLogResponse> scoreLogResponseList = new ArrayList<>();
        List<UserRewardAsst> userRewardAsstList = userRewardAsstPage.getContent();
        List<UserTaskAsst> userTaskAsstList = userTaskPage.getContent();
        int i = 0;
        int j = 0;
        while (scoreLogResponseList.size() < count) {
            UserRewardAsst userRewardAsst = userRewardAsstList.get(i);
            UserTaskAsst userTaskAsst = userTaskAsstList.get(j);
            ScoreLogResponse scoreLogResponse = new ScoreLogResponse();
            if (userRewardAsst.getGainTime().isAfter(userTaskAsst.getCompleteTime()) || userTaskAsstList.size() == 0) {
                scoreLogResponse.setAction("1");
                scoreLogResponse.setDateTime(userRewardAsst.getGainTime());
                scoreLogResponse.setScore(userRewardAsst.getRewardScore());
                scoreLogResponse.setName(userRewardAsst.getRewardName());
                i++;
            } else {
                scoreLogResponse.setAction("2");
                scoreLogResponse.setDateTime(userTaskAsst.getCompleteTime());
                scoreLogResponse.setName(userTaskAsst.getTaskName());
                scoreLogResponse.setScore(userTaskAsst.getTaskScore());
            }
            scoreLogResponseList.add(scoreLogResponse);
        }
        scoreLogListResponse.setScoreLogResponseList(scoreLogResponseList);
        return scoreLogListResponse;
    }

    @Override
    public ScoreEntity add(String userId, ScoreAble able) {
        ScoreEntity scoreEntity = scoreRepository.findByUserId(userId);
        if (scoreEntity == null) {
            scoreEntity = scoreRepository.save(ScoreEntity.of(userId));
        }
        scoreEntity.setTotalScore(scoreEntity.getTotalScore() + able.getScore());
        scoreEntity.setScore(scoreEntity.getScore() + able.getScore());
        return scoreRepository.save(scoreEntity);
    }
}
