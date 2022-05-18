package com.smart.store.repository;

import com.smart.store.model.entity.UserRewardAsst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRewardAsstRepository extends JpaRepository<UserRewardAsst, String> {

    Page<UserRewardAsst> findAllByUserIdOrderByGainTimeDesc(String userId, Pageable pageable);

    Page<UserRewardAsst> findAllByUserIdAndStatusOrderByGainTimeDesc(String userId, String status, Pageable pageable);

    UserRewardAsst findByAsstId(String asstId);
}
