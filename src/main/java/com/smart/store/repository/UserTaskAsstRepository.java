package com.smart.store.repository;

import com.smart.store.model.entity.UserTaskAsst;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskAsstRepository extends JpaRepository<UserTaskAsst, String> {

    Page<UserTaskAsst> findAllByUserIdOrderByCompleteTimeDesc(String userId, Pageable pageable);
}
