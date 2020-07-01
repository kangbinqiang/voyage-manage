package com.manage.controller;


import com.github.pagehelper.PageInfo;
import com.manage.common.ResponseMO;
import com.manage.entity.OrderDO;
import com.manage.model.OrderMO;
import com.manage.model.OrderQueryMO;
import com.manage.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "订单管理")
@RequestMapping("/order")
@Slf4j
public class OrderController extends BaseController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    @ApiOperation(value = "新增订单")
    public ResponseMO addOrder(@Valid @RequestBody OrderMO orderMO) {
        orderService.addOrder(orderMO);
        return success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "展示订单")
    public ResponseMO<PageInfo<OrderDO>> listOrder(@Valid @RequestBody OrderQueryMO queryMO) {
        PageInfo<OrderDO> all = orderService.listOrder(queryMO);
        return success(all);
    }


}
