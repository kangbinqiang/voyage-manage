package com.manage.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CateMO {

    @ApiModelProperty(name = "分类id")
    private String cateId;

    @ApiModelProperty(name = "父id")
    private String parentId;

    @ApiModelProperty(name = "分类名称")
    private String cateName;

    @ApiModelProperty(name = "分类层级")
    private Integer cateLevel;

    @ApiModelProperty(name = "状态")
    private Boolean cateStatus;

}
