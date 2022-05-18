package com.smart.store.controller.v1;

import com.smart.store.model.response.ScoreLogListResponse;
import com.smart.store.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/score")
@Api(value = "Score_Op")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    @GetMapping("/{userId}")
    @ApiImplicitParam(paramType = "path", name = "userId", required = true)
    public long getScoreByUserId(@PathVariable("userId")String userId) {
        return scoreService.getScoreByUserId(userId);
    }

    @GetMapping("/total/{userId}")
    public long getTotalScoreByUserId(@PathVariable("userId")String userId) {
        return scoreService.getTotalScoreByUserId(userId);
    }

    @GetMapping("/log/{userId}/{page}/{count}")
    public ScoreLogListResponse getScoreLogListByUserId(@PathVariable("userId") String userId
            , @PathVariable("page")int page, @PathVariable("count")int count) {
        return scoreService.getScoreLogListByUserId(userId, page, count);
    }
}
