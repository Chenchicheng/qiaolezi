package com.smart.store.service.impl;

import com.smart.store.model.entity.VacationEntity;
import com.smart.store.repository.VacationRepository;
import com.smart.store.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {

    @Autowired
    VacationRepository vacationRepository;

    @Override
    public void setVacation() {
        VacationEntity vacationEntity = new VacationEntity();
        vacationEntity.setDay(LocalDate.now().getDayOfMonth());
        vacationEntity.setDay(LocalDate.now().getYear());
        vacationEntity.setMonth(LocalDate.now().getMonthValue());
        vacationRepository.save(vacationEntity);
    }

    @Override
    public List<Integer> getVacation(int year, int month) {
        List<Integer> list = new ArrayList<>();
        for (VacationEntity vacationEntity : vacationRepository.getAllByYearAndMonth(year, month)) {
            list.add(vacationEntity.getDay());
        }
        return list;
    }

    @Override
    public int getDaysBetween() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        VacationEntity lastVacation = new VacationEntity();
        List<VacationEntity> vacationEntityList = new ArrayList<>();
        while (vacationRepository.getAllByYearAndMonth(year, month).size() == 0) {
            if (month == 1) {
                year = year - 1;
                month = 12;
            } else {
                month--;
            }
        }
        vacationEntityList = vacationRepository.getAllByYearAndMonth(year, month);
        if (vacationEntityList.size() == 2) {
            lastVacation = vacationEntityList.get(1);
        } else {
            lastVacation = vacationEntityList.get(0);
        }
        LocalDate last = LocalDate.of(lastVacation.getYear(), lastVacation.getMonth(), lastVacation.getDay());
        Period period = Period.between(last, LocalDate.now());
        return period.getDays();
    }

}
