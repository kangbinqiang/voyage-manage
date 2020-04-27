package com.manage.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_role")
@Data
public class RoleDO extends BaseDO{


    @Column(name = "role_name")
    private String name;

    @Column(name = "role_desc")
    private String desc;
}
