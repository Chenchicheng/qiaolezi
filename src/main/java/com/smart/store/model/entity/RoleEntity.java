package com.smart.store.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "role")
public class RoleEntity extends BaseEntity{

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "role_name", columnDefinition = "varchar(255) default null comment '角色名称'")
    private String roleName;
}
