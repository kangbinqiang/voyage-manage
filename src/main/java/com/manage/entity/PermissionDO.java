package com.manage.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "m_permission")
@Data
public class PermissionDO extends BaseDO {

    @Column(name = "permission_name")
    private String name;

    @Column(name = "permission_path")
    private String path;

    @Column(name = "permission_level")
    private String level;
}
