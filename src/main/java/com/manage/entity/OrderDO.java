package com.manage.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Table(name = "m_order")
@Entity
public class OrderDO extends BaseDO {

    @Column(name = "orderNo")
    private String orderNo;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "pay")
    private boolean pay;

    @Column(name = "post")
    private boolean post;


}
