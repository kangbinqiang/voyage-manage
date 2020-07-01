package com.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.entity.OrderDO;
import com.manage.mapper.OrderMapper;
import com.manage.model.OrderMO;
import com.manage.model.OrderQueryMO;
import com.manage.service.OrderService;
import com.manage.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderMapper orderMapper;


    @Override
    public void addOrder(OrderMO orderMO) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderMO, orderDO);
        orderDO.setId(StringUtil.generateUUID());
        orderDO.setOrderNo(StringUtil.generateUUID());
        orderDO.setCreatedTime(new Date());
        orderDO.setCreatedBy("admin");
        orderDO.setUpdatedTime(new Date());
        orderDO.setUpdatedBy("admin");
        orderMapper.insertSelective(orderDO);
    }

    @Override
    public PageInfo<OrderDO> listOrder(OrderQueryMO queryMO) {
        PageHelper.startPage(queryMO.getPageNumber(), queryMO.getPageSize());
        OrderDO orderDO = new OrderDO();
        if (StringUtils.isNotBlank(queryMO.getOrderNo())) {
            orderDO.setOrderNo(queryMO.getOrderNo());
        }
        List<OrderDO> all = orderMapper.select(orderDO);
        return new PageInfo<>(all);
    }
}
