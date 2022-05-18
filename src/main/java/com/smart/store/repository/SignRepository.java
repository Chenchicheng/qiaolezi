package com.smart.store.repository;

import com.smart.store.model.entity.ScoreEntity;
import com.smart.store.model.entity.SignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SignRepository extends JpaRepository<SignEntity, String> {

    int countBySignDateAndUserId(LocalDate localDate, String userId);

    List<SignEntity> findAllBySignDateBetweenAndSignDateIsNot(LocalDate startDate, LocalDate endDate1, LocalDate endDate2);
}
