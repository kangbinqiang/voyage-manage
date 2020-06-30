package com.manage.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "m_goods")
public class GoodsDO extends BaseDO {


    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    @Column(name = "goods_number")
    private Integer goodsNumber;
}
