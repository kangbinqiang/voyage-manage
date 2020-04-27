package com.manage.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "m_role_permission")
public class RolePermissionDO extends BaseDO {

    @Column(name = "role_id")
    private String roleId;

    @Column(name = "permission_id")
    private String permissionId;
}
