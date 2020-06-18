package com.manage.controller;


import com.github.pagehelper.PageInfo;
import com.manage.common.ResponseMO;
import com.manage.model.CateMO;
import com.manage.model.CateQueryMO;
import com.manage.model.CategoryMO;
import com.manage.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/goods")
@Api(tags = "商品管理")
@Slf4j
public class GoodsController extends BaseController {


    @Autowired
    private GoodsService goodsService;

    @PostMapping("/addCate")
    @ApiOperation(value = "添加分类")
    public ResponseMO addCate(@Valid @RequestBody CateMO cateMO) {
        goodsService.ddCate(cateMO);
        return success("新增成功");
    }

    @PostMapping("/cate/list")
    @ApiOperation(value = "展示分类")
    public ResponseMO<PageInfo<CategoryMO>> listCate(@Valid @RequestBody CateQueryMO cateQueryMO) {
        PageInfo<CategoryMO> result = goodsService.listCate(cateQueryMO);
        return success(result);
    }

}
