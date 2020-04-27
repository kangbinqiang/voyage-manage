package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class PermissionVO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限路径")
    private String path;

    @ApiModelProperty(value = "权限级别")
    private String level;

    @ApiModelProperty(value = "子权限")
    private List<PermissionVO> permissions;
}
