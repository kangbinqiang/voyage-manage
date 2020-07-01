package com.manage.service;

import com.github.pagehelper.PageInfo;
import com.manage.entity.OrderDO;
import com.manage.model.OrderMO;
import com.manage.model.OrderQueryMO;

public interface OrderService {


    void addOrder(OrderMO orderMO);

    PageInfo<OrderDO> listOrder(OrderQueryMO queryMO);
}
