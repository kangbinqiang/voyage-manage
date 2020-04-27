package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MenuMO {

    @ApiModelProperty(value = "菜单id")
    private String id;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单跳转地址")
    private String path;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "父id")
    private String parentId;

    @ApiModelProperty(value = "子菜单")
    private List<MenuMO> children;
}
