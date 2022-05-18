package com.smart.store.service;

import java.util.List;

public interface SignService {

    int sign(String userId);

    List<Integer> getSignDaysByMonth(int year, int month, String userId);
}
