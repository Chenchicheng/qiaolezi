package com.smart.store.repository;

import com.smart.store.model.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, String> {

    ScoreEntity findByUserId(String userId);
}
