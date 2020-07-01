package com.manage.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class OrderMO {

    @ApiModelProperty(value = "orderNo")
    private String orderNo;

    @ApiModelProperty(value = "price")
    private BigDecimal price;

    @ApiModelProperty(value = "pay")
    private boolean pay;

    @ApiModelProperty(value = "post")
    private boolean post;
}
