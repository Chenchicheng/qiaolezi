package com.smart.store.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{

    private String useId;

    private String username;

    private String password;

    private String roleId;
}
