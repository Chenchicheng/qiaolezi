package com.smart.store.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ScoreLogListResponse {

    private List<ScoreLogResponse> scoreLogResponseList;

    private int count;

    private int page;

    private int totalCount;
}
