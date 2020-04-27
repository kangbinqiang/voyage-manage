package com.manage.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "m_user")
public class UserDO extends BaseDO {

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

}
