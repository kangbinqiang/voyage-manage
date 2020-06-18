package com.manage.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "m_cate")
public class CateDO extends BaseDO{


    @Column(name = "cate_id")
    private String cateId;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "cate_name")
    private String cateName;

    @Column(name = "cate_level")
    private Integer cateLevel;

    @Column(name = "cate_status")
    private Boolean cateStatus;

}
