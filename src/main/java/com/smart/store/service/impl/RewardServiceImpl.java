package com.smart.store.service.impl;

import com.smart.store.exception.RewardNotFoundException;
import com.smart.store.exception.ScoreNotEnoughException;
import com.smart.store.model.entity.RewardEntity;
import com.smart.store.model.entity.ScoreEntity;
import com.smart.store.model.entity.UserRewardAsst;
import com.smart.store.model.response.RecordListResponse;
import com.smart.store.model.response.RecordResponse;
import com.smart.store.model.response.RewardListResponse;
import com.smart.store.model.response.RewardResponse;
import com.smart.store.repository.RewardRepository;
import com.smart.store.repository.ScoreRepository;
import com.smart.store.repository.UserRewardAsstRepository;
import com.smart.store.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserRewardAsstRepository userRewardAsstRepository;

    @Override
    public RewardListResponse getRewardList(int page, int count) {
        Page<RewardEntity> rewardEntityPage = rewardRepository.findAllByRewardLeftIsNotOrderByRewardScoreAsc(0, PageRequest.of(page - 1, count));
        List<RewardResponse> rewardResponses = new ArrayList<>();
        RewardListResponse listResponse = new RewardListResponse();
        listResponse.setTotalPage(rewardEntityPage.getTotalPages());
        if (!rewardEntityPage.isEmpty()) {
            List<RewardEntity> rewardEntityList = rewardEntityPage.getContent();
            for (RewardEntity rewardEntity : rewardEntityList) {
                RewardResponse rewardResponse = new RewardResponse();
                rewardResponse.setLeft(rewardEntity.getRewardLeft());
                rewardResponse.setRewardName(rewardEntity.getRewardName());
                rewardResponse.setRewardScore(rewardEntity.getRewardScore());
                rewardResponse.setRewardId(rewardEntity.getRewardId());
                rewardResponse.setUrl(rewardEntity.getRewardUrl());
                rewardResponses.add(rewardResponse);
            }
        }
        listResponse.setRewardResponseList(rewardResponses);
        return listResponse;
    }

    @Override
    @Transactional
    public void gainReward(String rewardId, String userId) {
        LocalDateTime gainTime = LocalDateTime.now();
        log.info(gainTime.toString());
        UserRewardAsst asst = new UserRewardAsst();
        asst.setAsstId(UUID.randomUUID().toString());
        asst.setGainTime(gainTime);
        asst.setRewardId(rewardId);
        RewardEntity rewardEntity = rewardRepository.getByRewardId(rewardId);
        if (rewardEntity == null) {
            throw new RewardNotFoundException("cannot find reward by id " + rewardId);
        }
        asst.setRewardName(rewardEntity.getRewardName());
        asst.setRewardScore(rewardEntity.getRewardScore());
        asst.setUserId(userId);
        asst.setStatus("0");
        userRewardAsstRepository.save(asst);
        if (rewardEntity.getRewardLeft() > 0) {
            rewardEntity.setRewardLeft(rewardEntity.getRewardLeft() - 1);
        }
        rewardRepository.save(rewardEntity);
        int gainScore = rewardEntity.getRewardScore();
        ScoreEntity scoreEntity = scoreRepository.findByUserId(userId);
        if (scoreEntity.getScore() - gainScore < 0) {
            throw new ScoreNotEnoughException("土币不够啦～");
        }
        scoreEntity.setScore(scoreEntity.getScore() - gainScore);
        scoreRepository.save(scoreEntity);
    }

    @Override
    public RecordListResponse getRecordByUserId(String userId, String status, int page ,int count) {
        Page<UserRewardAsst> userRewardAsstPage;
        RecordListResponse recordListResponse = new RecordListResponse();
        if (StringUtils.isEmpty(status)) {
            userRewardAsstPage = userRewardAsstRepository.findAllByUserIdOrderByGainTimeDesc(userId, PageRequest.of(page - 1, count));
        } else {
            userRewardAsstPage = userRewardAsstRepository.findAllByUserIdAndStatusOrderByGainTimeDesc(userId, status, PageRequest.of(page - 1, count));
        }
        DateTimeFormatter dfDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (userRewardAsstPage.hasContent()) {
            List<RecordResponse> recordResponseList = new ArrayList<>();
            List<UserRewardAsst> userRewardAsstList = userRewardAsstPage.getContent();
            for (UserRewardAsst userRewardAsst : userRewardAsstList) {
                RecordResponse recordResponse = new RecordResponse();
                recordResponse.setStatus(userRewardAsst.getStatus());
                recordResponse.setRewardName(userRewardAsst.getRewardName());
                recordResponse.setAsstId(userRewardAsst.getAsstId());
                recordResponse.setTookScore(userRewardAsst.getRewardScore());
                recordResponse.setDateTime(dfDateTime.format(userRewardAsst.getGainTime()));
                recordResponseList.add(recordResponse);
            }
            recordListResponse.setRecordResponseList(recordResponseList);
            recordListResponse.setTotalPage(userRewardAsstPage.getTotalPages());
        }
        return recordListResponse;
    }

    @Override
    public void confirmReward(String asstId) {
        UserRewardAsst asst = userRewardAsstRepository.findByAsstId(asstId);
        if (asst == null) {
            throw new RewardNotFoundException("无法找到相应订单～");
        }
        asst.setStatus("2");
        userRewardAsstRepository.save(asst);
    }

    @Override
    public RewardEntity add(RewardEntity rewardEntity) {
        rewardEntity.setRewardId(UUID.randomUUID().toString());
        return rewardRepository.save(rewardEntity);
    }
}
