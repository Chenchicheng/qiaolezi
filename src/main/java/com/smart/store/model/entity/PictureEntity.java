package com.smart.store.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "picture")
public class PictureEntity extends BaseEntity{

    @Column(name = "picture_id")
    private String pictureId;

    @Column(name = "url", columnDefinition = "varchar(255) default null comment '图片地址'")
    private String url;

    @Column(name = "type", columnDefinition = "varchar(10) default null comment '图片类型'")
    private String type;
}
