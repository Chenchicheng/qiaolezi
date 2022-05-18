package com.smart.store.model.entity;

import com.smart.store.model.ScoreAble;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sign")
public class SignEntity extends BaseEntity implements ScoreAble {

    private LocalDate signDate;

    private int score;

    private String signId;

    private String userId;

    public static SignEntity of(String userId) {
        SignEntity signEntity = new SignEntity();
        signEntity.setUserId(userId);
        signEntity.setSignId(UUID.randomUUID().toString());
        signEntity.setSignDate(LocalDate.now());
        signEntity.setScore(1);
        return signEntity;
    }
}
