package com.smart.store.controller.v1;

import com.smart.store.model.entity.RewardEntity;
import com.smart.store.model.response.RecordListResponse;
import com.smart.store.model.response.RewardListResponse;
import com.smart.store.service.RewardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reward")
@Api(value = "Reward_Op")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping("/{page}/{count}")
    public RewardListResponse getRewardList(@PathVariable int page, @PathVariable int count) {
        return rewardService.getRewardList(page, count);
    }

    @PostMapping("/gain/{rewardId}/{userId}")
    public void gainReward(@PathVariable("rewardId") String rewardId, @PathVariable("userId") String userId) {
        Assert.notNull(rewardId, "rewardId cannot be null");
        Assert.notNull(userId, "userId cannot be null");
        rewardService.gainReward(rewardId, userId);
    }

    @GetMapping("/record/{userId}/{page}/{count}")
    public RecordListResponse getRecordByUserId(@PathVariable("userId")String userId, String status, @PathVariable("page") int page, @PathVariable("count") int count) {
        Assert.notNull(userId, "userId cannot be null");
        return rewardService.getRecordByUserId(userId, status, page, count);
    }

    @PostMapping("/confirm/{asstId}")
    public void confirmReward(@PathVariable("asstId") String asstId) {
        Assert.notNull(asstId, "asstId cannot be null");
        rewardService.confirmReward(asstId);
    }

    @PostMapping("/add")
    public void add(RewardEntity rewardEntity) {
        rewardService.add(rewardEntity);
    }
}
