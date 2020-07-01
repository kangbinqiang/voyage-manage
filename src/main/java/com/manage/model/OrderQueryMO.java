package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class OrderQueryMO {

    @ApiModelProperty(value = "起始页数")
    private Integer pageNumber;

    @ApiModelProperty(value = "页数大小")
    private Integer pageSize;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;
}
