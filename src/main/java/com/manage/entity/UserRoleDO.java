package com.manage.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "m_user_role")
public class UserRoleDO extends BaseDO {


    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;
}
