package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class DispatcherMO {

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("权限id列表")
    private List<String> ids;
}
