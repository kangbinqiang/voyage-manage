package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;


@Data
public class ParamMO {

    @ApiModelProperty(name = "attr_name")
    private String attrName;

    @ApiModelProperty(name = "cate_id")
    private String cateId;

    @ApiModelProperty(name = "type")
    private String type;
}
