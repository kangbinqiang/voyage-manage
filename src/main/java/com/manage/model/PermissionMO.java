package com.manage.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PermissionMO {


    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限路径")
    private String path;

    @ApiModelProperty(value = "权限等级")
    private String level;


}
