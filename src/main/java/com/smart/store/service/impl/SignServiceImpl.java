package com.smart.store.service.impl;

import com.smart.store.exception.AlreadySignException;
import com.smart.store.model.entity.ScoreEntity;
import com.smart.store.model.entity.SignEntity;
import com.smart.store.repository.ScoreRepository;
import com.smart.store.repository.SignRepository;
import com.smart.store.service.ScoreService;
import com.smart.store.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class SignServiceImpl implements SignService {

    @Autowired
    SignRepository signRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    ScoreService scoreService;

    @Override
    public int sign(String userId) {
        LocalDate localDate = LocalDate.now();
        int count = signRepository.countBySignDateAndUserId(localDate, userId);
        if (count > 0) {
            throw new AlreadySignException();
        }
        SignEntity signEntity = SignEntity.of(userId);
        signRepository.save(signEntity);
        scoreService.add(userId, signEntity);
        return signEntity.getScore();
    }

    @Override
    public List<Integer> getSignDaysByMonth(int year, int month, String userId) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = null;
        List<Integer> list = new ArrayList<>();
        if (month == 12) {
            endDate = LocalDate.of(year + 1, 1, 1);
        } else {
            endDate = LocalDate.of(year, month + 1, 1);
        }
        List<SignEntity> signEntities = signRepository.findAllBySignDateBetweenAndSignDateIsNot(startDate, endDate, endDate);
        if (signEntities != null) {
            for (SignEntity signEntity : signEntities) {
                list.add(signEntity.getSignDate().getDayOfMonth());
            }
        }
        return list;
    }
}
