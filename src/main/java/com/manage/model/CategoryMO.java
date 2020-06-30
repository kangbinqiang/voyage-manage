package com.manage.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryMO {

    @ApiModelProperty(name = "分类id")
    private String cateId;

    @ApiModelProperty(name = "子节点")
    private List<CategoryMO> children;

    @ApiModelProperty(name = "分类名称")
    private String cateName;

    @ApiModelProperty(name = "分类层级")
    private Integer cateLevel;

    @ApiModelProperty(name = "状态")
    private Boolean cateStatus;

    @ApiModelProperty(name = "父id")
    private String parentId;

}
