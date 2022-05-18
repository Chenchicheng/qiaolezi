package com.smart.store.model.response;

import lombok.Data;

import java.util.List;

@Data
public class RecordListResponse {

    private int page;

    private int count;

    private int totalPage;

    private List<RecordResponse> recordResponseList;
}
