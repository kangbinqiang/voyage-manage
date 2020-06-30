package com.manage.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "m_param")
@Entity
public class ParamDO extends BaseDO {

    @Column(name = "attr_name")
    private String attrName;

    @Column(name = "cate_id")
    private String cateId;

    @Column(name = "type")
    private String type;
}
