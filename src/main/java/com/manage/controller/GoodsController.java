package com.manage.controller;


import com.github.pagehelper.PageInfo;
import com.manage.common.ResponseMO;
import com.manage.entity.GoodsDO;
import com.manage.model.*;
import com.manage.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @GetMapping("/parent/cate/list")
    @ApiOperation(value = "展示分类")
    public ResponseMO<List<CategoryMO>> listParentCate() {
        return success(goodsService.listParentCate());
    }

    @DeleteMapping("/delete/cate/{cateId}")
    @ApiOperation(value = "删除分类")
    public ResponseMO deleteCate(@PathVariable("cateId") String cateId) {
        goodsService.deleteCate(cateId);
        return success();
    }

    @GetMapping("/params/{cateId}")
    @ApiOperation(value = "展示参数或属性")
    public ResponseMO<List<ParamMO>> listParams(@PathVariable("cateId") String cateId, @RequestParam("type") String type) {
        List<ParamMO> result = goodsService.listParams(cateId,type);
        return success(result);
    }

    @PostMapping("/add/params")
    @ApiOperation(value = "新增参数或属性")
    public ResponseMO listParams(@Valid @RequestBody ParamMO paramMO) {
        goodsService.addParam(paramMO);
        return success();
    }

    @PostMapping("/add/goods")
    @ApiOperation(value = "新增商品")
    public ResponseMO addGoods(@Valid @RequestBody GoodsMO goodsMO) {
        goodsService.addGoods(goodsMO);
        return success();
    }


    @PostMapping("/goods/list")
    @ApiOperation(value = "分页展示商品")
    public ResponseMO<PageInfo<GoodsDO>> listGoods(@Valid @RequestBody GoodsQueryMO queryMO) {
        PageInfo<GoodsDO> result = goodsService.pagingGoods(queryMO);
        return success(result);
    }

}
