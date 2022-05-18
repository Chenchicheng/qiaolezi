package com.smart.store.repository;

import com.smart.store.model.entity.UserTaskAsst;
import com.smart.store.model.entity.VacationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<VacationEntity, String> {

    List<VacationEntity> getAllByYearAndMonth(int year, int month);
}
