package com.smart.store.service;

import java.util.List;

public interface VacationService {

    void setVacation();

    List<Integer> getVacation(int year, int month);

    int getDaysBetween();
}
