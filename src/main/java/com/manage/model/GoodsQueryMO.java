package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class GoodsQueryMO {

    @ApiModelProperty(value = "起始页数")
    private Integer pageNumber;

    @ApiModelProperty(value = "页数大小")
    private Integer pageSize;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;
}
