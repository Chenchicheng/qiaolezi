package com.smart.store.repository;

import com.smart.store.model.entity.RewardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<RewardEntity, String> {
    RewardEntity getByRewardId(String rewardId);

    Page<RewardEntity> findAllByRewardLeftIsNotOrderByRewardScoreAsc(int left, Pageable pageable);
}
