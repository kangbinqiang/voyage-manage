package com.manage.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserMO {

    @ApiModelProperty(value = "id")
    private String id;

    @NotEmpty
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String code;
}
