package com.manage.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "m_menu")
public class MenuDO extends BaseDO{

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "path")
    private String path;

    @Column(name = "icon")
    private String icon;

    @Column(name = "parent_id")
    private String parentId;

}
