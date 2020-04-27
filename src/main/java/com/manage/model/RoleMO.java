package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoleMO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String desc;

    @ApiModelProperty(value = "权限列表")
    private List<PermissionVO> permissions;
}
