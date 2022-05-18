package com.smart.store.model.entity;

import com.smart.store.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "vacation")
public class VacationEntity extends BaseEntity {

    private String vacationId;

    private int year;

    private int month;

    private int day;
}
